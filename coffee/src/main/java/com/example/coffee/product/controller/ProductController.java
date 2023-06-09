package com.example.coffee.product.controller;

import com.example.coffee.product.dto.ProductDTO;
import com.example.coffee.product.dto.SizeProductDTO;
import com.example.coffee.product.dto.TypeProductDTO;
import com.example.coffee.product.model.Email;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.EmailService;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.product.service.ISizeService;
import com.example.coffee.product.service.ITypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

//@Controller
public class ProductController {
//    @Autowired
    private IProductService iProductService;
//    @Autowired
    private ITypeService iTypeService;
//    @Autowired
    private ISizeService iSizeService;

//    @Autowired
    private EmailService emailService;
    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
//    @Value("${upload.path}")
    private String fileUpload;
//    @GetMapping("/")
//    public String order(){
//        return "redirect:/orderController/list";
//    }

    @GetMapping("/")
    public String ShowListProduct(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model) {
        Page<Product> productPage = iProductService.findAll(page);
        model.addAttribute("productPage", productPage);
        return "/orders";
    }

    @PostMapping("/homeMail")
    public String sendMail(@ModelAttribute("email") Email email) {
        emailService.sendEmail(email);
        return "redirect:/";
    }

    @GetMapping("/create")
    private String create(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        model.addAttribute("productDto", new ProductDTO());
        model.addAttribute("typePage", iTypeService.findAll(page));
        model.addAttribute("sizePage", iSizeService.findAll(page));
        return "/product/create";
    }

//    @PostMapping("/createProduct")
//    public String createProduct(@ModelAttribute("productPage") ProductDTO productDTO,@RequestParam("image") MultipartFile image) throws IOException {
//        Product product = new Product();
//        BeanUtils.copyProperties(productDTO, product);
//        StringBuilder fileNames = new StringBuilder();
////        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, image.getOriginalFilename());
//        fileNames.append(image.getOriginalFilename());
//        Files.write(Paths.get(UPLOAD_DIRECTORY), image.getBytes());
////        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
//
//        iProductService.save(product);
//        return "redirect:/";
//    }

    @PostMapping("/createProduct")
    public RedirectView createProduct(@ModelAttribute("productPage") ProductDTO productDTO,@RequestParam("image")  MultipartFile multipartFile){
        Product product = new Product();
        multipartFile=productDTO.getImage();
        String fileName = multipartFile.getOriginalFilename();
        BeanUtils.copyProperties(productDTO, product); new File(this.fileUpload + fileName);
        product.setImage(fileName);
        iProductService.save(product);
        return new RedirectView("redirect:/");
    }


//    @PostMapping("/createSong")
//    public String createSong(@Validated @ModelAttribute("song") SongDto songDto, BindingResult bindingResult , RedirectAttributes attributes) {
//        new SongDto().validate(songDto, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "create";
//        } else {
//            Song song = new Song();
//            BeanUtils.copyProperties(songDto, song);
//            iSongService.save(song);
//            attributes.addFlashAttribute("mess",true);
//            return "redirect:/";
//        }
//    }
}

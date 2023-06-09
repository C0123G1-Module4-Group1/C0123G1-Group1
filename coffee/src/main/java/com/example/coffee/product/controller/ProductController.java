package com.example.coffee.product.controller;

import com.example.coffee.product.dto.ProductDTO;
import com.example.coffee.product.model.Product;
import com.example.coffee.product.service.IProductService;
import com.example.coffee.product.service.ITypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/productCoffee")
public class ProductController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ITypeService iTypeService;



    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    //    @Value("${upload.path}")
    private String fileUpload;

    //    trang chủ của admin trước khi vào các chức năng
    @GetMapping("/homeAdmin")
    public String homeAdmin() {
        return "homeAdmin";
    }

    @GetMapping("/listProduct")
    public String ShowListProduct(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model,
                                  HttpServletResponse httpResponse) {
        Page<Product> productPage = iProductService.findAllByStatusIsFalse(page);
        model.addAttribute("productPage", productPage);
//        xoá cache
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        return "product/listAdminProduct";
    }

    //tạo ms sản phẩm
    @GetMapping("/create")
    private String create(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        model.addAttribute("productDto", new ProductDTO());
        model.addAttribute("typePage", iTypeService.findAll(page));
        return "product/createProduct";
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
//tạo ms sản phẩm
    @PostMapping("/createProduct")
    public String createProduct(@ModelAttribute("productPage") ProductDTO productDTO, RedirectAttributes attributes) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        boolean check = iProductService.save(product);
        attributes.addFlashAttribute("mess", check);
        return ("redirect:/productCoffee/create");
    }

    //xoá sản phẩm
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        boolean check = iProductService.delete(id);
        redirectAttributes.addFlashAttribute("check", check);
        return "redirect:/productCoffee/listProduct";

    }

    //    chỉnh sửa sản phẩm
    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable(value = "id") Integer id, @RequestParam(value = "page", defaultValue = "0") Integer page, Model model) {
        ProductDTO productDTO = new ProductDTO();
        Product product = iProductService.findById(id);
        BeanUtils.copyProperties(product, productDTO);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("typePage", iTypeService.findAll(page));
        return "product/updateProduct";
    }

    //    chỉnh sửa sản phẩm
    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute("productDTO") ProductDTO productDTO, RedirectAttributes attributes) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        boolean check = iProductService.save(product);
        attributes.addFlashAttribute("flag", check);
        return "redirect:/productCoffee/listProduct";

    }

    //    hiển thị chi tiết 1 sản phẩm
    @GetMapping("/viewProduct/{id}")
    public String view(@PathVariable("id") Integer id, Model model) {
        ProductDTO productDTO = new ProductDTO();
        Product product = iProductService.findById(id);
        BeanUtils.copyProperties(product, productDTO);
        model.addAttribute("productPage", product);
        return "product/viewProduct";
    }

    //    tìm kiếm sản phẩm
    @PostMapping("/searchProduct")
    public String search(@RequestParam("name") String name, @RequestParam(value = "page", defaultValue = "0") Integer page, Model model) {
        Pageable pageable= PageRequest.of(page,5);
        Page<Product> productPage = iProductService.searchProduct(name, pageable);
        model.addAttribute("productPage", productPage);
        model.addAttribute("name",name);
        return "product/listAdminProduct";
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


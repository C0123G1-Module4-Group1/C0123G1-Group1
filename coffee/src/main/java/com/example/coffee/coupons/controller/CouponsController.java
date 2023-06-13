package com.example.coffee.coupons.controller;

import com.example.coffee.coupons.Service.ICouponsService;
import com.example.coffee.coupons.dto.CouponsDTO;
import com.example.coffee.coupons.model.Coupons;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;


@Controller
@RequestMapping("/coupons")
public class CouponsController {
    @Autowired
    private ICouponsService iCouponsService;

    @GetMapping("/list")
    public String showList(@RequestParam(value = "page", defaultValue = "0") Integer page, Model model, HttpServletResponse response) {
        Page<Coupons> couponsPage = iCouponsService.findAllCoupons(PageRequest.of(page, 5));
        model.addAttribute("couponsPage",couponsPage );
        model.addAttribute("statusSearch",false);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        return "/coupons/list";
    }

    @GetMapping("/create")
    public String showCouponsPage(Model model) {
        model.addAttribute("couponsDTO", new CouponsDTO());
        return "/coupons/create";
    }

    @PostMapping("/create_coupons")
    public String createCoupons(@Validated @ModelAttribute("couponsDTO")CouponsDTO couponsDTO,BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        new CouponsDTO().validate(couponsDTO,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/coupons/create";
        }
        Coupons coupons = new Coupons();
        BeanUtils.copyProperties(couponsDTO, coupons);
        boolean check = iCouponsService.createCoupons(coupons);
        redirectAttributes.addFlashAttribute("check1", check);
        return "redirect:/coupons/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCoupons(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        Boolean check = iCouponsService.deleteCoupons(id);
        redirectAttributes.addFlashAttribute("check2", check);
        return "redirect:/coupons/list";
    }

    @GetMapping("/update/{id}")
    public String editCoupons(@PathVariable("id") Integer id, Model model,HttpServletResponse response) {
        Coupons coupons = iCouponsService.findCoupons(id);
        CouponsDTO couponsDTO = new CouponsDTO();
        BeanUtils.copyProperties(coupons, couponsDTO);
        model.addAttribute("couponsDTO", couponsDTO);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        return "/coupons/edit";
    }

    @PostMapping("/update")
    public String updateCoupons(@Validated @ModelAttribute("couponsDTO") CouponsDTO couponsDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        new CouponsDTO().validate(couponsDTO,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/coupons/edit";
        }
        Coupons coupons = new Coupons();
        BeanUtils.copyProperties(couponsDTO, coupons);
        boolean check= iCouponsService.updateCoupon(coupons);
        redirectAttributes.addFlashAttribute("check3", check);
        return "redirect:/coupons/update/"+coupons.getId();
    }

    @GetMapping("/search")
    public String searchCoupons(@RequestParam("codeCoupons") String codeCoupons,
                                       @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Page<Coupons> couponsPage = iCouponsService.findAllCouponsByCodeCoupons('%'+codeCoupons+'%', PageRequest.of(page, 5));
        if(couponsPage.isEmpty()){
            model.addAttribute("searchMess","There is no data for searching");
        }
        int size = couponsPage.getTotalPages();
        model.addAttribute("size", size);
        model.addAttribute("couponsPage", couponsPage);
        model.addAttribute("codeCoupons",codeCoupons);
        model.addAttribute("statusSearch",true);
        return "/coupons/list";
    }
}

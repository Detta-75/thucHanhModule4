package com.example.thuchanh.controller;

import com.example.thuchanh.model.Promotion;
import com.example.thuchanh.service.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/")
public class PromotionController {
    @Autowired
    private IPromotionService promotionService;

    @GetMapping("")
    public ModelAndView showHome() {
        return new ModelAndView("home").addObject("promotions", promotionService.findAll());
    }

    @GetMapping("show-form-create")
    public String showFormCreate(Model model) {
        Promotion promotion = new Promotion();
        model.addAttribute("promotion", promotion);
        return "add_promotion";
    }

    @PostMapping("add-promotion")
    public String addPromotion(@ModelAttribute("promotion") Promotion promotion,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add_promotion";
        }

        if (promotion.getEndDate().isBefore(promotion.getStartDate().plusDays(1))) {
            bindingResult.rejectValue("endDate", "error.endDate",
                    "Ngày kết thúc phải lớn hơn ngày bắt đầu ít nhất 1 ngày.");
            return "add_promotion";
        }
        promotionService.save(promotion);
        redirectAttributes.addFlashAttribute("message", "Thêm khuyến mãi thành công.");
        return "redirect:/";
    }

    @GetMapping("/delete-promotion/{id}")
    public String deletePromotion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            promotionService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Xóa khuyến mãi thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Xóa khuyến mãi bị lỗi: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/sreach")
    public String listPromotions(@RequestParam(required = false) Integer discountAmount,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                 Model model) {
        List<Promotion> promotions = promotionService.searchPromotions(discountAmount, startDate, endDate);
        model.addAttribute("promotions", promotions);
        model.addAttribute("discountAmount", discountAmount);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "home";
    }

}

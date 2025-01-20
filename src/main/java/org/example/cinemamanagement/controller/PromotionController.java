package org.example.cinemamanagement.controller;

import jakarta.validation.Valid;
import org.example.cinemamanagement.model.Promotion;
import org.example.cinemamanagement.model.PromotionDTO;
import org.example.cinemamanagement.service.PromotionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public String listPromotions(Model model) {
        List<Promotion> promotions = promotionService.getAllPromotions();
        model.addAttribute("promotions", promotions);
        return "index";
    }

    @GetMapping("/add")
    public String showAddPromotionForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "add";
    }

    @PostMapping("/add")
    public String addPromotion(@Valid @ModelAttribute("promotion") PromotionDTO promotionDTO, BindingResult bindingResult, Model model) {
        new PromotionDTO().validate(promotionDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("promotion", promotionDTO);
            return "add";
        }
        Promotion promotion = new Promotion();
        BeanUtils.copyProperties(promotionDTO, promotion);
        promotionService.addPromotion(promotion);
        return "redirect:/promotions";
    }

    @GetMapping("/edit/{id}")
    public String showEditPromotionForm(@PathVariable Long id, Model model) {
        Promotion promotion = promotionService.getPromotionById(id).orElseThrow(() -> new IllegalArgumentException("Invalid promotion ID: " + id));
        model.addAttribute("promotion", promotion);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editPromotion(@PathVariable Long id, @ModelAttribute Promotion promotion) {
        promotion.setId(id);
        promotionService.addPromotion(promotion);
        return "redirect:/promotions";
    }

    @PostMapping("/delete/{id}")
    public String deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return "redirect:/promotions";
    }

    @GetMapping("/search")
    public String searchPromotions(@RequestParam(required = false) Double discount,
                                   @RequestParam(required = false) LocalDate startDate,
                                   @RequestParam(required = false) LocalDate endDate,
                                   Model model) {
        List<Promotion> promotions = promotionService.searchPromotions(discount, startDate, endDate);
        model.addAttribute("promotions", promotions);
        return "index";
    }
}

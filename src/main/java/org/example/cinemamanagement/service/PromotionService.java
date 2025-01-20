package org.example.cinemamanagement.service;

import org.example.cinemamanagement.model.Promotion;
import org.example.cinemamanagement.repository.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    @Autowired
    private IPromotionRepository promotionRepository;

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Optional<Promotion> getPromotionById(Long id) {
        return promotionRepository.findById(id);
    }

    public Promotion addPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    public List<Promotion> searchPromotions(Double discount, LocalDate startDate, LocalDate endDate) {
        return promotionRepository.searchPromotions(discount, startDate, endDate);
    }
}
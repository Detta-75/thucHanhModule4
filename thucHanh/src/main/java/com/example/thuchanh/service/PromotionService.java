package com.example.thuchanh.service;

import com.example.thuchanh.model.Promotion;
import com.example.thuchanh.repository.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {
    @Autowired
    private IPromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public void deleteById(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public List<Promotion> searchPromotions(Integer discountAmount, LocalDate startDate, LocalDate endDate) {
        return promotionRepository.searchPromotions(discountAmount, startDate, endDate);
    }
}

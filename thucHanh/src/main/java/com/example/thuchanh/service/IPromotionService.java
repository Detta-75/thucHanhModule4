package com.example.thuchanh.service;

import com.example.thuchanh.model.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface IPromotionService {
    List<Promotion> findAll();
    void save(Promotion promotion);
    void deleteById(Long id);
    List<Promotion> searchPromotions(Integer discountAmount, LocalDate startDate, LocalDate endDate);
}

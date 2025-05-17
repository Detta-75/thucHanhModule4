package com.example.thuchanh.repository;

import com.example.thuchanh.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IPromotionRepository extends JpaRepository<Promotion, Long> {
    @Query("SELECT p FROM Promotion p " +
            "WHERE (:discountAmount IS NULL OR p.discountAmount >= :discountAmount) " +
            "AND (:startDate IS NULL OR p.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR p.endDate <= :endDate)")
    List<Promotion> searchPromotions(@Param("discountAmount") Integer discountAmount,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);
}

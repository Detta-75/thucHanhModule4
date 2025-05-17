package com.example.thuchanh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "promotion")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tiêu đề không được để trống.")
    private String title;

    @NotNull(message = "Vui lòng chọn ngày bắt đầu.")
    @FutureOrPresent(message = "Ngày bắt đầu không được trong quá khứ.")
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull(message = "Vui lòng chọn ngày kết thúc.")
    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull(message = "Vui lòng nhập mức giảm giá.")
    @Min(value = 10000, message = "Mức giảm giá phải lớn hơn 10.000 VNĐ.")
    @Column(name = "discount_amount")
    private Integer discountAmount;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Promotion(Long id, String title, LocalDate startDate, LocalDate endDate, Integer discountAmount, String description) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountAmount = discountAmount;
        this.description = description;
    }

    public Promotion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

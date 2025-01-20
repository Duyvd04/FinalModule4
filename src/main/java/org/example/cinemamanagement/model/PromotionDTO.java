package org.example.cinemamanagement.model;

import jakarta.validation.constraints.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class PromotionDTO implements Validator {

    private Long id;

    @NotNull(message = "Tiêu đề bắt buộc")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters.")
    private String title;

    @NotNull(message = "Start date is required.")
    @FutureOrPresent(message = "Start date must be in the future or today.")
    private LocalDate startDate;

    @NotNull(message = "End date is required.")
    private LocalDate endDate;

    @NotNull(message = "Discount is required.")
    @DecimalMin(value = "10000", message = "Discount must be greater than 10,000 VND.")
    private double discount;

    @NotNull(message = "Details are required.")
    @Size(min = 1, message = "Details cannot be empty.")
    private String details;

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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PromotionDTO promotionDTO = (PromotionDTO) target;
        if (promotionDTO.getEndDate() != null && promotionDTO.getStartDate() != null) {
            if (promotionDTO.getEndDate().isBefore(promotionDTO.getStartDate().plusDays(1))) {
                errors.rejectValue("endDate", "EndDateInvalid", "End date must be at least 1 day after start date.");
            }
        }
    }
}
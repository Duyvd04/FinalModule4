package org.example.cinemamanagement.model;

import jakarta.validation.constraints.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class PromotionDTO implements Validator {

    private Long id;

    @NotNull(message = "Tiêu đề bắt buộc.")
    @Size(min = 1, max = 100, message = "Tiêu đề từ 1 đến 100 kí tự.")
    private String title;

    @NotNull(message = "Thời gian bắt đầu bắt buộc.")
    @FutureOrPresent(message = "Thời gian bắt đầu phải là ngày/tháng/năm và phải lớn hơn thời gian hiện tại.")
    private LocalDate startDate;

    @NotNull(message = "Thời gian kết thúc bắt buộc.")
    private LocalDate endDate;

    @NotNull(message = "Mức giảm giá bắt buộc")
    @DecimalMin(value = "10000", message = "Mức giảm giá phải lớn hơn 10.000 VNĐ ")
    private double discount;

    @NotNull(message = "Chi tiết bắt buộc")
    @Size(min = 1, message = "Chi tiết không được để trống.")
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
                errors.rejectValue("endDate", "EndDateInvalid", "Thời gian kết thúc phải là ngày/tháng/năm và lớn hơn thời gian bắt đầu ít nhất 1 ngày.");
            }
        }
    }
}
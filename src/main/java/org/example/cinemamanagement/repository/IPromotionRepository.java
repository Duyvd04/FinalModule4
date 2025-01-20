package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IPromotionRepository extends JpaRepository<Promotion, Long> {
    @Query("SELECT p FROM Promotion p WHERE (:discount IS NULL OR p.discount = :discount) AND (:startDate IS NULL OR p.startDate >= :startDate) AND (:endDate IS NULL OR p.endDate <= :endDate)")
    List<Promotion> searchPromotions(@Param("discount") Double discount, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
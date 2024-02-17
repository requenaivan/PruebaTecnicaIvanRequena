package com.inditex.zwift.repository;

import com.inditex.zwift.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository to entity product
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select *  from product where " +
            "product_id = :productId and brand_id = :brandId " +
            "and (:applicationDate between start_date and end_date)", nativeQuery = true)
    List<Product> findByProductIdAndBrandIdAndApplicationDate(Integer productId, LocalDateTime applicationDate,Integer brandId);
}

package com.inditex.zwift.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity to managment products
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    private Long id;

    private Long productId;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private Long priceList;

    private Integer priority;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "curr")
    private Currency currency;

    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime endDate;
    public Product() {
    }

    public Product(Long id, Long productId, Brand brand, Long priceList, Integer priority, BigDecimal price, Currency currency, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.productId = productId;
        this.brand = brand;
        this.priceList = priceList;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

package com.inditex.mordor.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceDTO {

    public Long productId;

    public Integer priceList;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    public LocalDateTime startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    public LocalDateTime endDate;

    public BigDecimal price;

    public String brandId;

    public PriceDTO(Long productId, Integer priceList, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price, String brandId) {
        this.productId = productId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.brandId = brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}

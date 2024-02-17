package response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dto to response service get prices
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceResponseDTO {
    /**
     * Product id to entity
     */
    public Long productId;

    /**
     * priceList to entity
     */
    public Integer priceList;

    /**
     * Start Date to price
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    public LocalDateTime startDate;

    /**
     * End date to price
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
    public LocalDateTime endDate;

    /**
     * price to product
     */
    public BigDecimal price;

    /**
     * Brand id to product
     */
    public String brandId;

    /**
     * Brand name to product
     */
    public String brandName;

    /**
     * Currency to product
     */
    public String currency;

    /**
     * Currency Name to product
     */
    public String currencyName;

    public PriceResponseDTO(Long productId, Integer priceList, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price, String brandId, String brandName, String currency, String currencyName) {
        this.productId = productId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.brandId = brandId;
        this.brandName = brandName;
        this.currency = currency;
        this.currencyName = currencyName;
    }

    public PriceResponseDTO() {

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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}

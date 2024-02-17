package com.inditex.mordor.core.manager;

import com.inditex.mordor.client.PriceMsClient;
import com.inditex.mordor.common.exception.ErrorCode;
import com.inditex.mordor.common.exception.InditexApiException;
import com.inditex.mordor.common.response.PriceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceManager {

    private final PriceMsClient priceMsClient;
    public PriceManager(PriceMsClient priceMsClient){
        this.priceMsClient = priceMsClient;
    }

    public PriceDTO getPricesByFilter(Integer productId, LocalDateTime applicationDate, Integer brandId) {
        Optional<PriceDTO> price = priceMsClient.getPricesByFilter(productId, applicationDate, brandId);
        return price.orElseThrow(() -> new InditexApiException(
                ErrorCode.PRODUCT_NOT_FOUND,
                HttpStatus.NOT_FOUND,
                String.format("Product not found with parameters ProductId: [%s] " +
                        "ApplicationDate: [%s], BrandId: [%s]", productId, applicationDate, brandId)
        ));
    }
}

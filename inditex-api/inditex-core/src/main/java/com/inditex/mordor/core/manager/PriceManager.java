package com.inditex.mordor.core.manager;

import com.inditex.mordor.client.PriceMsClient;
import com.inditex.mordor.common.exception.Error;
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
        validateParametrs(productId, applicationDate, brandId);
        Optional<PriceDTO> price = priceMsClient.getPricesByFilter(productId, applicationDate, brandId);
        return price.orElseThrow(() -> new InditexApiException(
                Error.PRODUCT_NOT_FOUND.getCode(),
                HttpStatus.NOT_FOUND,
                String.format(Error.PRODUCT_NOT_FOUND.getName(), productId, applicationDate, brandId)
        ));
    }

    private void validateParametrs(Integer productId, LocalDateTime applicationDate, Integer brandId) {
        if(productId == null) {
            throw new InditexApiException(
                    Error.PRODUCT_ID_IS_REQUIRED.getCode(),
                    HttpStatus.BAD_REQUEST,
                    Error.PRODUCT_ID_IS_REQUIRED.getName()
            );
        }

        if(applicationDate == null) {
            throw new InditexApiException(
                    Error.APPLICATION_DATE_IS_REQUIRED.getCode(),
                    HttpStatus.BAD_REQUEST,
                    Error.APPLICATION_DATE_IS_REQUIRED.getName()
            );
        }

        if(brandId == null) {
            throw new InditexApiException(
                    Error.BRAND_ID_IS_REQUIRED.getCode(),
                    HttpStatus.BAD_REQUEST,
                    Error.BRAND_ID_IS_REQUIRED.getName()
            );
        }
    }
}

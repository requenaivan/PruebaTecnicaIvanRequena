package com.inditex.mordor.client;

import com.inditex.mordor.client.configuration.FeignAuthConfiguration;
import com.inditex.mordor.client.configuration.FeignConfiguration;
import com.inditex.mordor.common.constant.ExternalRoute;
import com.inditex.mordor.common.response.PriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@FeignClient(name = ExternalRoute.CLIENT_NAME_PRICE,
        url = "${microservices.inditex-ms.base-url}",
        configuration = {FeignConfiguration.class, FeignAuthConfiguration.class})
public interface PriceMsClient {

    @GetMapping(value = {ExternalRoute.ENDPOINT_PRICES})
    Optional<PriceDTO> getPricesByFilter(
            @RequestParam(value = "product_id", required = false) Integer productId,
            @RequestParam(value = "application_date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime applicationDate,
            @RequestParam(value = "brand_id", required = false) Integer brandId);
}

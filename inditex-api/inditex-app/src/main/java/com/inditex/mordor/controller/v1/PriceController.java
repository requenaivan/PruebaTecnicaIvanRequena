package com.inditex.mordor.controller.v1;

import com.inditex.mordor.common.constant.Route;
import com.inditex.mordor.common.response.PriceDTO;
import com.inditex.mordor.core.manager.PriceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
public class PriceController {
    private Logger logger = LoggerFactory.getLogger(PriceController.class);

    private PriceManager priceManager;

    public PriceController(PriceManager priceManager) {
        this.priceManager = priceManager;
    }

    /**
     * Endpoint para la consulta de los datos del modelo por fecha de aplicación,
     *  identificador de producto, cadena y hora de consulta
     * @param productId id de producto
     * @param applicationDate fecha de aplicacion
     * @param brandId id de la marca
     * @return PriceDTO
     */
    @GetMapping(value = {Route.PRICES})
    private PriceDTO getPrices(
            @RequestParam(value = "product_id", required = true) Integer productId,
            @RequestParam(value = "application_date", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime applicationDate,
            @RequestParam(value = "brand_id", required = true) Integer brandId){
        logger.info("PriceController:getPrices --productId:{} --applicationDate:{} " +
                        "--brandId:{}", productId, applicationDate, brandId);
        return priceManager.getPricesByFilter(productId, applicationDate, brandId);
    }
}

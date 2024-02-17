package com.inditex.zwift.manager;

import com.inditex.zwift.model.Product;
import com.inditex.zwift.repository.ProductRepository;
import mapper.PriceMapper;
import org.springframework.stereotype.Service;
import response.PriceResponseDTO;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * Class to managment business logic to inditex-ms
 */
@Service
public class PriceManager {

    private final ProductRepository productRepository;
    public PriceManager(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public PriceResponseDTO getPricesByFilter(Integer productId, LocalDateTime applicationDate, Integer brandId) {
        List<Product> products = productRepository.
                findByProductIdAndBrandIdAndApplicationDate(productId, applicationDate,  brandId);

        return products.stream()
                .max(Comparator.comparing(Product::getPriority)).
                map(PriceMapper::mapPriceDTO)
                .orElse(null);
    }
}

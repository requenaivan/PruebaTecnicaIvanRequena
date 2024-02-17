package mapper;

import com.inditex.zwift.model.Product;
import response.PriceResponseDTO;

/**
 * Mapper entity to DTO
 */
public class PriceMapper {

    /**
     * Mapper entity to DTO
     * @param product entity with result query
     * @return PRICEDTO mapped
     */
    public static PriceResponseDTO mapPriceDTO(Product product) {
        return new PriceResponseDTO(
                product.getProductId(),
                product.getPriceList().intValue(),
                product.getStartDate(),
                product.getEndDate(),
                product.getPrice(),
                product.getBrand().getId().toString(),
                product.getBrand().getName(),
                product.getCurrency().getIso(),
                product.getCurrency().getName()
        );
    }
}

package ra.bai2.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.bai2.dto.request.ProductRequest;
import ra.bai2.dto.response.ProductPutResponse;
import ra.bai2.model.Product;
import ra.bai2.repository.CategoriesRepository;

@Component
public class ProductPutMapper implements GenericMapper<Product, ProductRequest, ProductPutResponse>{
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Override
    public Product mapperRequestToEntity(ProductRequest productRequest) {
        return Product.builder().id(productRequest.getId())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .title(productRequest.getTitle())
                .description(productRequest.getDescription())
                .catalog(categoriesRepository.findById(productRequest.getCatalogId()).get())
                .status(productRequest.isStatus())
                .build();
    }

    @Override
    public ProductPutResponse mapperEntityToResponse(Product product) {
        return new ProductPutResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getTitle(),
                product.getDescription(),
                product.getCatalog(),
                product.getUpdated()
        );
    }
}

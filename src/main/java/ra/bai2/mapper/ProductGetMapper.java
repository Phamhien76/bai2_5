package ra.bai2.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.bai2.dto.request.ProductRequest;
import ra.bai2.dto.response.ProductGetResponse;
import ra.bai2.dto.response.ProductPostResponse;
import ra.bai2.dto.response.ProductPutResponse;
import ra.bai2.model.Product;
import ra.bai2.repository.CategoriesRepository;

@Component
public class ProductGetMapper implements GenericMapper<Product, ProductRequest, ProductGetResponse>{
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
    public ProductGetResponse mapperEntityToResponse(Product product) {
     return ProductGetResponse.builder()
             .id(product.getId())
             .name(product.getName())
             .price(product.getPrice())
             .title(product.getTitle())
             .description(product.getDescription())
             .catalogId(product.getCatalog().getId())
             .catalogName(product.getCatalog().getName())
             .status(product.isStatus()).build();
    }
}

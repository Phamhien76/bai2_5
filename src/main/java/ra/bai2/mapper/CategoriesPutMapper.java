package ra.bai2.mapper;

import org.springframework.stereotype.Component;
import ra.bai2.dto.request.CategoriesPutRequest;
import ra.bai2.dto.response.CategoriesResponse;
import ra.bai2.model.Categories;

@Component
public class CategoriesPutMapper implements GenericMapper<Categories, CategoriesPutRequest, CategoriesResponse>{

    @Override
    public Categories mapperRequestToEntity(CategoriesPutRequest categoriesPutRequest) {
        return Categories.builder().id(categoriesPutRequest.getId())
                .name(categoriesPutRequest.getName())
                .priority(categoriesPutRequest.getPriority())
                .description(categoriesPutRequest.getDescription())
                .status(categoriesPutRequest.isStatus()).build();
    }

    @Override
    public CategoriesResponse mapperEntityToResponse(Categories categories) {
        return new CategoriesResponse(
                categories.getId(),
                categories.getName(),
                categories.getPriority(),
                categories.getDescription(),
                categories.isStatus()
        );
    }
}

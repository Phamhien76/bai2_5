package ra.bai2.mapper;

import org.springframework.stereotype.Component;
import ra.bai2.dto.request.CategoriesPatchRequest;
import ra.bai2.dto.response.CategoriesResponse;
import ra.bai2.model.Categories;
@Component
public class CategoriesPatchMapper implements GenericMapper<Categories, CategoriesPatchRequest, CategoriesResponse>{
    @Override
    public Categories mapperRequestToEntity(CategoriesPatchRequest categoriesPatchRequest) {
        return Categories.builder().id(categoriesPatchRequest.getId())
                .status(categoriesPatchRequest.isStatus()).build();
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

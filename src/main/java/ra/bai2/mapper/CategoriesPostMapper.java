package ra.bai2.mapper;

import org.springframework.stereotype.Component;
import ra.bai2.dto.request.CategoriesPostRequest;
import ra.bai2.dto.response.CategoriesResponse;
import ra.bai2.model.Categories;

import java.util.Date;

@Component
public class CategoriesPostMapper implements GenericMapper<Categories, CategoriesPostRequest, CategoriesResponse>{

    @Override
    public Categories mapperRequestToEntity(CategoriesPostRequest categoriesPostRequest) {
        return Categories.builder().name(categoriesPostRequest.getName())
                .priority(categoriesPostRequest.getPriority())
                .description(categoriesPostRequest.getDescription())
                .created(new Date())
                .status(true).build();
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

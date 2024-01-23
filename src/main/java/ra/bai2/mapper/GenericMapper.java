package ra.bai2.mapper;

import ra.bai2.dto.response.ComboBoxRP;
import ra.bai2.dto.response.ProductGetResponse;
import ra.bai2.model.Categories;
import ra.bai2.model.Product;

public interface GenericMapper<E,R,S> {
    E mapperRequestToEntity(R r);
    S mapperEntityToResponse(E e);
}

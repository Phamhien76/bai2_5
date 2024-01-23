package ra.bai2.service;

import ra.bai2.dto.request.CategoriesPatchRequest;
import ra.bai2.dto.request.CategoriesPostRequest;
import ra.bai2.dto.request.CategoriesPutRequest;
import ra.bai2.dto.request.ProductRequest;
import ra.bai2.dto.response.CategoriesResponse;
import ra.bai2.dto.response.ProductGetResponse;
import ra.bai2.dto.response.ProductPostResponse;
import ra.bai2.dto.response.ProductPutResponse;
import ra.bai2.exception.CustomException;

import java.util.List;

public interface ProductService {
    List<ProductGetResponse> findAll(int page, int size, String direction, String orderBy);
   ProductGetResponse findById(String id);
    ProductPostResponse save(ProductRequest productRequest) throws CustomException;
    ProductPutResponse update(ProductRequest productRequest) throws CustomException;
   boolean delete(String id);
   List<ProductGetResponse> findAllByCatalogId(long id);




}

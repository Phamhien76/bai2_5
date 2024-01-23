package ra.bai2.service;

import ra.bai2.dto.request.CategoriesPatchRequest;
import ra.bai2.dto.request.CategoriesPostRequest;
import ra.bai2.dto.request.CategoriesPutRequest;
import ra.bai2.dto.response.CategoriesResponse;
import ra.bai2.exception.CustomException;

import java.util.List;

public interface CategoriesService {
    List<CategoriesResponse> findAll(int page, int size,String direction, String orderBy);
    CategoriesResponse save(CategoriesPostRequest categoriesPostRequest) throws CustomException;
    CategoriesResponse update(CategoriesPutRequest categoriesPutRequest) throws CustomException;
    CategoriesResponse updateStatus(CategoriesPatchRequest categoriesPatchRequest);
    CategoriesResponse findById(long id);
    List<CategoriesResponse> findAllByStatus();
}

package ra.bai2.service;

import ra.bai2.dto.request.*;
import ra.bai2.dto.response.CategoriesResponse;
import ra.bai2.dto.response.UserResponse;
import ra.bai2.exception.CustomException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, Object> findAll(int page, int size);
    UserResponse save(UserPostRequest userPostRequest) throws CustomException;
    UserResponse update(UserPutRequest userPutRequest) throws CustomException;
    UserResponse updateStatus(UserPatchRequest userPatchRequest);
    Map<String, Object> findByNameOrCreate(int page, int size, String name, String create, String direction, String orderBy);
    }

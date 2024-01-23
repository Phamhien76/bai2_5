package ra.bai2.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.bai2.dto.request.UserPatchRequest;
import ra.bai2.dto.request.UserPostRequest;
import ra.bai2.dto.request.UserPutRequest;
import ra.bai2.dto.response.CategoriesResponse;
import ra.bai2.dto.response.UserResponse;
import ra.bai2.exception.CustomException;
import ra.bai2.mapper.UserPatchMapper;
import ra.bai2.mapper.UserPostMapper;
import ra.bai2.mapper.UserPutMapper;
import ra.bai2.model.Categories;
import ra.bai2.model.User;
import ra.bai2.repository.UserRepository;
import ra.bai2.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPatchMapper userPatchMapper;
    @Autowired
    private UserPostMapper userPostMapper;
    @Autowired
    private UserPutMapper userPutMapper;
    @Override
    public Map<String, Object> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> pageUser = userRepository.findAll(pageable);
        int totalPage = pageUser.getTotalPages();
        List<User> listUser = pageUser.getContent();
        List<UserResponse> listUserResponse =  listUser.stream()
                .map(user -> userPostMapper.mapperEntityToResponse(user))
                .collect(Collectors.toList());
        Map<String, Object> data = new HashMap<>();
        data.put("totalPage",totalPage);
        data.put("user",listUserResponse);
        return data;
    }

    @Override
    public UserResponse save(UserPostRequest userPostRequest) throws CustomException {
        if (userRepository.existsByName(userPostRequest.getName())){
            throw new CustomException("UserName đã tồn tại vui lòng nhập lại");
        }
        User user = userRepository.save(userPostMapper.mapperRequestToEntity(userPostRequest));
        return userPostMapper.mapperEntityToResponse(user);
    }

    @Override
    public UserResponse update(UserPutRequest userPutRequest) throws CustomException {
        if (userRepository.existsByName(userPutRequest.getName())){
            throw new CustomException("UserName đã tồn tại vui lòng nhập lại");
        }
        boolean checkExit = userRepository.existsById(userPutRequest.getId());
        if (checkExit){
            User user = userRepository.save(userPutMapper.mapperRequestToEntity(userPutRequest));
            return userPutMapper.mapperEntityToResponse(user);
        }
        return null;
    }

    @Override
    public UserResponse updateStatus(UserPatchRequest userPatchRequest) {
        boolean checkExit = userRepository.existsById(userPatchRequest.getId());
        if (checkExit){
            User user = userRepository.save(userPatchMapper.mapperRequestToEntity(userPatchRequest));
            return userPatchMapper.mapperEntityToResponse(user);
        }
        return null;
    }

    @Override
    public Map<String, Object> findByNameOrCreate(int page, int size, String name, String create, String direction, String orderBy) {
        //Xử lý phần sắp xếp
        List<Sort.Order> listOrder = new ArrayList<>();
        Sort.Order orderName;
        Sort.Order orderCreate;
        // xủa lý sắp xếp theo tên
        if (orderBy.equals("name")){
            if (direction.equals("ASC")){
                orderName = new Sort.Order(Sort.Direction.ASC, "name");
            }else {
                orderName = new Sort.Order(Sort.Direction.DESC, "name");
            }
            listOrder.add(orderName);
        }else {
            if (direction.equals("ASC")){
                orderCreate = new Sort.Order(Sort.Direction.ASC, "create");
            }else {
                orderCreate = new Sort.Order(Sort.Direction.DESC, "create");
            }
            listOrder.add(orderCreate);
        }
        Pageable pageable = PageRequest.of(page,size,Sort.by(listOrder));

        Date dateNew = null;
        try {
            dateNew = new SimpleDateFormat("yyyy-MM-dd").parse(create);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Page<User> pageUser = userRepository.findByNameContainsOrCreated(name,dateNew,pageable);
        int totalPage = pageUser.getTotalPages();
        List<User> listUser = pageUser.getContent();
        List<UserResponse> listUserResponse =  listUser.stream()
                .map(user -> userPostMapper.mapperEntityToResponse(user))
                .collect(Collectors.toList());
        Map<String, Object> data = new HashMap<>();
        data.put("totalPage",totalPage);
        data.put("user",listUserResponse);
        return data;
 }
}

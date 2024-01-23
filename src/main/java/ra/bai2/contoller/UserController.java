package ra.bai2.contoller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.bai2.dto.request.UserPostRequest;
import ra.bai2.dto.request.UserPutRequest;
import ra.bai2.dto.response.ProductGetResponse;
import ra.bai2.dto.response.UserResponse;
import ra.bai2.exception.CustomException;
import ra.bai2.model.User;
import ra.bai2.repository.UserRepository;
import ra.bai2.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/ecommerce/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<Map<String,Object>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        return new ResponseEntity<>(userService.findAll(page, size), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserPostRequest userPostRequest) throws CustomException {
        UserResponse userResponse = userService.save(userPostRequest);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserPutRequest userPutRequest) throws CustomException {
        UserResponse userResponse = userService.update(userPutRequest);
        return  new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    @GetMapping("/findByNameOrCreate")
    public ResponseEntity<Map<String,Object>> findByNameOrCreate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String create,
            @RequestParam(defaultValue = "ACS") String direction,
            @RequestParam(defaultValue = "name") String orderBy){
        return  new ResponseEntity<>(userService.findByNameOrCreate(page, size, name, create, direction, orderBy),HttpStatus.OK);

    }
}

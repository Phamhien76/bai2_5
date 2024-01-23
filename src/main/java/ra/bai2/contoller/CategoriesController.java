package ra.bai2.contoller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.bai2.dto.request.CategoriesPatchRequest;
import ra.bai2.dto.request.CategoriesPostRequest;
import ra.bai2.dto.request.CategoriesPutRequest;
import ra.bai2.dto.response.CategoriesResponse;
import ra.bai2.dto.response.Message;
import ra.bai2.exception.CustomException;
import ra.bai2.service.CategoriesService;

import java.util.List;

@RestController
@RequestMapping(path = "/ecommerce/api/v1/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<List<CategoriesResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ASC")String direction,
            @RequestParam(defaultValue = "name")String orderBy
    ){
        List<CategoriesResponse> listCategories = categoriesService.findAll(page, size, direction, orderBy);
        return new ResponseEntity<>(listCategories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriesResponse> save(@Valid @RequestBody CategoriesPostRequest categoriesPostRequest) throws CustomException {
        CategoriesResponse categoriesResponse = categoriesService.save(categoriesPostRequest);
        return new ResponseEntity<>(categoriesResponse,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CategoriesPutRequest categoriesPutRequest) throws CustomException {
        CategoriesResponse categoriesResponse = categoriesService.update(categoriesPutRequest);
        if (categoriesResponse== null){
            return  new ResponseEntity<>(new Message("Id not found"),HttpStatus.NOT_FOUND);
        }else {
            return  new ResponseEntity<>(categoriesResponse,HttpStatus.OK);
        }
    }

    @PatchMapping
    public ResponseEntity<?>updateStatus(@RequestBody CategoriesPatchRequest categoriesPatchRequest){
        CategoriesResponse categoriesResponse = categoriesService.updateStatus(categoriesPatchRequest);
        if (categoriesResponse== null){
            return  new ResponseEntity<>(new Message("Id not found"),HttpStatus.NOT_FOUND);
        }else {
            return  new ResponseEntity<>(categoriesResponse,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable long id){
        CategoriesResponse categoriesResponse = categoriesService.findById(id);
        if (categoriesResponse== null){
            return  new ResponseEntity<>(new Message("Id not found"),HttpStatus.NOT_FOUND);
        }else {
            return  new ResponseEntity<>(categoriesResponse,HttpStatus.OK);
        }
    }

    @GetMapping("/status")
    public  ResponseEntity<?> findAllByStatus(){
        List<CategoriesResponse> listCategories = categoriesService.findAllByStatus();
        return new ResponseEntity<>(listCategories,HttpStatus.OK);
    }

}

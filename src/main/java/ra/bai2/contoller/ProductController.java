package ra.bai2.contoller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.bai2.dto.request.ProductRequest;
import ra.bai2.dto.response.Message;
import ra.bai2.dto.response.ProductGetResponse;
import ra.bai2.dto.response.ProductPostResponse;
import ra.bai2.dto.response.ProductPutResponse;
import ra.bai2.exception.CustomException;
import ra.bai2.model.Categories;
import ra.bai2.service.CategoriesService;
import ra.bai2.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = "ecommerce/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public ResponseEntity<List<ProductGetResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "name") String orderBy) {

        List<ProductGetResponse> listProduct = productService.findAll(page, size, direction, orderBy);
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        ProductGetResponse productGetResponse = productService.findById(id);
        if (productGetResponse == null) {
            return new ResponseEntity<>(new Message("Id not pound"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productGetResponse, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<ProductPostResponse> save(@Valid @RequestBody ProductRequest productRequest) throws CustomException {
        ProductPostResponse productPostResponse = productService.save(productRequest);
        return new ResponseEntity<>(productPostResponse,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductRequest productRequest) throws CustomException {
        ProductPutResponse productPutResponse = productService.update(productRequest);
        if (productPutResponse == null) {
            return new ResponseEntity<>(new Message("Id not pound"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productPutResponse, HttpStatus.OK);
        }

    }
@DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable String id){
        boolean result = productService.delete(id);
        if (result){
            return new ResponseEntity<>(new Message("Đã xóa thành công"),HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(new Message("Id not found"), HttpStatus.NOT_FOUND);
        }
    }
@GetMapping("/catalog/{id}")
    public ResponseEntity<?> findAlByCatalog(@PathVariable long id){
        List<ProductGetResponse> listProduct = productService.findAllByCatalogId(id);
        return new ResponseEntity<>(listProduct,HttpStatus.OK);
    }
}

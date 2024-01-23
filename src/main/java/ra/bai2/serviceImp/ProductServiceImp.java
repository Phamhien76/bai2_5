package ra.bai2.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.bai2.dto.request.ProductRequest;
import ra.bai2.dto.response.ProductGetResponse;
import ra.bai2.dto.response.ProductPostResponse;
import ra.bai2.dto.response.ProductPutResponse;
import ra.bai2.exception.CustomException;
import ra.bai2.mapper.ProductGetMapper;
import ra.bai2.mapper.ProductPostMapper;
import ra.bai2.mapper.ProductPutMapper;
import ra.bai2.model.Product;
import ra.bai2.repository.ProductRepository;
import ra.bai2.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductGetMapper productGetMapper;
    @Autowired
    private ProductPostMapper productPostMapper;
    @Autowired
    private ProductPutMapper productPutMapper;
    @Override
    public List<ProductGetResponse> findAll(int page, int size, String direction, String orderBy) {
        Pageable pageable;
        if (direction.equals("ASC")){
            pageable = PageRequest.of(page, size, Sort.by(orderBy).descending());
        }else {
            pageable = PageRequest.of(page,size,Sort.by(orderBy).descending());
        }
        List<Product> listProduct = productRepository.findAll(pageable).getContent();
        return listProduct.stream().map(product -> productGetMapper.mapperEntityToResponse(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductGetResponse findById(String id) {
        Optional<Product> otpProduct = productRepository.findById(id);
        if (otpProduct.isPresent()){
            return productGetMapper.mapperEntityToResponse(otpProduct.get());
        }
        return null;
    }

    @Override
    public ProductPostResponse save(ProductRequest productRequest) throws CustomException {
        if(productRepository.existsByName(productRequest.getName())){
            throw new CustomException("Tên sản phẩm đã tồn tại vui lòng nhập lại");
        }
        Product product = productRepository.save(productPostMapper.mapperRequestToEntity(productRequest));
        return productPostMapper.mapperEntityToResponse(product);
    }

    @Override
    public ProductPutResponse update(ProductRequest productRequest) throws CustomException {
        if(productRepository.existsByName(productRequest.getName())){
            throw new CustomException("Tên sản phẩm đã tồn tại vui lòng nhập lại");
        }
        boolean checkExist = productRepository.existsById(productRequest.getId());
        if (checkExist){
            Product product = productRepository.save(productPutMapper.mapperRequestToEntity(productRequest));
            return  productPutMapper.mapperEntityToResponse(product);
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        boolean checkExist = productRepository.existsById(id);
        if (checkExist){
            productRepository.deleteById(id);
        }
        return checkExist;
    }

    @Override
    public List<ProductGetResponse> findAllByCatalogId(long id) {
        List<Product> listProduct = productRepository.findAllByCatalog_Id(id);
        return listProduct.stream().map(product -> productGetMapper.mapperEntityToResponse(product))
                .collect(Collectors.toList());
    }
}

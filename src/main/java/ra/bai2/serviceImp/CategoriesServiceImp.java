package ra.bai2.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.bai2.dto.request.CategoriesPatchRequest;
import ra.bai2.dto.request.CategoriesPostRequest;
import ra.bai2.dto.request.CategoriesPutRequest;
import ra.bai2.dto.response.CategoriesResponse;
import ra.bai2.exception.CustomException;
import ra.bai2.mapper.CategoriesPatchMapper;
import ra.bai2.mapper.CategoriesPostMapper;
import ra.bai2.mapper.CategoriesPutMapper;
import ra.bai2.model.Categories;
import ra.bai2.repository.CategoriesRepository;
import ra.bai2.service.CategoriesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImp implements CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private CategoriesPutMapper categoriesPutMapper;
    @Autowired
    private CategoriesPostMapper categoriesPostMapper;
    @Autowired
    private CategoriesPatchMapper categoriesPatchMapper;
    @Override
    public List<CategoriesResponse> findAll(int page, int size,String direction, String orderBy) {
        Pageable pageable;
        if (direction.equals("ASC")){
            pageable = PageRequest.of(page,size, Sort.by(orderBy).descending());
        }else {
            pageable = PageRequest.of(page,size,Sort.by(orderBy).descending());
        }
        List<Categories> listCategories = categoriesRepository.findAll(pageable).getContent();
        return listCategories.stream().map(categories -> categoriesPatchMapper.mapperEntityToResponse(categories))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriesResponse save(CategoriesPostRequest categoriesPostRequest) throws CustomException {
        if (categoriesRepository.existsByName(categoriesPostRequest.getName())){
            throw new CustomException("CatalogName đã tồn tại, vui lòng nhập lại");
        }
        Categories categories = categoriesRepository.save(categoriesPostMapper.mapperRequestToEntity(categoriesPostRequest));
        return categoriesPostMapper.mapperEntityToResponse(categories);
    }

    @Override
    public CategoriesResponse update(CategoriesPutRequest categoriesPutRequest) throws CustomException {
        if (categoriesRepository.existsByName(categoriesPutRequest.getName())){
            throw new CustomException("CatalogName đã tồn tại, vui lòng nhập lại");
        }
        boolean checkExist = categoriesRepository.existsById(categoriesPutRequest.getId());
        if (checkExist){
            Categories categories = categoriesRepository.save(categoriesPutMapper.mapperRequestToEntity(categoriesPutRequest));
            return categoriesPutMapper.mapperEntityToResponse(categories);
        }
        return null;
    }

    @Override
    public CategoriesResponse updateStatus(CategoriesPatchRequest categoriesPatchRequest) {
        boolean checkExist = categoriesRepository.existsById(categoriesPatchRequest.getId());
        if (checkExist){
            Categories categories = categoriesRepository.findById(categoriesPatchRequest.getId()).get();            categories.setStatus(categoriesPatchRequest.isStatus());

            return categoriesPatchMapper.mapperEntityToResponse(categoriesRepository.save(categories));
        }
        return null;
    }

    @Override
    public CategoriesResponse findById(long id) {
        Optional<Categories> otpCatalog = categoriesRepository.findById(id);
        if (otpCatalog.isPresent()){
            return categoriesPostMapper.mapperEntityToResponse(otpCatalog.get());
        }
        return null;
    }

    @Override
    public List<CategoriesResponse> findAllByStatus() {
        List<Categories> listCategories = categoriesRepository.findAllByStatus(true);
       return listCategories.stream()
               .map(categories -> categoriesPostMapper.mapperEntityToResponse(categories))
               .collect(Collectors.toList());
    }
}

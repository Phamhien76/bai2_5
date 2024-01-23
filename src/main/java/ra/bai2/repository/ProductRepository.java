package ra.bai2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.bai2.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByName(String name);
    List<Product> findAllByCatalog_Id(long id);

}

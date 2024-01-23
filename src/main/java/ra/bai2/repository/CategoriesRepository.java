package ra.bai2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.bai2.model.Categories;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    List<Categories> findAllByStatus(boolean status);
    boolean existsByName(String name);
}

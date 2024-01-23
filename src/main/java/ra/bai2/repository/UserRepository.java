package ra.bai2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.bai2.model.User;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByName(String name);
    Page<User> findByNameContainsOrCreated(String name, Date create, Pageable pageable);
}

package ra.bai2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private long id;
    @Column(name = "catalog_name",columnDefinition = "varchar(100)",nullable = false,unique = true)
    private String name;
    private int priority;
    @Column(columnDefinition = "text")
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date created;
    @Column(name = "catalog_status")
    private boolean status;
    @OneToMany(mappedBy = "catalog")
    @JsonIgnore
    private List<Product> listProduct = new ArrayList<>();
}

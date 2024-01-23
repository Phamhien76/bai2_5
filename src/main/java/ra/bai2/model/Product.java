package ra.bai2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @Column(name = "product_id",columnDefinition = "char(5)")
    private String id;
    @Column(name = "product_name",columnDefinition = "varchar(100)",nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private float price;
    @Column(columnDefinition = "varchar(200)",nullable = false)
    private String title;
    @Column(columnDefinition = "text",nullable = false)
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date updated;
    @ManyToOne
    @JoinColumn(name = "catalog_id",referencedColumnName = "catalog_id")
    private Categories catalog;
    @Column(name = "product_status")
    private boolean status;
}

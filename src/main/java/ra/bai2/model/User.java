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
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "user_name", columnDefinition = "varchar(100)",nullable = false,unique = true)
    private String name;
    private String email;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date created;
    private String phone;
    @JsonIgnore
    private boolean permission=true;
    @Column(name = "user_status")
    private boolean status=true;
}

package bg.softuni.mobilele.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{


    @Column(name = "is_active")
    private boolean isActive;

    private LocalDate created;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "last_name")
    private String lastName;

    private LocalDateTime modified;

    private String password;
    @Column(nullable = false, unique = true)
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private UserRoleEntity name;

    /*
    @PrePersist
    protected void onCreate() {
        this.created = this.modified = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modified = LocalDateTime.now();
    }
     */
}

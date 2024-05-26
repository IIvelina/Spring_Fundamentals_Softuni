package bg.softuni.mobilele.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    private LocalDateTime created;

    private LocalDateTime modified;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ModelEntity> models;
    // getters and setters

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

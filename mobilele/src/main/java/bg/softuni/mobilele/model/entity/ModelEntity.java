package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.entity.enums.CategoryEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity{


    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    private LocalDateTime created;

    @Column(name = "end_year")
    private int endYear;

    @Column(name = "image_url")
    private String imageUrl;

    private LocalDateTime modified;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "start_year")
    private int startYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

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

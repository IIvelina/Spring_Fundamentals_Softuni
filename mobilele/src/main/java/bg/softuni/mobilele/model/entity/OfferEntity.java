package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.entity.enums.EngineEnum;
import bg.softuni.mobilele.model.entity.enums.TransmissionEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{


    private LocalDateTime created;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private EngineEnum engine;
    @Column(name = "image_url")
    private String imageUrl;

    private int mileage;

    private LocalDateTime modified;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    private int year;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private ModelEntity model;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private UserEntity seller;

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

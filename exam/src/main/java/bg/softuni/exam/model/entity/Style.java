package bg.softuni.exam.model.entity;

import bg.softuni.exam.model.enums.StyleNameEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleNameEnum name;
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Painting> paintings;

    public Style() {
        this.paintings = new HashSet<>();
    }

    public StyleNameEnum getName() {
        return name;
    }

    public void setName(StyleNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Painting> getPaintings() {
        return paintings;
    }

    public void setPaintings(Set<Painting> paintings) {
        this.paintings = paintings;
    }
}

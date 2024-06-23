package bg.softuni.exam.model.serviceModel;

import bg.softuni.exam.model.entity.User;
import bg.softuni.exam.model.enums.StyleNameEnum;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

public class PaintingServiceModel {

    private Long id;
    private String name;

    private String author;
    private String image;
    private boolean isFavorite;

    private int votes;

    private StyleNameEnum style;

    private User owner;

    public PaintingServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public StyleNameEnum getStyle() {
        return style;
    }

    public void setStyle(StyleNameEnum style) {
        this.style = style;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

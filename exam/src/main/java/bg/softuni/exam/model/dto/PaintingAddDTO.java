package bg.softuni.exam.model.dto;

import bg.softuni.exam.model.enums.StyleNameEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PaintingAddDTO {
    @NotBlank(message = "Name not be blank!")
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    private String name;
    @NotBlank(message = "Author not be blank!")
    @Size(min = 5, max = 30, message = "Author length must be between 5 and 30 characters !")
    private String author;
    @NotBlank(message = "Please enter valid URL!")
    @Size(max = 150, message = "Image URL must be no more than 150 characters!")
    private String image;
    @NotNull(message = "Select the style!")
    private StyleNameEnum style;

    public PaintingAddDTO() {
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

    public StyleNameEnum getStyle() {
        return style;
    }

    public void setStyle(StyleNameEnum style) {
        this.style = style;
    }
}

package bg.softuni.exam.service;

import bg.softuni.exam.model.entity.Style;
import bg.softuni.exam.model.enums.StyleNameEnum;

public interface StyleService {
    void initStyles();

    Style findByStyleNameEnum(StyleNameEnum style);
}

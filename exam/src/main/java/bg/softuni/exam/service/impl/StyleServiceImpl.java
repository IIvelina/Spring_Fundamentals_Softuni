package bg.softuni.exam.service.impl;

import bg.softuni.exam.model.entity.Style;
import bg.softuni.exam.model.enums.StyleNameEnum;
import bg.softuni.exam.repository.StyleRepository;
import bg.softuni.exam.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void initStyles() {
        if(styleRepository.count() != 0){
            return;
        }
        Arrays
                .stream(StyleNameEnum.values())
                .forEach(styleNameEnum -> {
                    Style style = new Style();
                    style.setName(styleNameEnum);
                    switch (styleNameEnum){
                        case IMPRESSIONISM -> style.setDescription("Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture.");
                        case ABSTRACT -> style.setDescription("Abstract art does not attempt to represent recognizable subjects in a realistic manner. ");
                        case EXPRESSIONISM -> style.setDescription("Expressionism is a style of art that doesn't concern itself with realism.");
                        case SURREALISM -> style.setDescription("Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation.");
                        case REALISM -> style.setDescription("Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance.");
                    }
                    styleRepository.save(style);
                });
    }

    @Override
    public Style findByStyleNameEnum(StyleNameEnum style) {
        return styleRepository
                .findByName(style)
                .orElse(null);
    }
}

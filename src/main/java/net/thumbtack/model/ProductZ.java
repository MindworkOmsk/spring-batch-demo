package net.thumbtack.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductZ {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;


}

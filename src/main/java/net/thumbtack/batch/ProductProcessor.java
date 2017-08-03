package net.thumbtack.batch;

import net.thumbtack.model.Product;
import net.thumbtack.model.ProductZ;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;


@Service
public class ProductProcessor implements ItemProcessor<Product, ProductZ> {

    @Override
    public ProductZ process(Product product) throws Exception {
        return new ProductZ(
                product.getId(),
                product.getName() + " was updated",
                product.getDescription(),
                product.getPrice(),
                product.getAction());
    }
}

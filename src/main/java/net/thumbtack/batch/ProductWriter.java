package net.thumbtack.batch;

import net.thumbtack.model.ProductZ;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductWriter implements ItemWriter<ProductZ> {

    public void write(List<? extends ProductZ> list) throws Exception {
        for (ProductZ product : list) {
            System.out.println(product);
        }
    }
}

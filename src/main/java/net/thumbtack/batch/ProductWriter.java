package net.thumbtack.batch;

import net.thumbtack.model.Product;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductWriter implements ItemWriter<Product> {

    public void write(List<? extends Product> list) throws Exception {
        for (Product product : list) {
            System.out.println(product);
        }
    }
}

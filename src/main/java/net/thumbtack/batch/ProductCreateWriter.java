package net.thumbtack.batch;

import net.thumbtack.model.ProductZ;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("productCreateWriter")
public class ProductCreateWriter implements ItemWriter<ProductZ> {

    public void write(List<? extends ProductZ> list) throws Exception {
        for (ProductZ product : list) {
            System.out.println(product);
        }
    }
}

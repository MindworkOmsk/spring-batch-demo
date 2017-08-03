package net.thumbtack.batch;

import net.thumbtack.model.ProductZ;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.BackToBackPatternClassifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProductWriter {

    @Autowired
    private ProductWriterClassifier productWriterClassifier;

    @Autowired
    private ProductCreateWriter productCreateWriter;

    @Autowired
    private ProductDeleteWriter productDeleteWriter;


    @Bean
    public ClassifierCompositeItemWriter<ProductZ> itemsWriter() {
        ClassifierCompositeItemWriter<ProductZ> writer = new ClassifierCompositeItemWriter<>();

        BackToBackPatternClassifier<ProductZ, ItemWriter<? super ProductZ>> classifier = new BackToBackPatternClassifier();
        classifier.setRouterDelegate(productWriterClassifier);
        classifier.setMatcherMap(new HashMap<String, ItemWriter<? super ProductZ>>() {{
            put("create", productCreateWriter);
            put("delete", productDeleteWriter);
        }});

        writer.setClassifier(classifier);

        return writer;
    }
}

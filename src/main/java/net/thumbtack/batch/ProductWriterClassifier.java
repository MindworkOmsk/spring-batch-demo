package net.thumbtack.batch;

import net.thumbtack.model.ProductZ;
import org.springframework.batch.support.annotation.Classifier;
import org.springframework.stereotype.Service;

@Service
public class ProductWriterClassifier {

    @Classifier
    public String classify(ProductZ classifiable) {
        return classifiable.getAction();
    }
}

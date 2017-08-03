package net.thumbtack.batch;


import net.thumbtack.model.Product;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;


@Service
public class FileReader {

    @Bean
    private DefaultLineMapper lineMapper() {
        final DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames(new String[]{"PRODUCT_ID", "NAME", "DESCRIPTION", "PRICE"});

        return new DefaultLineMapper<Product>() {{
            setLineTokenizer(delimitedLineTokenizer);
            setFieldSetMapper(new ProductFieldSetMapper());
        }};
    }

    @Bean
    public ItemReader<Product> itemsReader() {
        FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(this.getClass().getClassLoader().getResource("source.csv").getPath()));
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());

        return reader;
    }
}

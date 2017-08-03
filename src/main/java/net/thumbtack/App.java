package net.thumbtack;

import net.thumbtack.batch.ImportProductsJob;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@EnableBatchProcessing
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");

        ImportProductsJob importProductsJob = applicationContext.getBean(ImportProductsJob.class);
        importProductsJob.runJob();
    }
}

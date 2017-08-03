package net.thumbtack.batch;


import net.thumbtack.model.Product;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class ImportProductsJob {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Autowired
    @Qualifier("itemsReader")
    private ItemReader<Product> reader;

    @Autowired
    @Qualifier("productWriter")
    private ItemWriter<Product> writer;


    //job definition
    @Bean
    private Job superJob() {
        Step superStep = stepBuilderFactory.get("superStep")
                .<Product, Product>chunk(10) //same because without transformation
                .reader(reader)
                .writer(writer)
                .build();

        return jobBuilderFactory.get("superJob").start(superStep).build();
    }

    //job launch
    public void runJob() {
        try {
            JobExecution jobExecution = jobLauncher.run(superJob(), new JobParameters());

            System.out.println("Job finished with status - " + jobExecution.getStatus());
        } catch (JobExecutionAlreadyRunningException
                | JobRestartException
                | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}

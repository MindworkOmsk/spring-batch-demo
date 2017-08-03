package net.thumbtack.batch;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-spring-context.xml")
public class ITImportProductsJob {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job superJob;

    @Test
    public void testSuccess() throws Exception {
        JobExecution jobExecution = jobLauncher.run(superJob, new JobParameters());

        Assert.assertTrue(jobExecution.getStatus().equals(BatchStatus.COMPLETED));
    }
}

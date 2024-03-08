package com.demo.batch.configuration;

import com.demo.batch.processor.ExampleItemProcessor;
import com.demo.batch.reader.ExampleItemReader;
import com.demo.batch.writer.ExampleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SecondJob {

    private final ExampleItemReader itemReader;
    private final ExampleItemProcessor itemProcessor;
    private final ExampleItemWriter itemWriter;

    public SecondJob(ExampleItemReader itemReader, ExampleItemProcessor itemProcessor, ExampleItemWriter itemWriter) {
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
    }

    @Bean
    public Job job(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("Second Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep(jobRepository, platformTransactionManager))
                .build();
    }

    @Bean
    public Step firstChunkStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("First Chunk Step", jobRepository)
                .<Integer, Long>chunk(3, platformTransactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
}
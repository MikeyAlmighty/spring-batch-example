//package com.demo.batch.configuration;
//
//import com.demo.batch.service.FirstTasklet;
//import com.demo.batch.service.SecondTasklet;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class FirstJob {
//
//    private final FirstTasklet firstTasklet;
//    private final SecondTasklet secondTasklet;
//
//    public FirstJob(FirstTasklet firstTasklet, SecondTasklet secondTasklet) {
//        this.firstTasklet = firstTasklet;
//        this.secondTasklet = secondTasklet;
//    }
//
//    @Qualifier("firstJob")
//    @Bean
//    public Job job(JobRepository jobRepository, Step firstStep, Step secondStep){
//        return new JobBuilder("First Job", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(firstStep)
//                .next(secondStep)
//                .build();
//    }
//
//    @Bean
//    public Step firstStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
//        return new StepBuilder("First Step", jobRepository)
//                .tasklet(firstTasklet, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    Step secondStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new StepBuilder("Second Step", jobRepository)
//                .tasklet(secondTasklet, platformTransactionManager)
//                .build();
//    }
//}

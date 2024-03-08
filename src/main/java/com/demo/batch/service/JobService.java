package com.demo.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;

@Service
public class JobService {
    JobLauncher jobLauncher;

    @Qualifier("firstJob")
    private final Job firstJob;

    @Qualifier("secondJob")
    private final Job secondJob;

    public JobService(JobLauncher jobLauncher, Job firstJob, Job secondJob) {
        this.jobLauncher = jobLauncher;
        this.firstJob = firstJob;
        this.secondJob = secondJob;
    }

    @Async
    public void startJob(String jobName)  {
        var jobParameters = new JobParametersBuilder()
                .addJobParameter("currentTime", System.currentTimeMillis(), Long.class)
                .toJobParameters();
        try {
            JobExecution jobExecution = null;
            if (jobName.equals("FirstJob")) {
                jobLauncher.run(firstJob, jobParameters);
            } else if (jobName.equals("SecondJob")){
                jobLauncher.run(secondJob, jobParameters);
            }
            System.out.println("jobExecutionID = " + jobExecution.getId());
        } catch (Exception exception) {
            System.out.println("Exception while starting job");
        }

    }
}

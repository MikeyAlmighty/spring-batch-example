package com.demo.batch.controller;

import com.demo.batch.service.JobService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName) {
        jobService.startJob(jobName);
        return "Job Started...";
    }
}

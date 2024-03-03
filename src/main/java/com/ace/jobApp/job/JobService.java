package com.ace.jobApp.job;

import java.util.List;

public interface JobService {
    List<Job> findAllJobs();

    Job createJob(JobDto job);

    Job findJobById(Long id);

    Job updateJob(Long id, JobDto job);

    boolean deleteJob(Long id);
}

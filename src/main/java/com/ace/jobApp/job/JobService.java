package com.ace.jobApp.job;

import java.util.List;

public interface JobService {
    List<Job> findAllJobs();

    Job createJob(Job job);

    Job findJobById(Long id);

    Job updateJob(Long id, Job job);

    boolean deleteJob(Long id);
}

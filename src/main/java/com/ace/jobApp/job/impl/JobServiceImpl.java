package com.ace.jobApp.job.impl;

import com.ace.jobApp.job.Job;
import com.ace.jobApp.job.JobRepository;
import com.ace.jobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;

    JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) {
        try {
            return jobRepository.findById(id).orElseThrow(() -> new NoSuchFieldException("Invalid Job id"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Job updateJob(Long id, Job updatedJobData) {
        Job job = this.findJobById(id);
        job.setTitle(updatedJobData.getTitle());
        job.setDescription(updatedJobData.getDescription());
        job.setCurrency(updatedJobData.getCurrency());
        job.setMinSalary(updatedJobData.getMinSalary());
        job.setMaxSalary(updatedJobData.getMaxSalary());
        job.setLocation(updatedJobData.getLocation());
        return jobRepository.save(job);
    }

    @Override
    public boolean deleteJob(Long id) {
        Job job = this.findJobById(id);
        jobRepository.deleteById(id);
        return true;
    }
}

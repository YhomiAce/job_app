package com.ace.jobApp.job.impl;

import com.ace.jobApp.company.Company;
import com.ace.jobApp.company.CompanyService;
import com.ace.jobApp.job.Job;
import com.ace.jobApp.job.JobDto;
import com.ace.jobApp.job.JobRepository;
import com.ace.jobApp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final CompanyService companyService;

    JobServiceImpl(JobRepository jobRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
    }

    private Job dtoToJob(JobDto dto) {
        Company company = companyService.findById(dto.companyId());
        Job job = new Job();
        job.setCompany(company);
        job.setCurrency(dto.currency());
        job.setLocation(dto.location());
        job.setTitle(dto.title());
        job.setDescription(dto.description());
        job.setMinSalary(dto.minSalary());
        job.setMaxSalary(dto.maxSalary());
        return job;
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(JobDto jobDto) {
        Job job = dtoToJob(jobDto);
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
    public Job updateJob(Long id, JobDto updatedJobData) {
        Job existing = this.findJobById(id);
        Job job = dtoToJob(updatedJobData);
        job.setId(existing.getId());
        return jobRepository.save(job);
    }

    @Override
    public boolean deleteJob(Long id) {
        Job job = this.findJobById(id);
        jobRepository.deleteById(id);
        return true;
    }
}

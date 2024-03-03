package com.ace.jobApp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        List<Job> jobs = jobService.findAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> create(@RequestBody Job data){
        Job job = jobService.createJob(data);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJob(@PathVariable Long id) {
        return  new ResponseEntity<>(jobService.findJobById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> update(@PathVariable Long id, @RequestBody Job job) {
        return  new ResponseEntity<>(jobService.updateJob(id, job), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        jobService.deleteJob(id);
        return  new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }
}

package com.ace.jobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
    private  final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> findCompanyReviews(@PathVariable Long companyId) {
        return ResponseEntity.ok().body(reviewService.findAll(companyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findReviewById(@PathVariable Long id, @PathVariable Long companyId) {
        return ResponseEntity.ok().body(reviewService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        return new ResponseEntity<>(reviewService.create(companyId, review), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Review> findReviewById(@PathVariable Long companyId, @PathVariable Long id, @RequestBody Review review) {
        return ResponseEntity.ok().body(reviewService.update(companyId, id, review));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long id){
        reviewService.delete(companyId, id);
        return ResponseEntity.ok().body("Review deleted sucessfully");
    }

}

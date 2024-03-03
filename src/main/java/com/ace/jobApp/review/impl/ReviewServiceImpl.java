package com.ace.jobApp.review.impl;

import com.ace.jobApp.company.Company;
import com.ace.jobApp.company.CompanyService;
import com.ace.jobApp.review.Review;
import com.ace.jobApp.review.ReviewRepository;
import com.ace.jobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReviewServiceImpl implements ReviewService {
    private  final ReviewRepository reviewRepository;
    private  final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public Review create(Long companyId, Review review) {
        Company company = companyService.findById(companyId);
        review.setCompany(company);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAll(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Invalid Review Id"));
    }

    @Override
    public Review update(Long companyId, Long id, Review reviewData) {
        Company company = companyService.findById(companyId);
        Review review = findById(id);
        review.setContent(reviewData.getContent());
        review.setRating(reviewData.getRating());
        review.setReviewer(reviewData.getReviewer());
        review.setCompany(company);
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Long companyId, Long id) {
        Review review = findById(id);
        review.setCompany(null);

//        Company company = companyService.findById(companyId);
        reviewRepository.deleteById(id);
    }
}

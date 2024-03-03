package com.ace.jobApp.review;

import java.util.List;

public interface ReviewService {
    Review create(Long companyId, Review review);

    List<Review> findAll(Long companyId);

    Review findById(Long id);

    Review update(Long companyId, Long id, Review review);

    void  delete(Long companyId, Long id);
}

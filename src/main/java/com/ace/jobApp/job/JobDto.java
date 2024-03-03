package com.ace.jobApp.job;

public record JobDto(
        Long companyId,
        String title,

                String description,

                 double minSalary,

                  double maxSalary,

                 String location,

                 String currency
) {
}

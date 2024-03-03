package com.ace.jobApp.company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    Company create(Company company);

    Company findById(Long id);

    Company update(Long id, Company company);

    void delete(Long id);
}

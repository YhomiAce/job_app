package com.ace.jobApp.company.impl;

import com.ace.jobApp.company.Company;
import com.ace.jobApp.company.CompanyRepository;
import com.ace.jobApp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Invalid Company"));
    }

    @Override
    public Company update(Long id, Company updatedCompanyData) {
        Company company = this.findById(id);
        company.setName(updatedCompanyData.getName());
        company.setDescription(updatedCompanyData.getDescription());
        return companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        Company company = this.findById(id);
        companyRepository.deleteById(id);
    }
}

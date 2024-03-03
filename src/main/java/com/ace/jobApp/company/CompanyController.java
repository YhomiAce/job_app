package com.ace.jobApp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        return  ResponseEntity.ok().body(companyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(companyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody Company company) {
        Company savedCompany = companyService.create(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable Long id, @RequestBody Company company)  {
        Company savedCompany = companyService.update(id, company);
        return new ResponseEntity<>(savedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.ok().body("Company deleted successfully");
    }
}

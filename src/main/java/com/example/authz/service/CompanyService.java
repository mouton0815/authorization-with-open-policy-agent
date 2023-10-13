package com.example.authz.service;

import com.example.authz.domain.Company;
import com.example.authz.domain.CompanyData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public long getCompanyCount() {
        return repository.getCompanies().count();
    }

    public List<Company> getCompanies() {
        return repository.getCompanies().collect(Collectors.toList());
    }

    public Optional<Company> getCompany(int id) {
        return repository.getCompany(id);
    }

    public Company addCompany(CompanyData company) {
        return repository.addCompany(company);
    }

    public Optional<Company> updateCompany(int id, CompanyData companyData) {
        return repository.updateCompany(id, companyData);
    }

    public boolean deleteCompany(int id) {
        return repository.deleteCompany(id);
    }
}

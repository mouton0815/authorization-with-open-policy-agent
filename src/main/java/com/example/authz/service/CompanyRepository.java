package com.example.authz.service;

import com.example.authz.domain.Company;
import com.example.authz.domain.CompanyData;

import java.util.Optional;
import java.util.stream.Stream;

public interface CompanyRepository {
    Stream<Company> getCompanies();
    Optional<Company> getCompany(int id);
    Company addCompany(CompanyData company);
    Optional<Company> updateCompany(int id, CompanyData companyData);
    boolean deleteCompany(int id);
}

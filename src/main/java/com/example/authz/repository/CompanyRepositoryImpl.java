package com.example.authz.repository;

import com.example.authz.domain.Company;
import com.example.authz.domain.CompanyData;
import com.example.authz.service.CompanyRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class CompanyRepositoryImpl implements CompanyRepository {
    private static final Map<Integer, CompanyData> COMPANIES = new HashMap<>();

    static {
        COMPANIES.put(1, new CompanyData("Foo GmbH", "Germany"));
        COMPANIES.put(2, new CompanyData("Bar AG", "Spain"));
        COMPANIES.put(3, new CompanyData("Baz GbR", "Austria"));
    }

    public Stream<Company> getCompanies() {
        return COMPANIES.entrySet().stream().map(e -> new Company(e.getKey(), e.getValue()));
    }

    public Optional<Company> getCompany(int id) {
        CompanyData companyData = COMPANIES.get(id);
        return companyData == null ? Optional.empty() : Optional.of(new Company(id, companyData));
    }

    public Company addCompany(CompanyData company) {
        int id = COMPANIES.size() + 1;
        COMPANIES.put(id, company);
        return new Company(id, company);
    }

    public Optional<Company> updateCompany(int id, CompanyData companyData) {
        if (!COMPANIES.containsKey(id)) {
            return Optional.empty();
        }
        COMPANIES.put(id, companyData);
        return Optional.of(new Company(id, companyData));
    }

    public boolean deleteCompany(int id) {
        return COMPANIES.remove(id) != null;
    }
}

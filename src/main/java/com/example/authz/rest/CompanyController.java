package com.example.authz.rest;

import com.example.authz.domain.Company;
import com.example.authz.domain.CompanyData;
import com.example.authz.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping(value = "/companies", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> postCompany(@RequestBody CompanyData companyData) {
        Company company = service.addCompany(companyData);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(company.getId()).toUri();
        return ResponseEntity.created(location).body(company);
    }

    @GetMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Company> getCompanies() {
        return service.getCompanies();
    }

    @GetMapping(value = "/companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Company getCompany (@PathVariable("id") int id) {
        return service.getCompany(id).orElseThrow(CompanyNotFoundException::new);
    }

    @PutMapping(value = "/companies/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Company putCompany(@PathVariable("id") int id, @RequestBody CompanyData companyData) {
        return service.updateCompany(id, companyData).orElseThrow(CompanyNotFoundException::new);
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable("id") int id) {
        if (!service.deleteCompany(id)) {
            throw new CompanyNotFoundException();
        }
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such company")
    public static class CompanyNotFoundException extends RuntimeException {
        // Empty
    }
}

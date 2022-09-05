package com.example.springbootsesson.peaksoft.service;

import com.example.springbootsesson.peaksoft.model.Company;
import com.example.springbootsesson.peaksoft.repasitory.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompany() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

    public Company getById(Long id) {
        return companyRepository.findById(id).
                orElseThrow(() -> new NullPointerException("Company with " + id + " was not found"));
    }

    public void deleteById(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
        } else
            throw new NullPointerException(String.format("Company with id %s doesn't exists", id));

    }

    public Company update(Company company, Long id) {
        Company company1 = companyRepository.findById(id).
                orElseThrow(()->
                        new NullPointerException("Company with " + id + " was not found"));

        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        companyRepository.save(company1);
        return company1;
    }
}

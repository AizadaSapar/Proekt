package com.example.springbootsesson.peaksoft.repasitory;

import com.example.springbootsesson.peaksoft.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}

package com.example.springbootsesson.peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {

        @Id
        @SequenceGenerator(
                name = "company_sequence",
                sequenceName = "company_sequence",
                allocationSize = 1)
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "company_sequence"
        )
        private Long id;
        @Column(name = "company_name")
        private String companyName;
        @Column(name = "located_country")
        private String locatedCountry;

        public Company(String companyName, String locatedCountry) {
            this.companyName = companyName;
            this.locatedCountry = locatedCountry;
        }
}

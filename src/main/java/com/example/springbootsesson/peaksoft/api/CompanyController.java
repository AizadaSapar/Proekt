package com.example.springbootsesson.peaksoft.api;

import com.example.springbootsesson.peaksoft.model.Company;
import com.example.springbootsesson.peaksoft.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companies")
public class CompanyController {


    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("/new")
    public String newCompany(Model model) {
        model.addAttribute("newCompany", new Company());
        return "newCompany";
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("newCompany") Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies/allCompanies";
    }

    @GetMapping("/allCompanies")
    public String getAllCompanies(Model model) {
        model.addAttribute("allCompanies", companyService.getAllCompany());
        return "allCompanies";
    }

    @GetMapping("/editCompany/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model) {
        model.addAttribute("company", companyService.getById(id));
        return "editCompany";
    }

    @GetMapping("/updateCompany/{id}")
    public String updateCompany(@ModelAttribute("company") Company company, @PathVariable("id") Long id) {
        companyService.update(company, id);
        return "redirect:/companies/allCompanies";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteById(id);
        return "redirect:/companies/allCompanies";
    }
}


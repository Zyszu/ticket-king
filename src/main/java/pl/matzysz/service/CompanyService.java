package pl.matzysz.service;

import pl.matzysz.domain.Company;

import java.util.List;

public interface CompanyService {

    public void addCompany(Company company);
    public void editCompany(Company company);
    public List<Company> listCompany();
    public void deleteCompany(long id);
    public Company getCompany(long id);
    public Company getCompanyByName(String name);

}

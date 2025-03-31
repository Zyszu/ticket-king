package pl.matzysz.service;

import pl.matzysz.domain.Company;
import pl.matzysz.domain.User;

import java.util.List;

public interface CompanyService {

    public Company addCompany(Company company);
    public Company editCompany(Company company);
    public List<Company> listCompany();
    public void deleteCompany(long id);
    public Company getCompany(long id);
    public Company getCompanyByName(String name);
    public Company getCompanyByOwner(User owner);

}

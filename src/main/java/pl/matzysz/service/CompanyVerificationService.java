package pl.matzysz.service;

import pl.matzysz.domain.Company;
import pl.matzysz.domain.CompanyVerification;

import java.util.List;

public interface CompanyVerificationService {
    public CompanyVerification addCompanyVerification(CompanyVerification companyVerification);
    public CompanyVerification editCompanyVerification(CompanyVerification companyVerification);
    public List<CompanyVerification> listCompanyVerificationsByCompany(Company company);
    public List<CompanyVerification> listNotCompleteCompanyVerification();
    public CompanyVerification findCompanyVerificationById(Long id);

    public CompanyVerification acceptCompanyVerification(CompanyVerification companyVerification);
    public CompanyVerification rejectCompanyVerification(CompanyVerification companyVerification);
    public CompanyVerification startCompanyVerification(Company company);
}

package pl.matzysz.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.CompanyVerification;
import pl.matzysz.domain.User;
import pl.matzysz.repository.CompanyRepository;
import pl.matzysz.repository.CompanyVerificationRepository;
import pl.matzysz.repository.RoleRepository;
import pl.matzysz.repository.UserRepository;

import java.util.List;

@Service
public class CompanyVerificationServiceImpl implements CompanyVerificationService {

    private final CompanyVerificationRepository companyVerificationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyVerificationServiceImpl(
            CompanyVerificationRepository companyVerificationRepository,
            UserRepository userRepository,
            RoleRepository roleRepository,
            EntityManager entityManager,
            CompanyRepository companyRepository
    ) {
        this.companyVerificationRepository = companyVerificationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CompanyVerification addCompanyVerification(CompanyVerification companyVerification) {
        return companyVerificationRepository.save(companyVerification);
    }

    @Transactional
    public CompanyVerification editCompanyVerification(CompanyVerification companyVerification) {
        return companyVerificationRepository.save(companyVerification);
    }

    @Transactional
    public List<CompanyVerification> listCompanyVerificationsByCompany(Company company) {
        return companyVerificationRepository.findByCompany(company);
    }

    @Transactional
    public List<CompanyVerification> listNotCompleteCompanyVerification() {
        return companyVerificationRepository.findByVerificationComplete(false);
    }

    @Transactional
    public CompanyVerification findCompanyVerificationById(Long id) {
        return companyVerificationRepository.findById(id).orElse(null);
    }

    @Transactional
    public CompanyVerification startCompanyVerification(Company company) {
        if (company.getOwner() == null) {
            throw new IllegalArgumentException("Company must have a valid owner!");
        }

        User owner = userRepository.findById(company.getOwner().getId());
        owner = entityManager.merge(owner);
        company.setOwner(owner);

        // if has ROLE_VERIFIED already remove it
        owner.getRoles().remove(roleRepository.findByRole("ROLE_VERIFIED"));

        owner.getRoles().add(roleRepository.findByRole("ROLE_PROPRIETOR"));
        owner.getRoles().add(roleRepository.findByRole("ROLE_NOT_VERIFIED"));
        userRepository.save(owner);

        company.setAddress(entityManager.merge(company.getAddress())); // Merge address if detached

        CompanyVerification companyVerification = new CompanyVerification();
        companyVerification.setCompany(company);
        companyVerification.setVerificationComplete(false);

        companyRepository.save(company);
        return companyVerificationRepository.save(companyVerification);
    }

    @Transactional
    public CompanyVerification acceptCompanyVerification(CompanyVerification companyVerification) {
        Company verifyingCompany = companyVerification.getCompany();
        verifyingCompany.setVerified(true);
        verifyingCompany.setActive(true);

        User owner = companyVerification.getCompany().getOwner();
        owner = entityManager.merge(owner);
        owner.getRoles().add(roleRepository.findByRole("ROLE_VERIFIED"));
        owner.getRoles().remove(roleRepository.findByRole("ROLE_NOT_VERIFIED"));
        userRepository.save(owner);


        companyVerification.setAccepted(true);
        companyVerification.setVerificationComplete(true);
        return companyVerificationRepository.save(companyVerification);
    }

    @Transactional
    public CompanyVerification rejectCompanyVerification(CompanyVerification companyVerification) {
        Company verifyingCompany = companyVerification.getCompany();
        verifyingCompany.setVerified(false);
        verifyingCompany.setActive(false);

        User owner = companyVerification.getCompany().getOwner();
        owner = entityManager.merge(owner);
        owner.getRoles().remove(roleRepository.findByRole("ROLE_NOT_VERIFIED"));
        userRepository.save(owner);

        companyVerification.setAccepted(false);
        companyVerification.setVerificationComplete(true);
        return companyVerificationRepository.save(companyVerification);
    }

}

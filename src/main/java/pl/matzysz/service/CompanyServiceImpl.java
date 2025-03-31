package pl.matzysz.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.CompanyVerification;
import pl.matzysz.domain.Role;
import pl.matzysz.domain.User;
import pl.matzysz.repository.CompanyRepository;
import pl.matzysz.repository.CompanyVerificationRepository;
import pl.matzysz.repository.RoleRepository;
import pl.matzysz.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final RoleRepository roleRepository;
    private final CompanyVerificationRepository companyVerificationRepository;

    @Autowired
    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            UserRepository userRepository,
            EntityManager entityManager, RoleRepository roleRepository, CompanyVerificationRepository companyVerificationRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.roleRepository = roleRepository;
        this.companyVerificationRepository = companyVerificationRepository;
    }

    @Transactional
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public Company editCompany(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public List<Company> listCompany() {
        return companyRepository.findAll();
    }

    @Transactional
    public void deleteCompany(long id) {
        companyRepository.deleteById(id);
    }

    @Transactional
    public Company getCompany(long id) {
        return companyRepository.findById(id);
    }

    @Transactional
    public Company getCompanyByName(String name) {
        return companyRepository.findByCompanyName(name);
    }

    @Transactional
    public Company getCompanyByOwner(User owner) {
        return companyRepository.findByOwner(owner);
    }

}

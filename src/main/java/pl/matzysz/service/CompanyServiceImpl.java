package pl.matzysz.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.Company;
import pl.matzysz.domain.User;
import pl.matzysz.repository.CompanyRepository;
import pl.matzysz.repository.RoleRepository;
import pl.matzysz.repository.UserRepository;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final RoleRepository roleRepository;

    @Autowired
    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            UserRepository userRepository,
            EntityManager entityManager, RoleRepository roleRepository) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void addCompany(Company company) {
        if (company.getOwner() == null) {
            throw new IllegalArgumentException("Company must have a valid owner!");
        }

        User owner = userRepository.findById(company.getOwner().getId());
        company.setOwner(owner); // Ensure owner is correctly set before saving

        owner.getRoles().add(roleRepository.findByRole("ROLE_PROPRIETOR"));
        userRepository.save(owner);

        if (company.getAddress() != null) {
            company.setAddress(entityManager.merge(company.getAddress())); // Merge address if detached
        }

        companyRepository.save(company);
    }

    @Transactional
    public void editCompany(Company company) {
        if (company.getOwner() == null) {
            throw new IllegalArgumentException("Company must have a valid owner!");
        }

        User owner = userRepository.findById(company.getOwner().getId());

        company.setOwner(owner); // Ensure owner is correctly set

        if (company.getAddress() != null) {
            company.setAddress(entityManager.merge(company.getAddress())); // Merge address if detached
        }

        companyRepository.save(company);
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

}

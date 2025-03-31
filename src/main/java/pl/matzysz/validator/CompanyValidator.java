package pl.matzysz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.matzysz.domain.Company;

public class CompanyValidator implements Validator {

    private static final String NIP_REGEX =  "^[A-Z0-9]{9,13}$";
    private static final String COMPANY_NAME_REGEX =  "^[\\p{L} ]{3,30}$";
    private static final String ZIPCODE_REGEX = "\\d{2}-\\d{3}";

    @Override
    public boolean supports(Class<?> clazz) { return Company.class.isAssignableFrom(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        Company company = (Company) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nip", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.country", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.state", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.zipCode", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.street", "error.field.required");


        if (errors.getErrorCount() == 0) {
            // Validate zip code format
            if (!company.getNip().matches(NIP_REGEX)) {
                errors.rejectValue("nip", "error.invalid.nip");
            }

            if(!company.getCompanyName().matches(COMPANY_NAME_REGEX)) {
                errors.rejectValue("companyName", "error.invalid.companyname");
            }

            // Validate zip code format
            if (!company.getAddress().getZipCode().matches(ZIPCODE_REGEX)) {
                errors.rejectValue("address.zipCode", "error.invalid.zipcode");
            }

        }

    }

}

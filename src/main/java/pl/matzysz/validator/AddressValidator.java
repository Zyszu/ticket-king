package pl.matzysz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.matzysz.domain.Address;

public class AddressValidator implements Validator {

    private static final String ZIPCODE_REGEX = "\\d{2}-\\d{3}";

    @Override
    public boolean supports(Class<?> clazz) { return Address.class.isAssignableFrom(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        Address address = (Address) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "error.field.required");

        if (errors.getErrorCount() == 0) {
            // Validate zip code format
            if (!address.getZipCode().matches(ZIPCODE_REGEX)) {
                errors.rejectValue("zipCode", "error.invalid.zipcode");
            }
        }

    }
}

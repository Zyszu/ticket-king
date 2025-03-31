package pl.matzysz.validator;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.matzysz.domain.User;

public class UserValidator implements Validator {

    private final EmailValidator emailValidator = new EmailValidator();

    @Override
    public boolean supports(Class<?> clazz) { return User.class.isAssignableFrom(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.field.required");

        // If no errors so far, proceed with additional validations
        if (errors.getErrorCount() == 0) {
            // Validate email format
            if (!emailValidator.isValid(user.getEmail(), null)) {
                errors.rejectValue("email", "error.email.invalid");
            }
        }

    }

}

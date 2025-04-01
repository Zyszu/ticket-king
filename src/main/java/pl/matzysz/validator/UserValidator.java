package pl.matzysz.validator;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.matzysz.domain.User;
import pl.matzysz.service.EmailSenderService;
import pl.matzysz.service.UserService;

import java.util.Objects;

public class UserValidator implements Validator {

    private final EmailValidator emailValidator = new EmailValidator();

    // all char allowed, min length of 8, at least one special char
    private final String PASSWORD_REGEX_LENGTH = "^.{8,}$";
    private final String PASSWORD_REGEX_SPECIAL_CHAR = "^(?=.*[^\\w\\s]).*$";

    @Override
    public boolean supports(Class<?> clazz) { return User.class.isAssignableFrom(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.field.required");

        // If no errors so far, proceed with additional validations
        if (errors.getErrorCount() == 0) {
            // Validate email format
            if (!emailValidator.isValid(user.getEmail(), null)) {
                errors.rejectValue("email", "error.invalid.email");
            }

            if (!user.getPassword().matches(PASSWORD_REGEX_LENGTH)) {
                errors.rejectValue("password", "error.invalid.password.length");
            }

            if (!user.getPassword().matches(PASSWORD_REGEX_SPECIAL_CHAR)) {
                errors.rejectValue("password", "error.invalid.password.special");
            }

            if (Objects.equals(user.getEmail(), user.getPassword())) {
                errors.rejectValue("password", "error.invalid.password.same.as.email");
            }
        }

    }

}

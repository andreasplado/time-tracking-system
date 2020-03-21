package com.logines.schedule.validator;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.model.Users;
import com.logines.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userServiceImpl;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Users users = (Users) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.userForm.username");
        errors.addAllErrors(errors);
        if(users.getUsername() != null && users.getPassword() != null) {
            if (users.getUsername().length() < 6 || users.getUsername().length() > 32) {
                errors.rejectValue("username", "Size.userForm.username");
            }
            if (userServiceImpl.findByUsername(users.getUsername()) != null) {
                errors.rejectValue("username", "Duplicate.userForm.username");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
            if (users.getPassword().length() < 8 || users.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.userForm.password");
            }

            if (!users.getPasswordConfirm().equals(users.getPassword())) {
                errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
            }
        }
    }
}

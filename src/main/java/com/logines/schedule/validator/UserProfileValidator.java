package com.logines.schedule.validator;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.model.Users;
import com.logines.schedule.service.UserProfileService;
import com.logines.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserProfileValidator implements Validator {
    @Autowired
    private UserProfileService userServiceImpl;

    @Override
    public boolean supports(Class<?> aClass) {
        return Users.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserProfile userProfile = (UserProfile) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.userProfile.username");
        errors.addAllErrors(errors);
        if(userProfile.getUsername() != null) {
            if (userProfile.getUsername().length() < 6 || userProfile.getUsername().length() > 32) {
                errors.rejectValue("username", "Size.userForm.username");
            }
            if (userServiceImpl.findUserProfile(userProfile.getUsername()) != null) {
                errors.rejectValue("username", "Duplicate.userForm.username");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
            if (userProfile.getEmail().length() < 8 || userProfile.getEmail().length() > 32) {
                errors.rejectValue("email", "Size.userProfile.password");
            }
        }
    }
}

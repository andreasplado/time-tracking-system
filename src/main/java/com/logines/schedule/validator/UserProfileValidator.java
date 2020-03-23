package com.logines.schedule.validator;

import com.logines.schedule.model.UserProfile;
import com.logines.schedule.service.UserProfileService;
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
        return UserProfile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserProfile userProfile = (UserProfile) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.userProfile.username");
        errors.addAllErrors(errors);
        if(userProfile.getUsername() != null) {
            if (userProfile.getFullname().length() < 6 || userProfile.getUsername().length() > 32) {
                errors.rejectValue("fullname", "Size.userForm.fullname");
            }
            if (userServiceImpl.findUserProfile(userProfile.getUsername()) != null) {
                errors.rejectValue("username", "Duplicate.userForm.username");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.password");
            if (userProfile.getEmail().length() < 8 || userProfile.getEmail().length() > 32) {
                errors.rejectValue("email", "Size.userProfile.email");
            }
        }
    }
}

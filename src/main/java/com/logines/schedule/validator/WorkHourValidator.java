package com.logines.schedule.validator;


import com.logines.schedule.model.WorkHour;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class WorkHourValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
       return WorkHour.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        WorkHour workHour = (WorkHour)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "start_time", "NotEmpty.workHour.start_time");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "end_time", "NotEmpty.workHour.start_time");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "notes", "NotEmpty.workHour.notes");
    }
}

package com.logines.schedule.validator;


import com.logines.schedule.model.WorkHour;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class WorkHourValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        WorkHour workHour = (WorkHour)target;

        if(workHour.getStartTime() == null){
            errors.rejectValue("start_time", "NotEmpty.workHour.start_time");
        }
        if(workHour.getEndTime() == null){
            errors.rejectValue("end_time", "NotEmpty.workHour.end_time");
        }
        if(workHour.getTitle() == null){
            errors.rejectValue("title", "NotEmpty.workHour.title");
        }
    }
}

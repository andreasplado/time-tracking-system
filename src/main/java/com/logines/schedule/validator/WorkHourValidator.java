package com.logines.schedule.validator;


import com.logines.schedule.model.WorkHour;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class WorkHourValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return WorkHour.class.equals(clazz);
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

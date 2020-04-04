package com.logines.schedule.configuration;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.*;
import java.time.format.*;

@ControllerAdvice
public class LocalDateTimeControllerAdvice
{

    @InitBinder
    public void initBinder( WebDataBinder binder )
    {
        binder.registerCustomEditor( LocalDateTime.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText( String text ) throws IllegalArgumentException
            {
                LocalDateTime.parse( text, DateTimeFormatter.ISO_DATE_TIME );
            }
        } );
        binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText( String text ) throws IllegalArgumentException
            {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
                LocalTime.parse( text, formatter);
            }
        } );
    }
}
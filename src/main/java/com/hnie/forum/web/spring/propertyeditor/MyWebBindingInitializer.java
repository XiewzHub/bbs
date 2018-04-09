package com.hnie.forum.web.spring.propertyeditor;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/2.
 */
public class MyWebBindingInitializer implements WebBindingInitializer {

    private String pattern;

    public MyWebBindingInitializer() {
        this.pattern = "yyyy-MM-dd";
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void initBinder(WebDataBinder webDataBinder, WebRequest webRequest) {
        DateFormat format = new SimpleDateFormat(this.pattern);
        format.setLenient(true);
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(format,true));
    }
}

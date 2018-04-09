package com.hnie.forum.web.spring.converter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/2.
 */
public class UtilDateConverter implements Converter<String, Date> {

    private String[] patterns;
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    public void setPatterns(String[] patterns) {
        this.patterns = patterns;
    }

    public UtilDateConverter() {
        this.patterns = new String[]{DEFAULT_PATTERN};
    }

    @Override
    public Date convert(String text) {
        Date date = null;

        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException("");
        }

        try {
            date = DateUtils.parseDate(text, this.patterns);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}

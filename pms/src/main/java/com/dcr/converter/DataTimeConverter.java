package com.dcr.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataTimeConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date data = sdf.parse(source);
            return data;
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}

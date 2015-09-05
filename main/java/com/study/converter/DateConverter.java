package com.study.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.study.util.Utility;

@FacesConverter("dateConverter")
public class DateConverter extends Utility implements Converter {

    private SimpleDateFormat f4 = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        Date twoDigitYear = parseDate("1/1/2000");
        Date convertedDate = null;
        for (String pattern : datePatterns()) {
            SimpleDateFormat f2 = getTwoDigitYearFormatter(pattern, twoDigitYear);
            try {
                convertedDate = f2.parse(value);
                break;
            } catch (ParseException pe) {
                continue;
            }
        }
        if (convertedDate == null) {
            FacesContext.getCurrentInstance().addMessage("searchForm:startDate", new FacesMessage("Please provide a valid date in one of the following formats: " + datePatterns()));
        }
        return convertedDate;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        if (value == null) {
            return "";
        }
        return new SimpleDateFormat("MM/dd/yyyy").format((Date) value);
    }

    private Date parseDate(String dateAsString) {
        Date date = null;
        try {
            date = f4.parse(dateAsString);
        } catch (ParseException e) {
        }
        return date;
    }

    private SimpleDateFormat getTwoDigitYearFormatter(String pattern, Date twoDigitYearStart) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.set2DigitYearStart(twoDigitYearStart);
        formatter.setLenient(false);
        return formatter;
    }

    private static List<String> datePatterns() {
        List<String> datePatterns = new ArrayList<String>();
        datePatterns.add("MM-dd-yy");
        datePatterns.add("MM.dd.yy");
        datePatterns.add("MM/dd/yy");
        return datePatterns;
    }

}

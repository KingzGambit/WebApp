package com.study.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.study.util.Utility;

@FacesConverter("timeConverter")
public class TimeConverter extends Utility implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        Integer minutes = (Integer) value;
        String timeAsString = "";

        if (minutes < 60) {
            timeAsString = "12:" + padZero(minutes) + period(minutes);
        }

        if (minutes >= 60 && minutes < 1440) {
            int hour = minutes / 60;
            int min = minutes - (hour * 60);
            timeAsString = toTwelveH(hour) + ":" + padZero(min) + period(minutes);
        }

        if (minutes == 1440) {
            timeAsString = "12:00 AM";
        }

        return timeAsString;
    }

    public String padZero(Integer minutes) {
        String s = String.valueOf(minutes);
        return minutes < 10 ? "0" + s : s;
    }

    public String toTwelveH(Integer hours) {
        return hours > 13 ? String.valueOf(hours - 12) : String.valueOf(hours);
    }

    public String period(Integer minutes) {
        return minutes < 720 ? " AM" : " PM";
    }
}

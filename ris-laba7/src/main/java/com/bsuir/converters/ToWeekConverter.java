package com.bsuir.converters;

import org.springframework.cglib.core.Converter;

public class ToWeekConverter implements DateConverter {
    private ToDayConverter dayConverter;

    public ToWeekConverter(ToDayConverter dayConverter) {
        this.dayConverter = dayConverter;
    }

    public ToWeekConverter() {

    }

    @Override
    public Double convert(Double year) {
        return dayConverter.convert(year) / 7;
    }

    public ToDayConverter getDayConverter() {
        return dayConverter;
    }

    public void setDayConverter(ToDayConverter dayConverter) {
        this.dayConverter = dayConverter;
    }
}

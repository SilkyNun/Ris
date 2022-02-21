package com.bsuir.converters;

public class ToHourConverter implements DateConverter {
    private ToDayConverter dayConverter;

    public ToHourConverter(ToDayConverter dayConverter) {
        this.dayConverter = dayConverter;
    }

    public ToHourConverter() {

    }

    @Override
    public Double convert(Double year) {
        return dayConverter.convert(year) * 24;
    }

    public ToDayConverter getDayConverter() {
        return dayConverter;
    }

    public void setDayConverter(ToDayConverter dayConverter) {
        this.dayConverter = dayConverter;
    }
}

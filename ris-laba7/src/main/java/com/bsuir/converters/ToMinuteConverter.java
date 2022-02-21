package com.bsuir.converters;

public class ToMinuteConverter implements DateConverter {
    private ToHourConverter toHourConverter;

    public ToMinuteConverter(ToHourConverter toHourConverter) {
        this.toHourConverter = toHourConverter;
    }

    public ToMinuteConverter() {

    }

    @Override
    public Double convert(Double year) {
        return toHourConverter.convert(year) * 60;
    }

    public ToHourConverter getToHourConverter() {
        return toHourConverter;
    }

    public void setToHourConverter(ToHourConverter toHourConverter) {
        this.toHourConverter = toHourConverter;
    }
}

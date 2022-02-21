package com.bsuir.converters;

public class ToSecondConverter implements DateConverter {
    private ToMinuteConverter minuteConverter;

    public ToSecondConverter(ToMinuteConverter minuteConverter) {
        this.minuteConverter = minuteConverter;
    }

    public ToSecondConverter() {

    }

    @Override
    public Double convert(Double year) {
        return minuteConverter.convert(year) * 60;
    }

    public ToMinuteConverter getMinuteConverter() {
        return minuteConverter;
    }

    public void setMinuteConverter(ToMinuteConverter minuteConverter) {
        this.minuteConverter = minuteConverter;
    }
}

package com.bsuir.converters;

public class ToMonthConverter implements DateConverter {
    @Override
    public Double convert(Double year) {
        return year * 12;
    }
}

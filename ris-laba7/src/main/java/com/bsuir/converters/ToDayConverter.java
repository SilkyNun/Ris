package com.bsuir.converters;

public class ToDayConverter implements DateConverter{
    @Override
    public Double convert(Double year) {
        return year * 365;
    }
}

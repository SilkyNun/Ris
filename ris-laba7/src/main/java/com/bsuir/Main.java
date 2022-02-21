package com.bsuir;

import com.bsuir.converters.DateConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("date.xml");
        final Double years = 1d;

        List<DateConverter> converters = List.of(
                (DateConverter) context.getBean("toMonths"),
                (DateConverter) context.getBean("toWeeks"),
                (DateConverter) context.getBean("toDays"),
                (DateConverter) context.getBean("toHours"),
                (DateConverter) context.getBean("toMinutes"),
                (DateConverter) context.getBean("toSeconds")
        );

        converters.stream()
                .map(dateConverter -> dateConverter.getClass().getSimpleName() +  ": "
                        +  dateConverter.convert(years))
                .forEach(System.out::println);
    }
}

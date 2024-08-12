package com.i2i.employeeManagement.util;

import java.time.LocalDate;
import java.time.Period;

public class EmployeeUtil {
    public static String ageCalculator(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears() + "Y " + period.getMonths() + "M";
    }

}

package com.i2i.employeeManagement.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * This class  is the validation class which will validate the fields given.
 * @author paari
 */
public class EmployeeValidator{

    /**
     * Method to validate the input string.
     * @return boolean
     */
    public static boolean stringValidator(String input) {
        for (char c : input.toCharArray()) {
            int lengthOfString = input.length();

            if (!Character.isLetter(c) || lengthOfString > 10) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to validate the Date of birth.
     * @return boolean
     */
    public static boolean dobValidator(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(dob,formatter);
        return true;
    }

    /**
     * Method to validate the experience
     * @return boolean
     */
    public boolean experienceValidator(int experience) {
        return experience >= 50 || experience < 0;
    }
}

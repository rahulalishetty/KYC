package main;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.logging.Logger;

public class ValidRangeFinder {


    public static final String DATE_FORMAT="dd-MM-yyyy";
    public static final int NUM_OF_DAYS_PLUS_OR_MINUS= 30;
    private static final  Logger logger = Logger.getLogger(ValidRangeFinder.class.getName());
    public static final boolean FALSE = false;
    public static final boolean TRUE = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        n=scanner.nextInt();
        scanner.nextLine();
        for(int i=0 ; i<n ;i++) {
            String signUpDateInput=scanner.next();
            String currentDateInput=scanner.next();
            String validRange = assignSignUpAndCurrentDates(signUpDateInput, currentDateInput);
            logger.info(validRange);
        }
    }

    /**
     * assign user input for signUp and current dates
     * and calls getRangeToFillForm to get valid range to fill form
     *
     */
    public static String assignSignUpAndCurrentDates(String signUpDateInput, String currentDateInput){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            LocalDate signUpDate = LocalDate.parse(signUpDateInput, formatter);
            LocalDate currentDate = LocalDate.parse(currentDateInput, formatter);
            return getRangeToFillForm(signUpDate,currentDate);
        } catch (Exception e) {
            logger.info("invalid format of date, please input date in format dd-MM-yyyy");
        }
        return "";
    }

    /**
     * takes the signUpDate and currentDate and
     * prints valid range in which user can fill up form
     *
     * @param   signUpDate, currentDate
     * @return  void
     *
     */
    static String getRangeToFillForm(LocalDate signUpDate, LocalDate currentDate){

        //checks if current date occurs before signup if it is the case
        //then it shows up the warning that there is no range possible
        if(isValidDateForRange(signUpDate, currentDate)== FALSE){
            logger.warning("No valid date range is found to fill form");
            return "";
        }

        LocalDate aniversaryDate=LocalDate.of(currentDate.getYear(),signUpDate.getMonth(), signUpDate.getDayOfMonth());
        LocalDate rangeStartDate=aniversaryDate.minusDays((long)NUM_OF_DAYS_PLUS_OR_MINUS);
        LocalDate rangeEndDate=aniversaryDate.plusDays((long)NUM_OF_DAYS_PLUS_OR_MINUS);

        //checks if range start date comes after the current date
        //if it occurs in future then logs the message and returns
        if(isValidDateForRange(rangeStartDate,currentDate) == FALSE){
            logger.warning("No valid date range is found to fill form");
            return "";
        }

        //if range end date is in future then it assigns current date as
        //range end date
        if(isValidDateForRange(rangeEndDate,currentDate) == FALSE){
            rangeEndDate=currentDate;
        }

        return getValidRangeToFillForm(rangeStartDate,rangeEndDate);
    }


    /**
     * checks if dates passed in first parameter occurs before second parameter
     *
     * @param   DateExpectedToBeInPast, DateExpectedToBeInFuture
     * @return returns true if the DateExpectedToBeInPast occurs before DateExpectedToBeInFuture
     *         returns false if the DateExpectedToBeInPast occurs after DateExpectedToBeInFuture
     *
     */
    static boolean isValidDateForRange(LocalDate dateExpectedToBeInPast,LocalDate dateExpectedToBeInFuture){
        return ChronoUnit.DAYS.between(dateExpectedToBeInPast ,dateExpectedToBeInFuture)<0 ? FALSE : TRUE;
    }

    /**
     * prints the range in which customer can fill form
     *
     * @param   rangeStartDate, rangeEndDate
     * @return void
     *
     */
    static String getValidRangeToFillForm(LocalDate rangeStartDate, LocalDate rangeEndDate){
        DecimalFormat formatForDayAndMonth=new DecimalFormat("00");
        return  formatForDayAndMonth.format(rangeStartDate.getDayOfMonth())+"-"
                +formatForDayAndMonth.format(rangeStartDate.getMonthValue())+"-"+
                rangeStartDate.getYear()+" "+formatForDayAndMonth.format(rangeEndDate.getDayOfMonth())+"-"+
                formatForDayAndMonth.format(rangeEndDate.getMonthValue())+"-"+rangeEndDate.getYear();

    }
}
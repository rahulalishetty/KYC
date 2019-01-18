import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

class Main {
    public static final int NUM_OF_DAYS_PLUS_OR_MINUS= 30;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=scanner.nextInt();
        scanner.nextLine();
        for(int i=0 ; i<n ;i++) {
            String signUpDateAsString,currentDateAsString;
            signUpDateAsString=scanner.next();
            currentDateAsString=scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate signUpDate=LocalDate.parse(signUpDateAsString,formatter);
            LocalDate currentDate=LocalDate.parse(currentDateAsString,formatter);
            getRangeToFillForm(signUpDate,currentDate);
        }
    }
    static void getRangeToFillForm(LocalDate signUpDate, LocalDate currentDate){
        if(isCurrentDateValid(signUpDate,currentDate)){
            System.out.println("No Range");
            return;
        }
        LocalDate aniversaryDate=LocalDate.of(currentDate.getYear(),signUpDate.getMonth(), signUpDate.getDayOfMonth());
        LocalDate rangeStartDate=aniversaryDate.minusDays((long)NUM_OF_DAYS_PLUS_OR_MINUS);
        LocalDate rangeEndDate=aniversaryDate.plusDays((long)NUM_OF_DAYS_PLUS_OR_MINUS);
        if(isValidDateForRange(rangeStartDate,currentDate)){
            System.out.println("No range");
            return;
        }
        if(isValidDateForRange(rangeEndDate,currentDate)){
            rangeEndDate=currentDate;
        }
        printRange(rangeStartDate,rangeEndDate);
    }
    static boolean isCurrentDateValid(LocalDate signUpDate, LocalDate currentDate){
        if(currentDate.getYear() <= signUpDate.getYear())
            return true;
        else
            return false;
    }
    static boolean isValidDateForRange(LocalDate rangeDate,LocalDate currentDate){
        if(ChronoUnit.DAYS.between(rangeDate ,currentDate)<0)
            return true;
        else
            return false;
    }
    static void printRange(LocalDate rangeStartDate, LocalDate rangeEndDate){
        DecimalFormat formatForDayAndMonth=new DecimalFormat("00");
        System.out.print(formatForDayAndMonth.format(rangeStartDate.getDayOfMonth())+"-"+formatForDayAndMonth.format(rangeStartDate.getMonthValue())+"-"+rangeStartDate.getYear());
        System.out.println(" "+formatForDayAndMonth.format(rangeEndDate.getDayOfMonth())+"-"+formatForDayAndMonth.format(rangeEndDate.getMonthValue())+"-"+rangeEndDate.getYear());
    }
}
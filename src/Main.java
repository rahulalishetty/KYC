import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        scanner.nextLine();
        for(int i=0;i<n;i++){
            String signUpDate,currentDate;
            signUpDate=scanner.next();
            currentDate=scanner.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate signUpLocalDate=LocalDate.parse(signUpDate,formatter);
            LocalDate currentLocalDate=LocalDate.parse(currentDate,formatter);
            if(ChronoUnit.DAYS.between(signUpLocalDate,currentLocalDate)<0){
                System.out.println("No range");
                continue;
            }
            LocalDate aniversaryDate=LocalDate.of(currentLocalDate.getYear(),signUpLocalDate.getMonth(), signUpLocalDate.getDayOfMonth());
            LocalDate rangeStartDay=aniversaryDate.minusDays((long)30);
            if(ChronoUnit.DAYS.between(rangeStartDay,currentLocalDate)<0){
                System.out.println("No range");
                continue;
            }
            LocalDate rangeEndDate=aniversaryDate.plusDays((long)30);
            if(ChronoUnit.DAYS.between(rangeEndDate,currentLocalDate)<0){
                rangeEndDate=currentLocalDate;
            }
            DecimalFormat formatForDayAndMonth=new DecimalFormat("00");
            System.out.print(formatForDayAndMonth.format(rangeStartDay.getDayOfMonth())+"-"+formatForDayAndMonth.format(rangeStartDay.getMonthValue())+"-"+rangeStartDay.getYear());
            System.out.println(" "+formatForDayAndMonth.format(rangeEndDate.getDayOfMonth())+"-"+formatForDayAndMonth.format(rangeEndDate.getMonthValue())+"-"+rangeEndDate.getYear());
        }
    }
}



import main.ValidRangeFinder;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.*;

public class ValidRangeFinderTest {

    static ValidRangeFinder validRangeFinder=new ValidRangeFinder();

    @Test
    public void testValidCase() {
        //when current date is between range
        String signUpDate="16-07-1998";
        String currentDate= "27-06-2017";
        String result = validRangeFinder.assignSignUpAndCurrentDates(signUpDate, currentDate);
        assertEquals("16-06-2017 27-06-2017", result);
    }

    @Test
    public void testCornerCaseOne(){
        //when the current date is before signupdate
        String signUpDate="04-05-2017";
        String currentDate= "04-04-2017";
        String result = validRangeFinder.assignSignUpAndCurrentDates(signUpDate, currentDate);
        assertEquals("", result);
    }

    @Test
    public void testCornerCaseTwo(){
        //when current date is before rangeStartDate
        String signUpDate="16-07-1998";
        String currentDate= "15-06-2017";
        String result = validRangeFinder.assignSignUpAndCurrentDates(signUpDate, currentDate);
        assertEquals("", result);
    }

    @Test
    public void testCornerCaseThree(){
        //when current date is after rangeEndDate
        String signUpDate="04-02-2016";
        String currentDate= "04-04-2017";
        String result = validRangeFinder.assignSignUpAndCurrentDates(signUpDate, currentDate);
        assertEquals("05-01-2017 06-03-2017", result);
    }




}
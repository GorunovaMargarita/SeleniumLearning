package litecart.test;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserRegistration extends TestBase{
    @Test
    public void UserRegistrationTest(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
        String timeSt= dateFormat.format(date);
        String email = "TestEmail" + timeSt + "@gmail.com";
        String pass = "TestPass";
        String firstName = "TestUser"+timeSt;
        String lastName = "TestUser" +timeSt;
        String postcode = "18160";
        String address = "Address" + timeSt;
        String city = "New York";
        String country = "United States";
        String phone = timeSt;
        app.userRegistration(firstName,lastName,address,postcode,city,country,email,phone,pass);
        app.logout();
        app.login(email,pass);
    }
}

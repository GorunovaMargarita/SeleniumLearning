package litecart.test;

import org.junit.Test;
import org.openqa.selenium.By;

import java.io.File;

public class AddNewProduct extends TestBase{
    @Test
    public void addNewProductTest(){
        app.loginAdmin();
        String name = "Name";
        String code = "Code";
        String category = "Rubber Ducks";
        String defaultCategory = "Rubber Ducks";
        String productGroupsGender = "Male";
        File photo= new File("src/test/resources/3.png");
        String quantity = "2";
        String quantityUnit = "pcs";
        String deliveryStatus = "3-5 days";
        String soldOutStatus = "Temporary sold out";
        String dateValidFrom = "01012021";
        String dateValidTo = "31122021";
        app.addNewProduct(name,code,category,defaultCategory,productGroupsGender,
                            photo,quantity,quantityUnit,deliveryStatus,soldOutStatus,dateValidFrom,dateValidTo);
    }
}

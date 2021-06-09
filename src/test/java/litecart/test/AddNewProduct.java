package litecart.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewProduct extends TestBase{
    @Test
    public void addNewProductTest(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
        String timeSt= dateFormat.format(date);
        app.loginAdmin();
        String name = "Name" + timeSt;
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
        String manufacturer = "ACME Corp.";
        String keywords = "key";
        String shortDesc = "String shortDesc";
        String desc = "Такое описание вот тут";
        String headTitle = name + code;
        String metaDesc = "metaDescription";
        String price = "444";
        String currency = "Euros";
        String taxClass = "second";
        String priceWithTaxUSD = "500";
        String priceWithTaxEUR = "600";
        app.addNewProduct(name,code,category,defaultCategory,productGroupsGender,
                            photo,quantity,quantityUnit,deliveryStatus,soldOutStatus,dateValidFrom,dateValidTo,
                            manufacturer, null,keywords, shortDesc, desc, headTitle, metaDesc,
                            price, currency, taxClass, priceWithTaxUSD, priceWithTaxEUR);
        Assert.assertTrue(app.isProductExist(name));
    }
}

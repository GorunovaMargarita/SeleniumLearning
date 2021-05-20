package litecart.test;
import org.junit.Test;


import java.io.IOException;


public class Login extends TestBase {

  @Test
  public void loginTest() throws IOException {
    app.login("mail@mail.ru","TestUser");
  }



}

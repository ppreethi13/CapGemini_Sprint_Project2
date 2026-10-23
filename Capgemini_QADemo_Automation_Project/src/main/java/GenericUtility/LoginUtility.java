
package GenericUtility;

import org.openqa.selenium.WebDriver;
import qademoPom.LOGINPOM;

public class LoginUtility {

    ExcelUtility eu = new ExcelUtility();

    public void validUserSignIn(WebDriver driver) throws Exception {

        String email = eu.getData("SignIn", 1, 0);
        String password = eu.getData("SignIn", 1, 1);

        LOGINPOM login = new LOGINPOM(driver);

        login.clickSignInLink();
        login.getEmail(email);
        login.getPassword(password);
        Thread.sleep(1000);
        login.clickSignIn();

        Thread.sleep(2000);
    }
}

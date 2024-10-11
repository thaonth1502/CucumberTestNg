package thaonth7.pages;

import org.openqa.selenium.By;
import thaonth7.keywords.WebUI;
import static thaonth7.keywords.WebUI.*;

public class LoginPage {

    // Khai báo Objects
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By bntLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[@id='alerts']");


    //Hàm xử lý đặc trưng cho Login Page

    public void userEnterEmailAndPassword(String email, String password){
        setText(inputEmail, email);
        setText(inputPassword, password);
    }

    public void clickLoginButton(){
        clickElement(bntLogin);
    }

    public void checkErrorMessage (String message){
        getElementText(errorMessage);
    }
}

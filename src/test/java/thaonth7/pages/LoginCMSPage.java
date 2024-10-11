package thaonth7.pages;

import org.openqa.selenium.By;
import thaonth7.common.BaseTest;
import thaonth7.constants.ConfigData;

import static thaonth7.keywords.WebUI.*;

public class LoginCMSPage {

    // Khai báo Objects
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By bntLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//span[@data-notify='message']");


    //Hàm xử lý đặc trưng cho Login Page

    public void userEnterEmailAndPassword(String email, String password){
        setText(inputEmail, email);
        setText(inputPassword, password);
    }

    public void clickLoginButton(){
        clickElement(bntLogin);
    }

    public void checkErrorMessage(String message){
        getElementText(errorMessage);
    }

    public void checkEmailRequired(String message){
        String validateMessage = getWebElement(inputEmail).getAttribute("validationMessage");
        assertEquals(validateMessage, message, "Email required message is not display.");
    }

    public void checkPasswordRequired(String message){
        String validateMessage = getWebElement(inputPassword).getAttribute("validationMessage");
        assertEquals(validateMessage, message, "Password required message is not display.");
    }

    public CommonPage loginAdminRole(){
        BaseTest.createDriver();
        openURL(ConfigData.URL);
        setText(inputEmail, ConfigData.EMAIL);
        setText(inputPassword, ConfigData.PASSWORD);
        clickElement(bntLogin);
        return new CommonPage();

    }
}

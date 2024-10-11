package thaonth7.pages;

import org.openqa.selenium.By;
import thaonth7.helpers.ExcelHelper;

import static thaonth7.keywords.WebUI.*;

public class CategoriesPage {

    private By btnAddNewCategory = By.xpath("//span[normalize-space()='Add New category']");
    private By inputName = By.xpath("//input[@id='name']");
    private By btnSave = By.xpath("//button[normalize-space()='Save']");
    private By messageSuccess = By.xpath("//span[@data-notify='message']");



    public void clickAddNewCategoryButton(){
        clickElement(btnAddNewCategory);
    }

    public void inputValidValueIntoRequiredFields(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/dataTest/CMS.xlsx", "Category");
        String categoryName = excelHelper.getCellData("CATEGORY_NAME", 1);
        setText(inputName, categoryName);
    }

    public void clickSaveButton(){
        clickElement(btnSave);
    }

   public void checkMessageSuccessful(String expectedMessage){
        String actualMessage = getElementText(messageSuccess);
        assertEquals(actualMessage, expectedMessage, "FAIL! the message not display");
   }

}

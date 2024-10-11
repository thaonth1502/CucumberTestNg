package thaonth7.pages;

import org.openqa.selenium.By;
import thaonth7.keywords.WebUI;

import static thaonth7.keywords.WebUI.*;

public class CommonPage {

    private By menuProducts = By.xpath("//span[normalize-space()='Products']");
    private By menuCategory = By.xpath("//span[normalize-space()='Category']");
    private By menuAddNewProduct = By.xpath("//span[normalize-space()='Add New Product']");
    private By menuAllProducts = By.xpath("//span[normalize-space()='All products']");

    public void clickMenuProducts(){
       clickElement(menuProducts);
    }

    public CategoriesPage openCategoriesPage(){
        clickElement(menuCategory);
        return new CategoriesPage();
    }
}

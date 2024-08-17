package pages.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Cart {
    private BasePage basePage;
    private WebDriver driver;

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkoutButton;

    public Cart(BasePage basePage) {
        this.basePage = basePage;
        this.driver = basePage.getWebDriver();
        PageFactory.initElements(this.driver, this);
    }


    public void productsCheckout(){
        //hacer las demas validaciones
        try{
            Thread.sleep(3000);
            basePage.wait_2s.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void siSequiereRemoverAlgo(){
        //agregar la logica
    }

    private void otrosMetodosQueSePuedenAgregar(){
        //otros metodos que se pueden agregar

    }
}

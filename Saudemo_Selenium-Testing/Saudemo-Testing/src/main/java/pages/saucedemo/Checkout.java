package pages.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Checkout {
    private BasePage basePage;
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement zip;
    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;
    @FindBy(xpath = "//div[@class='error-message-container error']")
    private WebElement errorModalInformation;
    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishButton;
    @FindBy(xpath = "//*[@id='back-to-products']")
    private WebElement backHomeButton;

    public Checkout(BasePage basePage) {
        this.basePage = basePage;
        this.driver = basePage.getWebDriver();
        PageFactory.initElements(this.driver, this);
    }

    public void addCheckout(){
        try {
            addInformationCheckout();
            validatePrice();
            Thread.sleep(2000);
            basePage.scrollToWebElement(finishButton);
            Thread.sleep(2000);
            basePage.wait_2s.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
            validateOrder();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void addInformationCheckout() throws Exception{
        try {
            basePage.wait_5s.until(ExpectedConditions.elementToBeClickable(firstName)).click();
            firstName.sendKeys("Pardo");
            basePage.wait_5s.until(ExpectedConditions.elementToBeClickable(lastName)).click();
            lastName.sendKeys("Pardito");
            basePage.wait_5s.until(ExpectedConditions.elementToBeClickable(zip)).click();
            zip.sendKeys("1234");
            Thread.sleep(3000);
            basePage.wait_2s.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
            //reporte de funciono
        }catch (Exception e){
            //reporte de fallido
            e.printStackTrace();
            throw e;
        }
    }

    private void validatePrice(){
        //este metodo se utilizaria para validar el monto total
    }

    private void validateOrder(){
        //validar que la compra finalizao con exito
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}

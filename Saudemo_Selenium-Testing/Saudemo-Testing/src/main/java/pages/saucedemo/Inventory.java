package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class Inventory {
    private BasePage basePage;
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='title']")
    private WebElement productsH;
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement cartShopping;
    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement buttonOpenMenu;
    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    private WebElement logoutButton;

    public Inventory(BasePage basePage) {
        this.basePage = basePage;
        this.driver = basePage.getWebDriver();
        PageFactory.initElements(this.driver, this);
    }

    public void addProducts(){
        //se puede agrega
        String[] nameProducts = {"Sauce Labs Backpack", "Sauce Labs Bike Light"};
        try{
            clickProductByName(nameProducts);
            basePage.scrollToWebElement(cartShopping);
            basePage.wait_2s.until(ExpectedConditions.elementToBeClickable(cartShopping)).click();
        }catch (Exception e){
            //manejar reporte como: "no se ah agregado el producto o algo asi ya que depende de mas metodos y tal"
            e.printStackTrace();
        }

    }

    public void logout() throws Exception{
        basePage.wait_2s.until(ExpectedConditions.elementToBeClickable(buttonOpenMenu)).click();
        Thread.sleep(2000);
        basePage.wait_5s.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        Thread.sleep(4000);
    }

    private void clickProductByName(String[] productNames){
        //validar que el producto exista
        for(String productName : productNames){
            String formattedProductName = productName.toLowerCase().trim().replace(" ", "-");
            String path = "//*[@id='add-to-cart-" + formattedProductName + "']";

            try{
                WebElement pathElement = driver.findElement(By.xpath(path));
                basePage.scrollToWebElement(pathElement);
                basePage.wait_5s.until(ExpectedConditions.elementToBeClickable(pathElement)).click();
                // Reporte con exito aquí
                Thread.sleep(2000);

                //ESTOS CATCH SON UN EJEMPLO, USARLOS DEPENDERA DE QUE SE NECESITA HACER Y DE CADA UNO
            }catch (NoSuchElementException e) {
                // El elemento no se encontró
                // Reporte de error aquí
            } catch (TimeoutException e) {
                // El elemento no se volvió clickable en el tiempo esperado
                // Reporte de error aquí
            } catch (Exception e) {
                // Captura cualquier otra excepción inesperada
                // Reporte de error aquí
            }
        }
    }

    private void validateLogout(){
        //verificar que se hizo logout
    }

}

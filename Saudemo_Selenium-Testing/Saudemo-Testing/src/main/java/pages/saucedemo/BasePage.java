package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitDriver;
import java.time.Duration;
import java.util.NoSuchElementException;

public class BasePage {
    //public static Logger logger = (Logger) LogManager.getLogger();
    protected WebDriver driver;
    protected WaitDriver waitDriver;
    protected WebDriverWait wait;
    protected WebDriverWait wait_2s;
    protected WebDriverWait wait_5s;
    protected WebDriverWait wait_10s;

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement inputUser;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement inputPassword;
    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;
    @FindBy(xpath = "//div[@class='login_logo']")
    private WebElement swagLabs;
    @FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]/h3")
    private WebElement modalError;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitDriver = new WaitDriver(driver);

        wait_10s = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait_5s = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait_2s = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, this);
    }

    public void loginSauceDemo() throws Throwable{
        //validar que swag labs aparezca
        //ingresar los datos del login
        //user: standard_user , password: secret_sauce
        //si aparece el modal de error agrega un fail
        String URL = "https://www.saucedemo.com/";
        driver.navigate().to(URL);
        try{
            login();
            /*
            if(elementIsDisplayed(modalError)){
                //manejar el modal de error ya que podria ser el user or password
            }*/

            Thread.sleep(3000);

        }catch (Throwable e){
            throw new Throwable(e);
        }
    }

    private void login(){
        try {
            wait_5s.until(ExpectedConditions.elementToBeClickable(inputUser)).click();
            inputUser.sendKeys("standard_user");
            Thread.sleep(2000);
            wait_5s.until(ExpectedConditions.elementToBeClickable(inputPassword)).click();
            inputPassword.sendKeys("secret_sauce");
            Thread.sleep(2000);
            wait_5s.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }


    //metodos sobre los webElements
    protected boolean elementIsDisplayed(WebElement webElement){
        try {
            // El método devuelve true si el elemento está visible y false si no lo está.
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            // Si se captura esta excepción, significa que el elemento no está presente en el DOM.
            //logger.warn("No existe el elemento: " + webElement.toString());
            return false;
        }
    }

    public void click(By elementLocation) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }

    public void waitVisibility(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //GETTERS AND SETTERS
    protected WebDriver getWebDriver() {
        return this.driver;
    }

    protected void scrollToWebElement(WebElement element) throws InterruptedException {
        JavascriptExecutor javaScriptExecutor = (JavascriptExecutor) driver;
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        javaScriptExecutor.executeScript(scrollElementIntoMiddle, element);
        Thread.sleep(1000);
    }
}

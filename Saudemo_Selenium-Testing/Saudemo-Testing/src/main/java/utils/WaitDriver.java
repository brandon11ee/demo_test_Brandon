package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitDriver {
    private WebDriverWait wait;
    private WebDriver driver;
    private static final int DEFAULT_TIMEOUT = 1;

    public WaitDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }


    /**
     * Espera hasta que el elemento sea clickable y luego realiza una accion de clic
     *
     * @param nameObject WebElement que se espera que se clickable
     * @param timeout    Tiempo maximo, en segundos para esperar hasta que el elemento sea clickable
     */
    public void waitClick(WebElement nameObject, long timeout){
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        waiting.until(ExpectedConditions.elementToBeClickable(nameObject)).click();
    }


    /**
     * Espera hasta que el elemento sea clickable y luego envía los datos especificados
     *
     * @param data       Los datos que se enviarán al elemento
     * @param nameObject Elemento web al que se enviaran los datos
     * @param timeout    tiempo maximo, en segundos, para esperar hasta que el elemento sea clickable
     */
    public void waitSendKeys(String data, WebElement nameObject, long timeout){
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        waiting.until(ExpectedConditions.elementToBeClickable(nameObject)).sendKeys(data);
    }


    /**
     * Espera hasta que el elemento especificado sea clickable.
     *
     * @param nameObject Elemento web que se espera que sea clickable.
     * @param timeout    Tiempo máximo, en segundos, para esperar hasta que el elemento sea clickable.
     */
    public void waitElementToBeClickeable(WebElement nameObject, long timeout){
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        waiting.until(ExpectedConditions.elementToBeClickable(nameObject));
    }


    /**
     * Espera hasta que el elemento especificado sea visible en la página.
     *
     * @param nameObject Elemento web que se espera que sea visible.
     * @param timeout    Tiempo máximo, en segundos, para esperar hasta que el elemento sea visible.
     */
    public void waitVisibilityOf(WebElement nameObject, long timeout){
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        waiting.until(ExpectedConditions.visibilityOf(nameObject));
    }


    /**
     * Espera hasta que un elemento identificado por el selector (By) sea visible en la página.
     *
     * @param by Selector que identifica el elemento a esperar.
     */
    public void waitVisibility(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    /**
     * Espera hasta que un elemento ubicado por un XPath específico sea visible en la página.
     *
     * @param xpath    El XPath del elemento que se desea esperar.
     * @param timeout  Tiempo máximo, en segundos, para esperar hasta que el elemento sea visible.
     */
    public void waitVisibilityOfElementedLocated(String xpath, long timeout) {
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        waiting.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Espera hasta que un elemento ubicado por un XPath específico no sea visible en la página.
     *
     * @param xpath    El XPath del elemento que se desea esperar que no sea visible.
     * @param timeout  Tiempo máximo, en segundos, para esperar hasta que el elemento no sea visible.
     */
    public boolean waitInVisibilityOfElementedLocated(String xpath, long timeout) {
        WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return waiting.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }
}


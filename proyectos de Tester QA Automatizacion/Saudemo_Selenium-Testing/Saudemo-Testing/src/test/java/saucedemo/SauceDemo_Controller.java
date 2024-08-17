package saucedemo;

import net.lightbody.bmp.BrowserMobProxyServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.saucedemo.BasePage;
import utils.DriverManager;

public class SauceDemo_Controller {
    protected WebDriver driver;
    protected BrowserMobProxyServer proxyServer;
    protected BasePage basePage;
    private boolean readHTTP = false;

    @BeforeAll
    public static void initializeRegression(){
        //agregar algo si es necesario para iniciar los test
    }

    @BeforeEach
    public void runBeforeEachTest(){
        try{
            //colocar el navegador a usar y si usa proxy
            DriverManager driverManager = new DriverManager("chrome", false);
            driver = driverManager.getDriver();
            proxyServer = driverManager.getProxy();
            Thread.sleep(2000);
        }catch (Exception t){
            t.printStackTrace();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void teardown(){
        //agregar lo necesario para despues de cada test
        driver.quit();
        if (readHTTP) {
            proxyServer.abort();
        }
    }

    public static void endRegression(){
        try{
            //colocar lo necesario para despues de finalizar los test completos
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

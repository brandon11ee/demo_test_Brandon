package saucedemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import io.github.artsok.ParameterizedRepeatedIfExceptionsTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.saucedemo.BasePage;
import pages.saucedemo.Cart;
import pages.saucedemo.Checkout;
import pages.saucedemo.Inventory;

@DisplayName("Test Sauce Demo")

@Execution(ExecutionMode.SAME_THREAD)
public class SauceDemo_Test extends SauceDemo_Controller{

    @DisplayName("Regresión")
    @Test
    void test() throws Throwable {
        BasePage baseP = new BasePage(driver);

        //flujo a iniciar
        baseP.loginSauceDemo();
        new Inventory(baseP).addProducts();
        new Cart(baseP).productsCheckout();
        new Checkout(baseP).addCheckout();
        new Inventory(baseP).logout();

        //reporte de finaliza la prueba
    }
}

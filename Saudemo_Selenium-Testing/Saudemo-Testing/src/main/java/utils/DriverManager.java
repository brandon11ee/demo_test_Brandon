package utils;

import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;
import net.lightbody.bmp.BrowserMobProxyServer;

public class DriverManager {
    private final String browser;
    private final boolean useProxy;
    private BrowserMobProxyServer proxyServer = null;
    private String downloadPath = "";

    final String userAgent = "CM/Performance/02.10.23";

    public DriverManager(String browser, boolean useProxy) throws Exception {
        if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("firefox")
                || browser.equalsIgnoreCase("edge")) {
            this.browser = browser;
            this.useProxy = useProxy;

        } else {
            throw new Exception(browser + " no está implementado.");
        }
    }

    public DriverManager(String browser, String downloadPath) throws Exception {
        if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("firefox")
                || browser.equalsIgnoreCase("edge")) {
            this.browser = browser;
            this.useProxy = false;
            this.downloadPath = downloadPath;

        } else {
            throw new Exception(browser + " no está implementado.");
        }
    }

    public BrowserMobProxyServer getProxy() {
        return proxyServer;
    }

    public WebDriver getDriver() throws Throwable {
        Proxy proxyConfig = null;
        try {
            /*List<Proxy> proxies = ProxySelector.getDefault().select(new URI("https://www.example.com"));*/
            if (useProxy) {
                this.proxyServer = new BrowserMobProxyServer();
                proxyServer.start();
                proxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT,
                        CaptureType.REQUEST_HEADERS);
                proxyServer.newHar();
                proxyConfig = ClientUtil.createSeleniumProxy(proxyServer);

                String hostIp = Inet4Address.getLocalHost().getHostAddress();
                proxyConfig.setHttpProxy(hostIp + ":" + proxyServer.getPort());
                proxyConfig.setSslProxy(hostIp + ":" + proxyServer.getPort());

            }

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--user-agent=" + userAgent, "incognito", "--start-maximized","--delete-all-cookies");
                    chromeOptions.addArguments("incognito", "--start-maximized","--delete-all-cookies");
                    chromeOptions.setAcceptInsecureCerts(true);
                    chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    chromeOptions.setExperimentalOption("useAutomationExtension", false);

                    if (useProxy) {
                        chromeOptions.setProxy(proxyConfig);
                        chromeOptions.setCapability(CapabilityType.PROXY, proxyConfig);
                    }

                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("profile.default_content_settings.popups", 0);
                    prefs.put("download.prompt_for_download", "false");
                    prefs.put("download.default_directory", downloadPath);
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    chromeOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                    chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    return new ChromeDriver(chromeOptions);

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addPreference("general.useragent.override", userAgent);
                    firefoxOptions.addArguments("--start-maximized", "--delete-all-cookies", "--auto-open-devtools-for-tabs");
                    firefoxOptions.setAcceptInsecureCerts(true);
                    if (useProxy) {
                        firefoxOptions.setProxy(proxyConfig);
                        firefoxOptions.setCapability(CapabilityType.PROXY, proxyConfig);
                    }
                    firefoxOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                    firefoxOptions.setCapability(ChromeOptions.CAPABILITY, firefoxOptions);
                    firefoxOptions.setCapability("acceptInsecureCerts", true);
                    return new FirefoxDriver(firefoxOptions);

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("start-maximized", "-inprivate", "--user-agent=" + userAgent, "--delete-all-cookies");
                    if (useProxy) {
                        edgeOptions.setProxy(proxyConfig);
                        edgeOptions.setCapability(CapabilityType.PROXY, proxyConfig);
                    }
                    //edgeOptions.addArguments("--auto-open-devtools-for-tabs");
                    edgeOptions.setAcceptInsecureCerts(true);
                    edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                    edgeOptions.setExperimentalOption("useAutomationExtension", false);
                    Map<String, Object> edgePrefs = new HashMap<>();
                    edgePrefs.put("credentials_enable_service", false);
                    edgePrefs.put("profile.password_manager_enabled", false);
                    edgePrefs.put("profile.default_content_settings.popups", 0);
                    edgePrefs.put("download.prompt_for_download", "false");
                    edgePrefs.put("download.default_directory", downloadPath);
                    edgeOptions.setExperimentalOption("prefs", edgePrefs);
                    edgeOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                    edgeOptions.setCapability(ChromeOptions.CAPABILITY, edgeOptions);
                    return new EdgeDriver(edgeOptions);

                default:

                    throw new Exception(browser + " no está implementado.");
            }

        }
        catch (Throwable t) {
            t.printStackTrace();
            throw t;
        }
    }



    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--user-agent=" + userAgent);
        chromeOptions.addArguments("incognito", "--start-maximized", "--delete-all-cookies");
        chromeOptions.setAcceptInsecureCerts(true);
        // ... (otros ajustes que ya tienes)

        // Añade las preferencias que tenías antes
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", "false");
        prefs.put("download.default_directory", downloadPath);
        chromeOptions.setExperimentalOption("prefs", prefs);

        chromeOptions.setCapability("unhandledPromptBehavior", UnexpectedAlertBehaviour.ACCEPT);
        chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return chromeOptions;
    }


}

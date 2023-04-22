package com.effecti.etapaencontrar.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class WebScrapingConfig {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Dell Optiplex 7010/Downloads/chromedriver_win32");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("sites/comprasnet_principal.html");
//        driver.get("http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp");
        driver.quit();
    }

}


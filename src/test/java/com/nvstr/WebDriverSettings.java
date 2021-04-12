package com.nvstr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebDriverSettings {
    // Headers for a request
    String username = "autoqa.devops@gmail.com";
    String pass = "Test54321";
    String Content_Type = "application/json";
    String Accept = "application/json";
    String uid = "autoqa.devops@gmail.com";
    String accesstoken = "y8_6LXSNS0BUNJ23Jk-fog";
    String client = "XWOO-c1bA6twlc9rozVVJg";
    String expiry = "1933719368";


    public WebDriver driver;

    public static final String HUB_URL = "http://localhost:4444/wd/hub";

    private static boolean remoteWebDriver = false;
    static {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(HUB_URL + "/status").openConnection();
            try {
                con.setRequestMethod("GET");
                remoteWebDriver = con.getResponseCode() == HttpURLConnection.HTTP_OK;
            } finally {
                con.disconnect();
            }
        } catch (IOException ignore) {}

        if (!remoteWebDriver) {
            WebDriverManager.chromedriver().setup();
        }
    }

}

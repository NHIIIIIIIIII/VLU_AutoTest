package utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class AuthenticationMicrosoftBypass {
    private static final String COOKIES = "COOKIES";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String CONFIG_PROPERTIES_FILE = "config.properties";
    private static final String LOGIN_URL = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login";

    // Lấy cookies từ lần đăng nhập đầu tiên
    public String getCookiesFromFirstLogin(long timeToAuthentication) throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.navigate().to(LOGIN_URL);


        webDriver.findElement(By.id("OpenIdConnect")).click();

        // Đợi quá trình xác thực (Microsoft login)
        Thread.sleep(timeToAuthentication);

        // Nhấn nút Back (từ chối MFA nếu cần)
//        webDriver.findElement(By.id("idBtn_Back")).click();
//        Thread.sleep(2000);

        // Lấy cookies
        Set<Cookie> cookiesSet = webDriver.manage().getCookies();
        String cookiesString = cookiesToString(cookiesSet);

        // Lưu cookies vào file properties
        setCookiesToPropertiesFile(cookiesSet);

        webDriver.close();
        return cookiesString;
    }

    // Đăng nhập vào website bằng cookies
    public WebDriver loginToWebsite(String cookiesString) throws InterruptedException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.navigate().to(LOGIN_URL);

//        // Vượt qua cảnh báo bảo mật
//        webDriver.findElement(By.id("details-button")).click();
//        Thread.sleep(1500);
//        webDriver.findElement(By.id("proceed-link")).click();
//        Thread.sleep(1500);
        webDriver.findElement(By.id("OpenIdConnect")).click();

        // Thêm cookies từ chuỗi
        if (cookiesString != null && !cookiesString.isEmpty()) {
            String[] cookiePairs = cookiesString.split("; ");
            for (String pair : cookiePairs) {
                String[] nameValue = pair.split("=", 2);
                if (nameValue.length == 2) {
                    String cookieName = nameValue[0].replace("[", "").trim();
                    String cookieValue = nameValue[1].trim();
                    webDriver.manage().addCookie(new Cookie(cookieName, cookieValue));
                }
            }
        }

        // Refresh để áp dụng cookies
        webDriver.navigate().refresh();
        Thread.sleep(2000);

        return webDriver;
    }

    // Lấy cookies từ file properties
    public String getCookiesFromPropertiesFile() {
        Properties properties = loadProperties();
        if (properties != null) {
            return properties.getProperty(COOKIES);
        }
        System.out.println("Không thể đọc cookies từ file " + CONFIG_PROPERTIES_FILE);
        return null;
    }

    // Lưu cookies vào file properties
    public void setCookiesToPropertiesFile(Set<Cookie> cookies) {
        try {
            Properties properties = loadProperties();
            if (properties == null) {
                properties = new Properties();
            }

            String cookiesString = cookiesToString(cookies);
            properties.setProperty(COOKIES, cookiesString);

            String[] credentials = getUsernameAndPasswordFromPropertiesFile();
            if (credentials != null) {
                properties.setProperty(USERNAME, credentials[0]);
                properties.setProperty(PASSWORD, credentials[1]);
            }

            try (FileOutputStream out = new FileOutputStream(CONFIG_PROPERTIES_FILE)) {
                properties.store(out, "Cấu hình cookies và thông tin đăng nhập");
                System.out.println("Đã lưu cookies vào " + CONFIG_PROPERTIES_FILE);
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu cookies: " + e.getMessage());
        }
    }

    // Lấy username và password từ file properties
    public String[] getUsernameAndPasswordFromPropertiesFile() {
        Properties properties = loadProperties();
        if (properties != null) {
            String username = properties.getProperty(USERNAME);
            String password = properties.getProperty(PASSWORD);
            if (username != null && password != null) {
                return new String[]{username, password};
            }
        }
        System.out.println("Không thể đọc username/password từ " + CONFIG_PROPERTIES_FILE);
        return null;
    }

    // Load file properties
    private Properties loadProperties() {
        File configFile = new File(CONFIG_PROPERTIES_FILE);
        if (!configFile.exists()) {
            System.out.println("File " + CONFIG_PROPERTIES_FILE + " không tồn tại.");
            return null;
        }

        try (InputStream input = new FileInputStream(configFile)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file properties: " + e.getMessage());
            return null;
        }
    }

    // Chuyển Set<Cookie> thành chuỗi
    private String cookiesToString(Set<Cookie> cookies) {
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookies) {
            sb.append(cookie.getName()).append("=").append(cookie.getValue()).append("; ");
        }
        return sb.toString().trim();
    }
}

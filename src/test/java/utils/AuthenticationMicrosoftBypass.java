package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static base.DriverConfig.sleep;


public class AuthenticationMicrosoftBypass {
    private static final String COOKIES = "COOKIES";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String CONFIG_PROPERTIES_FILE = "config.properties";
    private static final String LOGIN_URL = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login";
    private static final String COOKIE_DOMAIN = "cntttest.vanlanguni.edu.vn";
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_LOGIN_WAIT = 30000;
    
    private final WebDriver webDriver;
    private final Properties properties;

    public AuthenticationMicrosoftBypass(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.properties = loadProperties();
    }

    public boolean setBrowserCookiesFromProperties() {
        if (properties == null) {
            System.out.println("Không thể đọc file config.properties");
            return false;
        }

        String cookiesString = properties.getProperty(COOKIES);
        if (cookiesString == null || cookiesString.isEmpty()) {
            System.out.println("Không tìm thấy COOKIES trong file config.properties");
            return false;
        }

        try {
            webDriver.navigate().to(LOGIN_URL);
            
            cookiesString = parseCookieString(cookiesString);
            Cookie authCookie = extractAuthenticationCookie(cookiesString);
            
            if (authCookie != null) {
                webDriver.manage().addCookie(authCookie);
                System.out.println("Đã thêm cookie xác thực: " + authCookie.getName());
                
                webDriver.navigate().refresh();
                sleep(2000);
                
                boolean loginSuccess = !webDriver.getCurrentUrl().contains("Login");
                System.out.println(loginSuccess ? 
                    "Đăng nhập thành công bằng cookies!" : 
                    "Cookies không hoạt động, cần đăng nhập thủ công...");
                    
                return loginSuccess;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi thiết lập cookies: " + e.getMessage());
        }
        return false;
    }

    private String parseCookieString(String cookiesString) {
        if (cookiesString.startsWith("[") && cookiesString.endsWith("]")) {
            cookiesString = cookiesString.substring(1, cookiesString.length() - 1);
        }
        return cookiesString.replace("\\=", "=");
    }

    private Cookie extractAuthenticationCookie(String cookiesString) {
        if (!cookiesString.contains(".AspNet.Cookies")) {
            return null;
        }

        int startIndex = cookiesString.indexOf(".AspNet.Cookies");
        int endIndex = cookiesString.indexOf(";", startIndex);
        if (endIndex == -1) endIndex = cookiesString.length();
        
        String cookiePair = cookiesString.substring(startIndex, endIndex);
        String[] parts = cookiePair.split("=", 2);
        
        if (parts.length == 2) {
            return new Cookie(parts[0].trim(), parts[1].trim(), COOKIE_DOMAIN, "/", null, true, true);
        }
        return null;
    }

    public String getCookiesFromFirstLogin(long timeToAuthentication) {
        try {
            webDriver.navigate().to(LOGIN_URL);
            webDriver.findElement(By.id("OpenIdConnect")).click();

            System.out.println("Vui lòng đăng nhập thủ công trong " + (timeToAuthentication / 1000) + " giây...");
            sleep(timeToAuthentication);

            if (!webDriver.getCurrentUrl().contains("Login")) {
                System.out.println("Đăng nhập thành công!");
                Set<Cookie> cookiesSet = webDriver.manage().getCookies();
                saveCookiesToProperties(cookiesSet);
                return cookiesToString(cookiesSet);
            }
            
            System.out.println("Đăng nhập thất bại hoặc hết thời gian!");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi trong quá trình đăng nhập: " + e.getMessage());
            return null;
        }
    }

    public void loginToWebsite(Set<Cookie> cookies) {
        try {
            webDriver.navigate().to(LOGIN_URL);
            new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.urlToBe(LOGIN_URL));

            if (cookies == null) {
                if (setBrowserCookiesFromProperties()) {
                    return;
                }
                cookies = getCookiesFromPropertiesFile();
            }

            if (cookies != null && !cookies.isEmpty()) {
                handleExistingCookies(cookies);
                return;
            }

            System.out.println("Không tìm thấy cookies, cần đăng nhập thủ công.");
            getCookiesFromFirstLogin(DEFAULT_LOGIN_WAIT);
        } catch (Exception e) {
            System.out.println("Lỗi trong quá trình đăng nhập: " + e.getMessage());
        }
    }

    private WebDriver handleExistingCookies(Set<Cookie> cookies) {
        webDriver.findElement(By.id("OpenIdConnect")).click();
        sleep(2000);

        addCookiesToDriver(cookies);
        webDriver.navigate().refresh();
        sleep(3000);

        if (!webDriver.getCurrentUrl().contains("Login")) {
            System.out.println("Đăng nhập bằng cookies thành công!");
        } else {
            System.out.println("Đăng nhập bằng cookies thất bại, cookies có thể đã hết hạn.");
            getCookiesFromFirstLogin(DEFAULT_LOGIN_WAIT);
        }
        return webDriver;
    }

    private void addCookiesToDriver(Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            try {
                webDriver.manage().addCookie(cookie);
            } catch (Exception e) {
                System.out.println("Không thể thêm cookie: " + cookie.getName());
            }
        }
    }

    public void saveCookiesToProperties(Set<Cookie> cookies) {
        if (properties == null) return;
        
        int index = 0;
        for (Cookie cookie : cookies) {
            storeCookieInProperties(cookie, index++);
        }
        properties.setProperty("cookie.count", String.valueOf(index));

        try (FileOutputStream out = new FileOutputStream(CONFIG_PROPERTIES_FILE)) {
            properties.store(out, "Cấu hình cookies");
            System.out.println("Đã lưu " + index + " cookies vào file " + CONFIG_PROPERTIES_FILE);
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu cookies: " + e.getMessage());
        }
    }

    private void storeCookieInProperties(Cookie cookie, int index) {
        String prefix = "cookie." + index + ".";
        properties.setProperty(prefix + "name", cookie.getName());
        properties.setProperty(prefix + "value", cookie.getValue());
        properties.setProperty(prefix + "domain", cookie.getDomain());
        properties.setProperty(prefix + "path", cookie.getPath());
        if (cookie.getExpiry() != null) {
            properties.setProperty(prefix + "expiry", String.valueOf(cookie.getExpiry().getTime()));
        }
        properties.setProperty(prefix + "isSecure", String.valueOf(cookie.isSecure()));
        properties.setProperty(prefix + "isHttpOnly", String.valueOf(cookie.isHttpOnly()));
    }

    public Set<Cookie> getCookiesFromPropertiesFile() {
        if (properties == null) return null;

        int count = Integer.parseInt(properties.getProperty("cookie.count", "0"));
        if (count == 0) {
            System.out.println("Không có cookies trong file properties");
            return null;
        }

        Set<Cookie> cookies = new HashSet<>();
        for (int i = 0; i < count; i++) {
            Cookie cookie = buildCookieFromProperties(i);
            if (cookie != null) {
                cookies.add(cookie);
            }
        }
        
        System.out.println("Đã đọc " + cookies.size() + " cookies từ file " + CONFIG_PROPERTIES_FILE);
        return cookies;
    }

    private Cookie buildCookieFromProperties(int index) {
        String prefix = "cookie." + index + ".";
        try {
            String name = properties.getProperty(prefix + "name");
            String value = properties.getProperty(prefix + "value");
            String domain = properties.getProperty(prefix + "domain");
            String path = properties.getProperty(prefix + "path");
            String expiryStr = properties.getProperty(prefix + "expiry");
            Date expiry = expiryStr != null ? new Date(Long.parseLong(expiryStr)) : null;
            boolean isSecure = Boolean.parseBoolean(properties.getProperty(prefix + "isSecure"));
            boolean isHttpOnly = Boolean.parseBoolean(properties.getProperty(prefix + "isHttpOnly"));

            return new Cookie.Builder(name, value)
                    .domain(domain)
                    .path(path)
                    .expiresOn(expiry)
                    .isSecure(isSecure)
                    .isHttpOnly(isHttpOnly)
                    .build();
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc cookie " + index + ": " + e.getMessage());
            return null;
        }
    }

    public String[] getUsernameAndPasswordFromPropertiesFile() {
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

    private Properties loadProperties() {
        File configFile = new File(CONFIG_PROPERTIES_FILE);
        if (!configFile.exists()) {
            System.out.println("File " + CONFIG_PROPERTIES_FILE + " không tồn tại.");
            return null;
        }

        try (InputStream input = new FileInputStream(configFile)) {
            Properties props = new Properties();
            props.load(input);
            return props;
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file properties: " + e.getMessage());
            return null;
        }
    }

    private String cookiesToString(Set<Cookie> cookies) {
        StringBuilder sb = new StringBuilder();
        for (Cookie cookie : cookies) {
            sb.append(cookie.getName())
              .append("=")
              .append(cookie.getValue())
              .append("; ");
        }
        return sb.toString().trim();
    }

    public boolean checkCookiesValid() {
        Set<Cookie> cookies = getCookiesFromPropertiesFile();
        if (cookies == null || cookies.isEmpty()) {
            return false;
        }

        Date now = new Date();
        for (Cookie cookie : cookies) {
            if (cookie.getExpiry() != null && cookie.getExpiry().before(now)) {
                System.out.println("Cookie " + cookie.getName() + " đã hết hạn");
                return false;
            }
        }
        return true;
    }
}

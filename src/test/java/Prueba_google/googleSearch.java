package Prueba_google;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class googleSearch {
    private WebDriver driver;

    @Before
    public void setUp() {

        // Para ChromeDriver
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
        //this.driver = new ChromeDriver(options);
        //this.driver.manage().window().maximize();
        //this.driver.get("https://www.google.com.co/");

        // Para GeckoDriver (Firefox)
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        this.driver = new FirefoxDriver(options);
        this.driver.manage().window().maximize();
        this.driver.get("https://www.google.com.co/");

    }

    @Test
    public void testGoogle() {

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("Maclamy Alvarez");
        searchBox.submit();

        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Maclamy Alvarez"));

        // Aserción para validar el título

        String expectedTitle = "Maclamy Alvarez - Buscar con Google";
        String actualTitle = driver.getTitle();
        Assert.assertEquals("El título de la página no es el esperado", expectedTitle, actualTitle);
    }
    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
            protected void failed(Throwable throwable , Description description) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenshotFile, new File("test_failed_" + description.getMethodName() + getDate() + ".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            @Override
            protected void finished(Description description){
                driver.quit();
            }
        };
public String getDate(){
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
    Date date = new Date();
    return dateFormat.format(date);
        }
    }









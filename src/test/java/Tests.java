
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tests {
    WebDriver driver;


    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://master.hiring-assignment.qa.c66.me/");
    }

    @Test(priority = 1)
    public void loginTest() {
        Duration d1 = Duration.ofSeconds(30);
        driver.get("https://master.hiring-assignment.qa.c66.me/");

        WebElement loginBtn = driver.findElement(By.linkText("Login"));
        loginBtn.click();

        WebElement userNameTxtBox = driver.findElement(By.id("email"));
        userNameTxtBox.sendKeys("tester@shair.co");

        WebElement passwordTxtBox = driver.findElement(By.name("password"));
        passwordTxtBox.sendKeys("secure-password-for-assessment");

        WebElement submitBtn = driver.findElement(By.name("submit"));
        submitBtn.click();

        String welcomeTxt = driver.findElement(By.xpath("//h1[text()='Testing!']")).getText();
        Assert.assertEquals("Testing!", welcomeTxt, "Login failed");

    }

    @Test(dataProvider = "getSearchText", priority = 3)
    public void searchNoteByTextTest(String text) {

        WebElement searchLink = driver.findElement(By.linkText("Search"));
        searchLink.click();

        WebElement searchByTextTxtBox = driver.findElement(By.id("searchText"));
        searchByTextTxtBox.sendKeys(text);

        List<WebElement> noteCards = driver.findElements(By.xpath("//p[contains(text(), 'Test Note')]"));
        Assert.assertTrue(noteCards.size() > 0, "No notes found for given search text");


    }

    @Test(priority = 2)
    public void createNewNoteTest() {

        WebElement profileLink = driver.findElement(By.linkText("Profile"));
        profileLink.click();

        WebElement newContentTxtBox = driver.findElement(By.id("searchText"));
        newContentTxtBox.sendKeys("Test Note");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        List<WebElement> noteCards = driver.findElements(By.xpath("//p[contains(text(), 'Test Note')]"));
        Assert.assertTrue(noteCards.size() > 0, "Could not create a new note!");
    }

    // this test will fail as its a bug
    @Test(dataProvider = "getSearchText", priority = 4)
    public void readNoteTest(String text) {
        WebElement searchLink = driver.findElement(By.linkText("Search"));
        searchLink.click();

        WebElement searchByTextTxtBox = driver.findElement(By.id("searchText"));
        searchByTextTxtBox.sendKeys(text);

        driver.findElement(By.xpath("//a[contains(@class, 'Search_card__6y8CF')][1]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='Home_description__41Owk']")).isDisplayed(), "Could not read the note");

    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }

    @DataProvider
    public Object[][] getSearchText() {
        Object[][] data = new Object[1][1];
        data[0][0] = "Test Note";
        return data;
    }

}
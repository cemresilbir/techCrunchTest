import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Random;

public class techCrunchTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Set up Chrome WebDriver
        System.setProperty("webdriver.chrome.driver", "/Users/cemrenursilbir/Desktop/chromedriver");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless"); // Run in headless mode (no GUI)
        driver = new ChromeDriver(options);
    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void techCrunch() {

            // Go to the TechCrunch website
            System.setProperty("webdriver.chrome.driver", "/Users/cemrenursilbir/Desktop/chromedriver");
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless"); // Run in headless mode (no GUI)
            driver = new ChromeDriver(options);
            driver.get("https://techcrunch.com/");
            // Verify "The Latest News" list
            WebElement latestNewsSection = driver.findElement(By.xpath("(//*[contains(@class,'river')])[2]"));

            //Check if each news have an authors
            int newsCount = driver.findElements(By.xpath("//*[contains(@class,'post-block__header')]")).size();
            int authorsCount = driver.findElements(By.xpath("//*[contains(@class,'river-byline__authors')]")).size();
            System.out.println("Authour's count: " + authorsCount);
            System.out.println("New's count: " + newsCount);
            assert newsCount == authorsCount : "Each news have a authors.";

            //Check if each news has an image
            int imagesCount = driver.findElements(By.xpath("//figure[@class='post-block__media']")).size();
            assert imagesCount == newsCount : "Each news have a images.";

            //Click on one news from "The Latest News" list
            java.util.List<WebElement> newsItems = latestNewsSection.findElements(By.xpath("//*[contains(@class,'post-block post-block--image post-block--unread')]"));
            Random rand = new Random();
            int randomIndex = rand.nextInt(newsItems.size());
            WebElement randomNews = newsItems.get(randomIndex);
            randomNews.click();

            //Verify the browser title is the same with the news title
            WebElement openedNews = randomNews.findElement(By.xpath("//*[contains(@class,'article__title')]"));
            String newsTitle = openedNews.getText();
            String pageTitle = driver.getTitle();
            Assert.assertEquals(newsTitle, pageTitle);


            //Verify the links within the news content
            WebElement articleContent = driver.findElement(By.className("article__content"));
            java.util.List<WebElement> links = articleContent.findElements(By.tagName("a"));

            if (!links.isEmpty()) {
                System.out.println("There are links in the news content.");
            } else {
            Assert.assertFalse(links.isEmpty(), "There is no link in the news content.");
            }


        }

    }



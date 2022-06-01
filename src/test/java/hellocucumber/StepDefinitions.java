package hellocucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


class IsItFriday {
    static String isItFriday(String today) {
        System.out.println("today passed is " + today);
        return "Friday".equals(today) ? "TGIF" : "Nope";
//            if (today == "\"Friday\""){
//                System.out.println("Returning TGIF");
//                return "TGIF";
//            }
//            else {
//                System.out.println("Returning Nope");
//                return "Nope";
//            }
        }
    }

public class StepDefinitions {
    private String today;
    private String actualAnswer;
    private final ChromeOptions chrome_options = new ChromeOptions();
    private final WebDriver driver;

    public StepDefinitions() {
    chrome_options.addArguments("--headless");
    chrome_options.addArguments("--disable-gpu");
    chrome_options.addArguments("--disable-dev-shm-usage");
    driver = new ChromeDriver(chrome_options);
    }

    @Given("today is {string}")
    public void today_is(String today) {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        this.today = today;
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_friday_yet() {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        System.out.println("Expected answer is " + expectedAnswer + " Actual answer is " + actualAnswer);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @Given("I am on the Google search page")
    public void i_am_on_the_google_search_page() {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        driver.get("https://www.google.com");
    }
    @When("I search for {string}")
    public void i_search_for(String query) {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
        WebElement element = driver.findElement(By.name("q"));
        // Enter something to search for
        element.sendKeys(query);
        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
    }
    @Then("the page title should start with {string}")
    public void the_page_title_should_start_with(String titleStartsWith) {
        // Write code here that turns the phrase above into concrete actions
        new WebDriverWait(driver,10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith(titleStartsWith);
            }
        });
    }

    @After()
    public void closeBrowser(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Cheese_Search_Results");
        driver.quit();
    }

}

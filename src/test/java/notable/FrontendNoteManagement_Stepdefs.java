package notable;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FrontendNoteManagement_Stepdefs {

    private WebDriver driver;

    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() throws Exception {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");
    }

    @When("^I enter the id of my note$")
    public void i_enter_the_id_of_my_note() throws Exception {
        driver.findElement(By.name("id")).sendKeys("9000");
    }

    @When("^I enter the content of my note$")
    public void i_enter_the_content_of_my_note() throws Exception {
        driver.findElement(By.name("content")).sendKeys("At vero eos et accusamus");
    }

    @When("^I submit the form$")
    public void i_submit_the_form() throws Exception {
        Thread.sleep(3000);
        driver.findElement(By.id("btn__add-note")).click();
    }

    @Then("^I should be notified that note has been added successfully$")
    public void i_should_be_notified_that_note_has_been_added_successfully() throws Exception {
        String body = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(body.contains("Note added successfully"));
    }

    @Then("^my note should appear on the home page$")
    public void my_note_should_appear_on_the_home_page() throws Exception {
        Thread.sleep(3000);
        String body = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(body.contains("At vero eos et accusamus"));
    }
}

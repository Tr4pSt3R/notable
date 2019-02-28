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

    private static final String GECKO_DRIVER_PATH = "/usr/local/bin/geckodriver";
    private static final String APP_URL = "http://localhost:3000/";

    private void setup() {
        System.setProperty("webdriver.gecko.driver", GECKO_DRIVER_PATH);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(APP_URL);
    }

    private void sendInput(String target, String value) {
        driver.findElement(By.name(target)).sendKeys(value);
    }

    private void submitForm() throws Exception {
        Thread.sleep(3000);
        driver.findElement(By.id("btn__add-note")).click();
    }

    /* ADD NEW NOTE */
    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() throws Exception {
        setup();
    }

    @When("^I enter the id of my note$")
    public void i_enter_the_id_of_my_note() throws Exception {
        sendInput("id", "9000");
    }

    @When("^I enter the content of my note$")
    public void i_enter_the_content_of_my_note() throws Exception {
        sendInput("content", "At vero eos et accusamus");
    }

    @When("^I submit the form$")
    public void i_submit_the_form() throws Exception {
        submitForm();
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

    /* DELETE NOTE */
    @Given("^I have added a note with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_have_added_a_note_with_and(String id, String content) throws Exception {
        setup();
        sendInput("id", id);
        sendInput("content", content);
        submitForm();
    }

    @When("^I click on the delete button for \"([^\"]*)\"$")
    public void i_click_on_delete_button_for(String link_id) throws Exception {
        driver.findElement(By.id("delete__" + link_id)).click();
    }

    @Then("^I should be notified that the note has been deleted$")
    public void i_should_be_notified_that_the_note_has_been_deleted() throws Exception {
        String body = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(body.contains("Note deleted successfully"));
    }

    @Then("^the note with \"([^\"]*)\" should no longer appear in the list of notes$")
    public void the_note_with_should_no_longer_appear_in_the_list_of_notes(String content) throws Exception {
        Thread.sleep(1000);
        String body = driver.findElement(By.tagName("body")).getText();
        Assert.assertFalse(body.contains(content));
    }
}

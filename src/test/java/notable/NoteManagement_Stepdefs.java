package notable;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NoteManagement_Stepdefs {
    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() {
        executeGet("http://localhost:8080/notes");
    }

    @When("^I enter my note$")
    public void i_enter_my_note() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I save it$")
    public void i_save_it() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^note should be saved successfully$")
    public void note_should_be_saved_successfully() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}

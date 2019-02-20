package notable;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static junit.framework.TestCase.assertEquals;

public class NoteManagement_Stepdefs {
    private static final String NOTABLE_PATH = "/notable";
    private WireMockServer wireMockServer = new WireMockServer();
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    @Given("^I am on the home page$")
    public void i_am_on_the_home_page() throws IOException {
        wireMockServer.start();

        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo(NOTABLE_PATH)).willReturn(aResponse().withBody("Welcome to Notable!")));

        HttpGet request = new HttpGet("http://localhost:8080/notable");
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = convertResponseToString(httpResponse);

        verify(getRequestedFor(urlEqualTo(NOTABLE_PATH)));
        assertEquals("Welcome to Notable!", stringResponse);
    }

    private static String convertResponseToString(@org.jetbrains.annotations.NotNull HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String stringResponse = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return stringResponse;
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

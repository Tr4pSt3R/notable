package notable;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

public class NoteManagement_Stepdefs {
    private static HttpResponse response;
    private static HttpClient httpClient;
    private static final String BASE_URL = "http://localhost:8080";

    static {
        httpClient = HttpClientBuilder.create().build();
    }

    @When("^I make a POST request$")
    public void i_make_a_POST_request() throws Exception {
        HttpPost request = new HttpPost(BASE_URL + "/notes");
        StringEntity params = new StringEntity("{\"id\": \"0\", \"content\":\"Hello World\"}");
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        response = httpClient.execute(request);
    }

    @When("^I change the content of the note with id \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_change_the_content_of_the_note_with_id_to(String id, String content) throws Exception {
        HttpPut request = new HttpPut(BASE_URL + "/note/" + id);
        String data = String.format("{\"id\": \"%s\", \"content\": \"%s\"}", id, content);
        StringEntity params = new StringEntity(data);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        response = httpClient.execute(request);
    }

    @Then("^I should get an HTTP success$")
    public void i_should_get_an_HTTP_success() {
        Assert.assertEquals(
                200,
                response.getStatusLine().getStatusCode()
        );
    }


    @Given("^I have a note with id \"([^\"]*)\" and with content \"([^\"]*)\"$")
    public void i_have_a_note_with_id_with_content(String id, String content) throws Exception {
        HttpPost request = new HttpPost(BASE_URL + "/notes");
        String data = String.format("{\"id\": \"%s\", \"content\": \"%s\"}", id, content);
        StringEntity params = new StringEntity(data);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        response = httpClient.execute(request);
    }


    @When("^I make a GET request to \"([^\"]*)\"$")
    public void i_make_a_GET_request_to(String uri) throws Exception {
        HttpGet request = new HttpGet("http://localhost:8080" + uri);
        response = httpClient.execute(request);
    }

    @Then("^the response body should be:$")
    public void the_response_body_should_be(String expected_response_body) throws Exception {
        Assert.assertEquals(
                expected_response_body,
                EntityUtils.toString(response.getEntity())
        );
    }
}

package notable;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class APINoteManagement_Stepdefs {
    private static HttpResponse response;
    private static HttpClient httpClient;
    private static final String BASE_URL = "http://localhost:8080";

    static {
        httpClient = HttpClientBuilder.create().build();
    }

    private void httpPostHelper(String id, String content) throws Exception {
        HttpPost request = new HttpPost(BASE_URL + "/api/notes");
        String data = String.format("{\"id\": \"%s\", \"content\":\"%s\"}", id, content);
        StringEntity params = new StringEntity(data);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        response = httpClient.execute(request);
    }

    @Given("^I have a note with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_have_a_note_with_and(String id, String content) throws Exception {
        httpPostHelper(id, content);
    }


    @When("^I make a POST request$")
    public void i_make_a_POST_request() throws Exception {
        httpPostHelper("0", "Hello World");
    }

    @When("^I change the content of the note with id \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_change_the_content_of_the_note_with_id_to(String id, String content) throws Exception {
        HttpPut request = new HttpPut(BASE_URL + "/api/note/" + id);
        String data = String.format("{\"id\": \"%s\", \"content\": \"%s\"}", id, content);
        StringEntity params = new StringEntity(data);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        response = httpClient.execute(request);
    }

    @When("^I make a DELETE request to \"([^\"]*)\"$")
    public void i_make_a_DELETE_request_to(String uri) throws Exception {
        HttpDelete request = new HttpDelete(BASE_URL + uri);
        request.addHeader("content-type", "application/json");

        response = httpClient.execute(request);
    }

    @Then("^I should see \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_should_see_and(String id, String content) throws Exception {
        Assert.assertTrue(EntityUtils.toString(response.getEntity()).contains(content));
    }

    @Then("^a note with content \"([^\"]*)\" should not appear in index view$")
    public void a_note_with_content_should_not_appear_in_index_view(String deleted_content) throws Exception {
        String response_string = EntityUtils.toString(response.getEntity());

        Assert.assertFalse(response_string.contains(deleted_content));
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
        httpPostHelper(id, content);
    }


    @When("^I make a GET request to \"([^\"]*)\"$")
    public void i_make_a_GET_request_to(String uri) throws Exception {
        HttpGet request = new HttpGet(BASE_URL + uri);
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

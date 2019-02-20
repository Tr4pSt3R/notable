package notable;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;

public class NoteManagement_Stepdefs {
    private static HttpResponse response;

    @When("^I make a \"([^\"]*)\" request to \"([^\"]*)\"$")
    public void i_make_a_request_to(String arg1, String arg2) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://localhost:8080/notes");
        StringEntity params = new StringEntity("note={\"content\":\"Hello World\"}");
        //request.addHeader("content-type", "application/json");
        request.addHeader("content-type", "application/x-www-form-urlencoded");
        request.setEntity(params);

        response = httpClient.execute(request);
    }

    @Then("^I should get an HTTP \"([^\"]*)\" status code$")
    public void i_should_get_an_HTTP_status_code(String status_code) {
        Assert.assertEquals(
                Integer.parseInt(status_code),
                response.getStatusLine().getStatusCode()
        );
    }

    @Then("^the response body should be:$")
    public void the_response_body_should_be(String arg1) throws Exception {
        Assert.assertEquals(
                "\"content\":\"Hello World\"",
                EntityUtils.toString(response.getEntity())
        );
    }

}

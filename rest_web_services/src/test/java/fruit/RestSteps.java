package fruit;

import com.sun.jersey.api.client.*;

import cucumber.api.PendingException;
import cucumber.api.java.en.*;

import hooks.ServerHooks;

import java.net.HttpURLConnection;

import org.testng.*;

public class RestSteps {
    private ClientResponse response;

        @When("^the client requests GET /fruits$")
        public void theClientRequestsGETFruits() throws Throwable {
            try {
                Client client = Client.create();
                WebResource webResource = client
                        .resource("http://localhost:" + ServerHooks.PORT + "/fruits");
                response = webResource.type("application/json")
                        .get(ClientResponse.class);
            } catch (RuntimeException r) {
                throw r;
            } catch (Exception e) {
                System.out.println("Exception caught");
                e.printStackTrace();
            }
            Assert.assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK,  "Did not receive OK response: ");
        }

        @Then("^the response should be JSON:$")
        public void theResponseShouldBeJSON(String jsonExpected) throws Throwable {
            Assert.assertEquals(response.getEntity(String.class),jsonExpected, "Incorrect JSON representation");
        }
    }


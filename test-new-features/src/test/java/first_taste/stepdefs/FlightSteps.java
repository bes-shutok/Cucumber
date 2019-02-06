package first_taste.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;

public class FlightSteps {
    private HashMap<String,Integer> assortment = new HashMap<String,Integer>();
    private String ticket;
    @Given("^the flight ([A-Z]+\\d+) is leaving today")
    public void theFlightIsLeavingToday(String ticketNumber){
        ticket=ticketNumber;
        System.out.println("Ticket #: "+ticket);
    }

    @When("^mockWhen$")
    public void mockWhen(){}

    @Then("^mockThen$")
    public void mockThen(){}
}

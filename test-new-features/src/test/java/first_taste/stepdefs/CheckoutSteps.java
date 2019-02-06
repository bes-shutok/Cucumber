package first_taste.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import first_taste.implementation.Checkout;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public class CheckoutSteps {
    private HashMap<String,Integer> assortment = new HashMap<String,Integer>();
    private Checkout checkout= new Checkout();
    @Given("^the price of a \"([^\"]*)\" is (\\d+)c$")
    public void thePriceOfAIsC(String itemName, int price){
        assortment.put(itemName,price);
    }

    @When("^I checkout (\\d+) \"([^\"]*)\"$")
    public void iCheckout(int itemCount, String itemName){
        checkout.add(itemCount, assortment.get(itemName));
    }

    @Then("^the total price should be (\\d+)c$")
    public void theTotalPriceShouldBeC(int total){
        assertEquals(total, checkout.total());
    }
}

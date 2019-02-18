package first_taste.stepdefs;

import cucumber.api.java.en.*;
import java.util.HashMap;
import static org.testng.Assert.assertEquals;

public class TransferFunds {
    private HashMap<String,Integer> accounts = new HashMap<String,Integer>();
    @Given("^I have deposited \\$(\\d+) in my (\\w+) Account$")
    public void iHaveDeposited$InMyAccount(int amount, String accountType) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("\nI deposited $" + amount + " to " + accountType + " Account");
        accounts.put(accountType,amount);
        // System.out.println(accounts);
    }

    @When("^I enter \\$(\\d+) to tranfer from my (\\w+) Account into my (\\w+) Account$")
    public void iEnter$ToTranferFromMySavingsAccountIntoMyCheckingAccount(int amount, String debitedAccount, String receivingAccount){
        System.out.println("\nI tranfer $" + amount + " from my " + debitedAccount + " Account to my " + receivingAccount + " Account");
        accounts.put(debitedAccount,accounts.get(debitedAccount)-amount);
        accounts.put(receivingAccount,accounts.get(receivingAccount)+amount);
        System.out.println(accounts);
    }

    @Then("^the balance of the (\\w+) Account should be \\$(\\d+)$")
    public void theBalanceOfTheCheckingAccountShouldBe$(String accountType, int amount) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals((int)accounts.get(accountType),amount);
    }
}

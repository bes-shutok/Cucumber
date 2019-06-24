package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import transforms.MoneyConverter;

public class CashSlotSteps {
    @Autowired
    private CashSlot cashSlot;

    // FAILURE
    @Then("^(\\$\\d+\\.\\d+) should be dispensed$")
    public void $ShouldBeDispensed(@Transform(MoneyConverter.class) Money amount) {
        Assert.assertEquals(cashSlot.getContents(),amount,"Incorrect CashSlot content - ");
    }

    @Then("^nothing should be dispensed$")
    public void nothingShouldBeDispensed() {
        Assert.assertEquals(cashSlot.getContents(),new Money("0.00"),"Incorrect CashSlot content - ");
    }

}

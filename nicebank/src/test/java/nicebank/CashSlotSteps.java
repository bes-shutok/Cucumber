package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import support.KnowsTheCashSlot;
import transforms.MoneyConverter;

public class CashSlotSteps {
    private KnowsTheCashSlot cashSlotHelper;
    public CashSlotSteps(KnowsTheCashSlot cashSlotHelper) {
        this.cashSlotHelper = cashSlotHelper;
    }

    // FAILURE
    @Then("^(\\$\\d+\\.\\d+) should be dispensed$")
    public void $ShouldBeDispensed(@Transform(MoneyConverter.class) Money amount) {
        Assert.assertEquals(cashSlotHelper.getCashSlot().getContents(),amount,"Incorrect CashSlot content - ");
    }

    @Then("^nothing should be dispensed$")
    public void nothingShouldBeDispensed() {
        Assert.assertEquals(cashSlotHelper.getCashSlot().getContents(),new Money("0.00"),"Incorrect CashSlot content - ");
    }

}

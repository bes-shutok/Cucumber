package nicebank;

import cucumber.api.*;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import support.KnowsTheDomain;
import transforms.MoneyConverter;

public class CashSlotSteps {
    private KnowsTheDomain helper;
    public CashSlotSteps(KnowsTheDomain helper) {
        this.helper = helper;
    }

    @Then("^(\\$\\d+\\.\\d+) should be dispensed$")
    public void $ShouldBeDispensed(@Transform(MoneyConverter.class) Money amount) {
        Assert.assertEquals(helper.getCashSlot().getContents(),amount,"Incorrect account balance - ");
    }

    @Then("^nothing should be dispensed$")
    public void nothingShouldBeDispensed() {
        Assert.assertEquals(helper.getCashSlot().getContents(),new Money("0.00"),"Incorrect CashSlot content - ");
    }

}

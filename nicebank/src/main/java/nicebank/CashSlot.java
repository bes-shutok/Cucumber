package nicebank;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class CashSlot {
    private Money contents;

    public Money getContents() {return contents;}
    public void dispense(Money amount) {contents=amount;}
}

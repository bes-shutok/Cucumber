package nicebank;

public interface Teller {
    boolean withdrawFrom(Account account, Money amount);
}

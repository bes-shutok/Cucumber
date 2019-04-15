package nicebank;

public class NotEnoughMoney extends Exception {

    // Parameterless Constructor
    public NotEnoughMoney(){
        super("Not enough money on the account!");
    }

    // Constructor that accepts a message
    public NotEnoughMoney(String message){
        super(message);
    }
}

package first_taste.implementation;

public class Checkout {
    private int totalPrice=0;
    public void add(int count, int price) {
        totalPrice = totalPrice + count*price;
    }
    public int total() { return totalPrice;}
}

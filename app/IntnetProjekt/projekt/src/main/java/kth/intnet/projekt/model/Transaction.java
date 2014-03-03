package kth.intnet.projekt.model;

/**
 * Created by Ludde on 2014-03-03.
 */
public class Transaction {
    private int fromUser;
    private int toUser;
    private float amount;
    private String fromCurr;
    private String type;

    public Transaction(int fromUser, int toUser, float amount, String fromCurr, String type){
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.fromCurr = fromCurr;
        this.type = type;
    }
    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getFromCurr() {
        return fromCurr;
    }

    public void setFromCurr(String fromCurr) {
        this.fromCurr = fromCurr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

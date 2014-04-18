package kth.intnet.projekt.model;

import java.sql.Date;

/**
 * Created by Ludde on 2014-03-03.
 */
public class Transaction {
    private int fromUser;
    private int toUser;
    private float amount;
    private String fromCurr;
    private String type;
    private float rate;
    private String dt;

    public Transaction(int fromUser, int toUser, float amount, String fromCurr, String type, float rate, String dt){
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.fromCurr = fromCurr;
        this.type = type;
        this.rate = rate;
        this.dt = dt;
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

    public float getRate() { return rate; }

    public void setRate(float rate) { this.rate = rate; }

    public String getDate() { return dt; }

    public void setDate(String dt){ this.dt = dt; }

    @Override
    public String toString(){
        return fromUser + ", " + toUser + ", " + amount + ", " + dt.toString();
    }
}

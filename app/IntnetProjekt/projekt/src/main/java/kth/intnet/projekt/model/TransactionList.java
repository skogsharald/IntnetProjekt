package kth.intnet.projekt.model;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ludde on 2014-03-06.
 */
public class TransactionList {
    private Transaction[] transfers;
    private ArrayList<Transaction> tArrayList;

    public TransactionList(){

    }

    public Transaction[] getTransactionList() {
        return transfers;
    }

    public ArrayList<Transaction> getTransactionArrayList() { return tArrayList; }

    public void convertListToArrayList(){
        tArrayList = new ArrayList<Transaction>();
        if(transfers == null){
            Log.e("ERROR", "Transactionlist is null");
        }
        for(Transaction t: transfers){
            tArrayList.add(t);
        }
    }

    public void addTransaction(Transaction t){
        tArrayList.add(t);
    }

    public void setTransactionList(Transaction[] transactionList) {
        this.transfers = transactionList;
    }
}

package com.svetaukiyo.transactionAnalyser;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private String id;
    private Date date;
    private double amount;
    private String merchant;
    private Type type;

    public Transaction(String id, Date date, double amount, String merchant, Type type) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.merchant = merchant;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", merchant=" + merchant +
                ", type=" + type +
                '}';
    }
}

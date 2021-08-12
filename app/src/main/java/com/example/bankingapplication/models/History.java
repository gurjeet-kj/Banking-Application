package com.example.bankingapplication.models;

public class History {
    public History(String subscriptionNumber, String amountPaid) {
        this.subscriptionNumber = subscriptionNumber;
        this.amountPaid = amountPaid;
    }

    public String getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public void setSubscriptionNumber(String subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    private String subscriptionNumber;
    private String amountPaid;
}

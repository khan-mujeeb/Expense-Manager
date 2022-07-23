package com.example.expensetracker;

public class TransactionModel {
    String amount, id, note, type, date;

    public TransactionModel(String amount, String id, String note, String type,String date){
        this.amount = amount;
        this.id = id;
        this.note = note;
        this.type = type;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount(){
        return amount;
    }

    public String getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setType(String type) {
        this.type = type;
    }
}

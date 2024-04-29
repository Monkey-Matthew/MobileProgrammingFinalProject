package com.zybooks.cop4656project.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "transactions")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "amount")
    public double amount;

    //constructor
    public Transaction(double amount, Date date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    //getters
    public long getId() { return id; }
    public Date getDate() { return date; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }

    //setters
    public void setDate(Date date) { this.date = date; }
    public void setDescription(String Description) { this.description = description; }
    public void setAmount(double amount) { this.amount = amount; }
}

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

    @ColumnInfo(name = "category_id")
    public long categoryId;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "amount")
    public double amount;

    @ColumnInfo(name = "transaction_type")
    public String transactionType;

    public Transaction(long id, long categoryId, double amount, Date date, String description, String transactionType) {
        this.id = id;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.transactionType = transactionType;
    }

    public long getId() { return id; }
    public Date getDate() { return date; }
    public long getCategoryId() { return categoryId; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getTransactionType() { return transactionType; }
}

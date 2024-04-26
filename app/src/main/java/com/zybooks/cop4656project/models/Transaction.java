package com.zybooks.cop4656project.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "transactions", foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id", childColumns = "category_id", onDelete = ForeignKey.CASCADE))
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "date")
    public Date date;

    @ColumnInfo(name = "category_id")
    public int categoryId;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "amount")
    public double amount;

    @ColumnInfo(name = "transaction_type")
    public String transactionType;

    public Transaction(int budgetID, int categoryId, double amount, Date date, String description, String transactionType) {
        this.id = budgetID;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.transactionType = transactionType;
    }

    public int getId() { return id; }
    public Date getDate() { return date; }
    public int getCategoryId() { return categoryId; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getTransactionType() { return transactionType; }
}

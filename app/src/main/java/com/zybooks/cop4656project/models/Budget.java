package com.zybooks.cop4656project.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "budget", foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "id", childColumns = "category_id", onDelete = ForeignKey.CASCADE))
public class Budget {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "category_id")
    public int categoryId;
    @ColumnInfo(name = "amount")
    public double amount;
    @ColumnInfo(name = "start_date")
    public Date startDate;
    @ColumnInfo(name = "end_date")
    public Date endDate;

    public Budget(int budgetID, int categoryId, double amount, Date startDate, Date endDate) {
        this.id = budgetID;
        this.categoryId = categoryId;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() { return id; }
    public int getCategoryId() { return categoryId; }
    public double getAmount() { return amount; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }

}

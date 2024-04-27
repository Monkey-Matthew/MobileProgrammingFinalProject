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

    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "category_id")
    public int categoryId;
    @ColumnInfo(name = "monthly_budget")
    public double monthlyBudget;
    @ColumnInfo(name = "general_monthly_save_goal")
    public double monthlySaveGoal;
    @ColumnInfo(name = "savings_type")  //will be 1 for conservative, 2 for normal and 3 for agg.
    public int savingsType;
    @ColumnInfo(name = "start_date")
    public Date currentDate;

    //constructor
    public Budget(int id, int categoryId, double monthlyBudget, Date currentDate,
                  double monthlySaveGoal, int savingsType) {
        this.id = id;
        this.categoryId = categoryId;
        this.monthlyBudget = monthlyBudget;
        this.currentDate = currentDate;
        this.monthlySaveGoal = monthlySaveGoal;
        this.savingsType = savingsType;
    }

    //getters
    public int getId() { return id; }
    public int getCategoryId() { return categoryId; }
    public double getMonthlyBudget() { return monthlyBudget; }
    public double getMonthlySaveGoal() { return monthlySaveGoal; }
    public Date getCurrentDate() { return currentDate; }
    public int getSavingsType() { return savingsType; }

    //setters for values that can be changed
    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public void setMonthlySaveGoal(double monthlySaveGoal) {
        this.monthlySaveGoal = monthlySaveGoal;
    }

    public void setSavingsType(int savingsType) {
        this.savingsType = savingsType;
    }
}

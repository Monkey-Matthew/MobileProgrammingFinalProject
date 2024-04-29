package com.zybooks.cop4656project.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "budget")
public class Budget {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "monthly_income")
    public double monthlyIncome;
    @ColumnInfo(name = "general_monthly_save_goal")
    public double monthlySaveGoal;
    @ColumnInfo(name = "savings_type")  //will be 1 for conservative, 2 for normal and 3 for agg.
    public long savingsType;
    @ColumnInfo(name = "current_date")
    public Date currentDate;

    @ColumnInfo(name = "auto_savings")
    public boolean autoSavings;

    @ColumnInfo(name = "amount_saved")
    public double amountSaved;

    //constructor
    public Budget(long id, String name, double monthlyIncome, Date currentDate,
                  double monthlySaveGoal, long savingsType, boolean autoSavings, double amountSaved) {
        this.id = id;
        this.name = name;
        this.monthlyIncome = monthlyIncome;
        this.currentDate = currentDate;
        this.monthlySaveGoal = monthlySaveGoal;
        this.savingsType = savingsType;
        this.autoSavings = autoSavings;
        this.amountSaved = amountSaved;
    }

    //getters
    public long getId() { return id; }
    public String getName() { return name;}
    public double getMonthlyIncome() { return monthlyIncome; }
    public double getMonthlySaveGoal() { return monthlySaveGoal; }
    public long getSavingsType() { return savingsType; }
    public boolean getAutoSavings() { return autoSavings; }
    public double getAmountSaved() { return amountSaved; }

    //setters for values that can be changed
    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    public void setMonthlySaveGoal(double monthlySaveGoal) {
        this.monthlySaveGoal = monthlySaveGoal;
    }
    public void setSavingsType(int savingsType) {
        this.savingsType = savingsType;
    }
    public void setAutoSavings(boolean autoSavings) { this.autoSavings = autoSavings; }
    public void setAmountSaved(double amountSaved) { this.amountSaved = amountSaved; }
}

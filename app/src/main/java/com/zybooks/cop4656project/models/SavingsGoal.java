package com.zybooks.cop4656project.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "savings_goals")
public class SavingsGoal {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "target_amount")
    public double targetAmount;

    @ColumnInfo(name = "current_amount")
    public double currentAmount;

    @ColumnInfo(name = "target_date")
    public Date targetDate;

    @ColumnInfo(name = "name")
    public String name;

    public SavingsGoal(int goalID, double target, double current, Date targetDate, String name) {
        this.id = goalID;
        this.targetAmount = target;
        this.currentAmount = current;
        this.targetDate = targetDate;
        this.name = name;
    }

    public int getId() { return id; }
    public double getTargetAmount() { return targetAmount; }

    public double getCurrentAmount() { return currentAmount; }

    public Date getTargetDate() { return targetDate; }

    public String getName() { return name; }
}

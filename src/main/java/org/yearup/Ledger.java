package org.yearup;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ledger {
    private LocalDate date;
    private LocalTime time;
    private String desc;
    private String vendor;
    private double amount;

    public Ledger() {}

    public Ledger(LocalDate date, LocalTime time, String desc, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.desc = desc;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

package com.rideshare.models;

public class Rider {
    private int id;
    private String name;
    private String phone;
    private double walletBalance;

    public Rider(int id, String name, String phone, double walletBalance) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.walletBalance = walletBalance;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void addToWallet(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }
        walletBalance += amount;
    }

    public boolean deductFromWallet(double amount) {
        if (!hasValidBalance(amount)) {
            return false;
        }
        walletBalance -= amount;
        return true;
    }

    private boolean hasValidBalance(double amount) {
        return (walletBalance >= amount && amount >= 0);
    }
}

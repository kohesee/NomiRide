package com.rideshare.models;

public class Rider {
    // Private fields (hidden from external access)
    private int id;
    private String name;
    private String phone;
    private double walletBalance;

    // Constructor (optionally initialize fields)
    public Rider(int id, String name, String phone, double walletBalance) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.walletBalance = walletBalance;
    }

    // Public getter for id
    public int getId() {
        return id;
    }
    // Public setter for id (could add validation if needed)
    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    // Getter and setter for phone
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter for walletBalance
    public double getWalletBalance() {
        return walletBalance;
    }
    // (No public setter for balance: manage via add/deduct)

    // Add an amount to the wallet balance
    public void addToWallet(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }
        walletBalance += amount;
    }

    // Deduct an amount from the wallet, with validation
    public boolean deductFromWallet(double amount) {
        if (!hasValidBalance(amount)) {
            return false; // Insufficient funds, operation fails
        }
        walletBalance -= amount;
        return true; // Deduction successful
    }

    // Private helper: checks if enough balance exists
    private boolean hasValidBalance(double amount) {
        return (walletBalance >= amount && amount >= 0);
    }
}

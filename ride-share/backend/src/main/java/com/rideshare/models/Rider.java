package com.rideshare.models;

public class Rider {
    private String id;
    private String name;
    private String phone;
    private double walletBalance;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public double getWalletBalance() { return walletBalance; }
    public void setWalletBalance(double walletBalance) { this.walletBalance = walletBalance; }

    public boolean deductFromWallet(double amount) {
        if (!hasValidBalance(amount)) return false;
        this.walletBalance -= amount;
        return true;
    }

    public void addToWallet(double amount) { this.walletBalance += amount; }

    private boolean hasValidBalance(double amount) { return amount >= 0 && this.walletBalance >= amount; }
}

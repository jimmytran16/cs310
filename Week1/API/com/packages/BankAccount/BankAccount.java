package com.packages.BankAccount;

public class BankAccount implements BankAccountInterface{
    // set properties of the BankAccount class
    private double current_balance;
    private String account_holder;


    // Constructor
    public BankAccount(double first_deposit,String account_holder){
        this.current_balance = first_deposit;
        this.account_holder = account_holder;
    }

    // Member functions
    @Override
    public void depositMoney(double amount){
        current_balance = current_balance + amount;
        System.out.printf("You just deposited %.2f --  Current Balance %.2f\n",amount,this.current_balance);           
    }
    @Override
    public void withdrawlMoney(double amount){
        if (checkIfBalanceIsSuffiecient(amount)) { 
            current_balance = current_balance - amount;  
            System.out.printf("You just withraw %.2f --  Current Balance %.2f\n",amount,current_balance); 
        }
        else{
            System.out.printf("Cannot withdrawl %.2f, current balance is %.2f",amount,current_balance);
        }
    }
    @Override
    public void getCurrentInBank(){
        System.out.println("Current Balance " +current_balance);           
    }   

    @Override
    public void getAccountHolder(){
        System.out.printf("Account Holder Name: %s\n",account_holder);
    }

    public void insideBank(){
        System.out.println("INSIDE BANK");
    }

    //helper function to make sure that the balance does not go negative
    private boolean checkIfBalanceIsSuffiecient(double amount){
        if ((current_balance - amount) < 0) { return false; }
        else { return true; }
    }
}

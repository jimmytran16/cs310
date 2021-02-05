import java.util.*;


public class onee {
  public static void main (String args[]) {
     List<Account> accounts = new LinkedList<Account>();
     accounts.add(new BankAccount("Joe", 100, 100));
     accounts.add(new BankAccount("Sue", 101, 200)); //sort by balance, using a Comparator and list sort.



    // calls the collection sort to sort the account linked list
     Collections.sort(accounts, new BalanceComparator());

     // balance comparator class to compare the balance
     // tenerary operators to return the right integer comparison
     class BalanceComparator implements Comparator<Account> {
       @Override
       public int compare(Account a, Account b) {
         int a_balance = a.getBalance();
         int b_balance = b.getBalance();
         return a_balance < b_balance ? -1 : a_balance == b_balance ? 0 : 1;
       }
     }



     










     for (Account acc : accounts ) {
       System.out.println(acc.getBalance());
     }
  }
}



interface Account {
    int withdraw(int amt);
    void deposit(int amt);
    int getBalance();
}

class BankAccount implements Account {
    // constructor - create a BA
    public BankAccount(String nm, int _id, int bal) {
      id = _id;
      this.balance = bal;
      this.name = nm;
    }
    // Account API functions
    public int withdraw(int amt) { return balance; }
    public void deposit(int amt) {}
    public int getBalance() { return balance;}
    // Fields - all private
    private int id;
    private String name;
    private int balance;
}

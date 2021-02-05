import com.packages.BankAccount.BankAccount;
import com.packages.BankAccount.BankAccountInterface;
import java.util.Scanner;

public class entry{
  public static void main(final String args[]){
    String answer = "";
    Scanner input = new Scanner(System.in);
    
    //init a bank account instance
    BankAccountInterface bankInstance = new BankAccount(123.4,"Jimmy Tran");

    do {
      System.out.println("\nWhat would you like to do?");
      answer = input.nextLine();
      clearBuffer(input);
      switch(answer){
        
        case "d":
          double amount;
          System.out.print("How much would you like to deposit: ");
          amount = input.nextDouble();
          bankInstance.depositMoney(amount);
          clearBuffer(input);
          break;
        
        case "w":
          double withdrawl_am;
          System.out.print("How much would you like to withdrawl: ");
          withdrawl_am = input.nextDouble();
          bankInstance.withdrawlMoney(withdrawl_am);
          clearBuffer(input);
          break;
        
        case "q":
          answer = "q";
          input.close();
          break;
        
        default:
          System.out.println("Invalid entry, please try again!");
      }
    }while(!(answer == "q"));
  }


  private static void clearBuffer(Scanner i){ //helper function to clear the Scanner's buffer
    i.nextLine();
  }
}

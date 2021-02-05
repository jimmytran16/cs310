

public class CombinationLock {
  // Private string to hold the combination string
  private int combinationCode;

  //Constructor, takes in the combination string parameter
  public CombinationLock(int combinationCode){
    this.combinationCode = combinationCode;
  }

  //Method to change the combo, passing in the old combination and new combination,
  //if the old combination is correct then, reassign the combination to the new one.
  public void changeCombo(int oldCombo, int newCombo){
    if(oldCombo == combinationCode) {
      combinationCode = oldCombo;
      System.out.print("Combination code changed successfully!");
    }else{
      System.out.print("Unable to change the combination code, passcode incorrect!");
    }
  }

  //Method to open the code, passing in the comboCode and comparing it to the saved combination
  public void open(int comboCode){
    if(comboCode == combinationCode) {
      System.out.print("Successfully opened!");
    }else{
      System.out.print("Failed to open, incorrect combination");
    }
  }
}

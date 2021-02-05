public class recursive {
  public static void main(String args[]){
    System.out.println(factorial(5));
  }
  static int factorial (int n){
    System.out.println("THE N VALUE CURRENTLY - "+n);
    if (n <= 1) return 1;
    return n * factorial(n-1);
  }
}

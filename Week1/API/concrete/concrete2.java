package concrete;
import pack.names.names;

public class concrete2 implements names {

  public void sayHi(){
      System.out.println("Hello from 2");
  }

  public void sayHiJimmy(){
      System.out.println("Hello Jimmy from 2");
  }

  public void sayHiToSomeone(String name){
    System.out.println("Hello "+name+ "from 2");
  }
}

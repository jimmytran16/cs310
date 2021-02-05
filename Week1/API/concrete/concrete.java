package concrete;
import pack.names.names;

public class concrete implements names{
  private String name;
  public concrete(){
    this.name = "Jimmy";
  }
  public void sayHi(){
    System.out.println("Hello");
  }
  public void sayHiJimmy(){
    System.out.println("Hello "+name);
  }
  public void sayHiToSomeone(String someone){
    System.out.println("Hi "+someone);
  }
}

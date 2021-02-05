import java.util.*;

/* Like Orders, Students are ordered on basis of id only. */
import java.lang.Integer;

public class onec {
  public static void main(String args[]) {
    Student[] students = {new Student("Bob",1, 4.1), new Student("Joe", 2, 3.0), new Student("Bob",3, 3.9)};

    Arrays.sort(students);





    System.out.print(Arrays.toString(students));
  }
}

class Student implements Comparable<Student>  {
    private int id;
  private String name;
  private double gpa;

    public Student(String n, int i, double gpa) { name = n; id = i; this.gpa = gpa;}

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
    this.name = name;
  }
    public double getGpa() {
    return gpa;
  }
  public void setGpa(double gpa) {
    this.gpa = gpa;
  }

    public boolean equals(Object rhs)  {   // more compact code, but still correct
       if (rhs == null || getClass() != rhs.getClass())
           return false;
       Student other = (Student) rhs;
       return id == other.id;
    }
    public int compareTo(Student other)
        { return Integer.valueOf(id).compareTo(Integer.valueOf(other.id)); }
    public int hashCode()
        { return Integer.valueOf(id).hashCode(); }
    public String toString() { return "(" + name+ " " + id + " " + gpa + ")";}
}

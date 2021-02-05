public class n6 {
   // 6 b>Date:
   // has equals
   // based on month,day,year,
   // needs consistent hashCode;

public int hashCode () {
// or use another prime here, or just xor them, or ...
    return 31∗31∗month+ 31∗day + year ; }[5:22 PM]6 Counter>

    public boolean equals(Object other) {
        if (other == this)
            return true; // or skip this line if (other == null) return false ;
        if (other.getClass() != this.getClass())
            return false;
        Counter that = (Counter) other;
        return this.name == that.name;
    }

    public int hashCode() {
        return name.hasCode(); // Use String.hashCode
    }
}

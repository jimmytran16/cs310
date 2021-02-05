import java.util.*;

public class one { // number 1 , 2
	public static void main (String args[]) {
		String[] names = {"Joe","Annie","Bruce","Tien"};
		int[] nums = {3,2,10};

		Arrays.sort(names);
		Arrays.sort(nums);
		System.out.println(Arrays.toString(names));
		System.out.println(Arrays.toString(nums));
	}

	// private static Comparator<String> compareStringInOrder =  Comparator<String> () {
	// 	public int compare(String s1, String s2) {
	// 		return (res != 0) ? res : s1.compareTo(s2);
	// 	}
	// }
}

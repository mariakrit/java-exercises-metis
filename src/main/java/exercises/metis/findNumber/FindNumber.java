package exercises.metis.findNumber;

import java.util.Arrays;

public class FindNumber {

	public static String findLookupNumber(int[] intArray, int lookupNumber) {
		boolean found = Arrays.stream(intArray).anyMatch(num -> num == lookupNumber);
		return found ? "Found" : "There is no number " + lookupNumber + " in the array";
	}
}

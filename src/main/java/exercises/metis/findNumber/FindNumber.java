package exercises.metis.findNumber;

import org.springframework.stereotype.Component;

public class FindNumber {

	public static String findLookupNumber(int[] intArray, int lookupNumber) {

		for (int num : intArray) {
			if (num == lookupNumber) {
				return "Found";
			}
		}

		return "There is no number " + lookupNumber + " in the array";
	}
}

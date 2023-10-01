package exercises.metis.findNumber;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FindNumberTest {

	@Test
	void testFound() {
		int[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int lookupNumber = 9;
		String response = FindNumber.findLookupNumber(intArray, lookupNumber);
		assertEquals(response, "Found");
	}

	@Test
	void testNotFound() {
		int[] intArray = { 8, 6, 33, 100 };
		int lookupNumber = 7;
		String response = FindNumber.findLookupNumber(intArray, lookupNumber);
		assertEquals(response, "There is no number " + lookupNumber + " in the array");
	}

	@Test
	void testFoundCaseTwo() {
		int[] intArray = { 2, 55, 60, 97, 86 };
		int lookupNumber = 9;
		String response = FindNumber.findLookupNumber(intArray, lookupNumber);
		assertEquals(response, "There is no number " + lookupNumber + " in the array");
	}

}

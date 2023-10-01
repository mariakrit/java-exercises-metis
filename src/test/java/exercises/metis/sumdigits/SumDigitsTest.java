package exercises.metis.sumdigits;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SumDigitsTest {

	@Test
	void testCalculateSumDigitsCase10() {
		int n = 10;

		int calculatedSum = SumDigits.calculateSumDigits(n);
		assertTrue(calculatedSum < 10);
		assertTrue(calculatedSum == 1);
	}

	@Test
	void testCalculateSumDigitsCase38() {
		int n = 38;

		int calculatedSum = SumDigits.calculateSumDigits(n);
		assertTrue(calculatedSum < 10);
		assertTrue(calculatedSum == 2);
	}

	@Test
	void testCalculateSumDigitsCase785() {
		int n = 785;

		int calculatedSum = SumDigits.calculateSumDigits(n);
		assertTrue(calculatedSum < 10);
		assertTrue(calculatedSum == 2);
	}

	@Test
	void testCalculateSumDigitsCase99892() {
		int n = 99892;

		int calculatedSum = SumDigits.calculateSumDigits(n);
		assertTrue(calculatedSum < 10);
		assertTrue(calculatedSum == 1);
	}
}

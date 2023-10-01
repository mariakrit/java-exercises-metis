package exercises.metis.sumdigits;

import org.springframework.stereotype.Component;

public class SumDigits {

	public static int calculateSumDigits(int n) {

		int sum = n;

		while (sum >= 10) {
			int flag = 0;

			while (sum > 0) {
				flag += sum % 10;
				sum /= 10;
			}

			sum = flag;
		}

		return sum;
	}
}

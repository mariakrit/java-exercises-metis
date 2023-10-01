package exercises.metis.cryptographic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CryptographicTest {

	@Test
	void encryptTextCase1() {

		String text = "Cats are great pets.";

		String encryptedText = Cryptographic.encryptText(text);
		assertEquals(encryptedText, "Atscay areway reatgay etspay.");
	}

	@Test
	void encryptTextCase2() {

		String text = "Tom got a small piece of pie.";

		String encryptedText = Cryptographic.encryptText(text);
		assertEquals(encryptedText, "Omtay otgay away mallsay iecepay ofway iepay.");
	}

	@Test
	void encryptTextCase3() {

		String text = "He told us a very exciting tale.";

		String encryptedText = Cryptographic.encryptText(text);
		assertEquals(encryptedText, "Ehay oldtay usway away eryvay excitingway aletay.");
	}

}

package exercises.metis.cryptographic;

public class Cryptographic {

	public static String encryptText(String text) {
		String[] words = text.split("\\s+");
		StringBuilder result = new StringBuilder();

		for (String word : words) {
			char firstChar = word.charAt(0);

			String dotSubstring = null;

			boolean endsWithPunctuation = word.endsWith(".");

			if (endsWithPunctuation) {
				dotSubstring = word.substring(word.length() - 1, word.length());
				word = word.substring(0, word.length() - 1);
			} else {
				word = word.substring(0, word.length());
			}

			String modifiedWord;
			if (("aeiouAEIOU".contains(String.valueOf(firstChar)))) {
				modifiedWord = word + "way";
			} else {
				if (Character.isUpperCase(firstChar)) {
					modifiedWord = Character.toUpperCase(word.charAt(1)) + word.substring(2)
							+ Character.toLowerCase(word.charAt(0)) + "ay";
				} else {
					modifiedWord = word.charAt(1) + word.substring(2) + word.charAt(0) + "ay";
				}
			}

			result.append(modifiedWord);

			if (endsWithPunctuation) {
				return result.append(dotSubstring).toString();
			}
			result.append(" ");
		}

		return result.toString();
	}
}

package org.focusrobotique.common.io;

public class HexPrintWriter {

	public static void append(StringBuilder s, char c) {
		if (s == null) {
			return;
		}
		s.append(c);
	}

	public static int appendBool(StringBuilder s, boolean b) {
		if (b) {
			append(s, '1');
		} else {
			append(s, '0');
		}
		return 1;
	}

	public static int appendBoolAsString(StringBuilder s, boolean b) {
		if (b) {
			s.append("true");
			return 4;
		} else {
			s.append("false");
			return 5;
		}
	}

	public static void appendSeparator(StringBuilder s) {
		append(s, '-');
	}

	public static void appendSpace(StringBuilder s) {
		append(s, ' ');
	}

	// HEXADECIMAL

	public static char convertToHex(char c) {
		// Value between 0 and 9
		if (c < 10) {
			return (char) (c + 48);
		} else if (c < 16) {
			// Value between A and F
			// (65 - 10 + c)
			return (char) (c + 55);
		}
		return 0;
	}

	public static boolean appendHex(StringBuilder s, char c) {
		char hexChar = convertToHex(c);
		if (hexChar != 0) {
			append(s, hexChar);
			return true;
		}
		return false;
	}

	private static void internalAppendHex(StringBuilder s, long value, int shiftMax) {
		int i;
		for (i = shiftMax; i >= 0; i -= 4) {
			appendHex(s, (char) ((value >> i) & 0xF));
		}
	}

	public static void appendHex2(StringBuilder s, char value) {
		internalAppendHex(s, value, 4);
	}

	public static void appendHex3(StringBuilder s, int value) {
		internalAppendHex(s, value, 8);
	}

	public static void appendHex4(StringBuilder s, int value) {
		internalAppendHex(s, value, 12);
	}

	public static void appendHex5(StringBuilder s, int value) {
		internalAppendHex(s, value, 16);
	}

	public static void appendHex6(StringBuilder s, long value) {
		internalAppendHex(s, value, 20);
	}

	public static void appendHex8(StringBuilder s, long value) {
		internalAppendHex(s, value, 28);
	}

	// FLOAT

	public static void appendHexFloat4(StringBuilder s, float value, int digitPrecision) {
		// we append it as a long value excluding digit after comma (but we multiply it
		// before)
		float valueWrite = value;
		int i;
		for (i = 0; i < digitPrecision; i++) {
			valueWrite *= 10.0f;
		}
		int longValue = (int) valueWrite;
		appendHex4(s, longValue);
	}

	public static void appendHexFloat6(StringBuilder s, float value, int digitPrecision) {
		// we append it as a long value excluding digit after comma (but we multiply it
		// before)
		float valueWrite = value;
		int i;
		for (i = 0; i < digitPrecision; i++) {
			valueWrite *= 10.0f;
		}
		long longValue = (long) valueWrite;
		appendHex6(s, longValue);
	}

	public static void appendHexFloat8(StringBuilder s, float value, int digitPrecision) {
		// we append it as a long value excluding digit after comma (but we multiply it
		// before)
		float valueWrite = value;
		int i;
		for (i = 0; i < digitPrecision; i++) {
			valueWrite *= 10.0f;
		}
		long longValue = (long) valueWrite;
		appendHex8(s, longValue);
	}
}

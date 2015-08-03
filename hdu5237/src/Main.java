/**
 * 2015年7月14日 下午2:56:01
 * PrjName:hdu5237
 * @ Semprathlon
 */

import java.io.*;
import java.util.*;

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

}

public class Main {

	final static String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

	static String encode(String str) {
		String res = new String();
		int ch1 = 0, ch0, pos = 0;
		for (int i = 0; i < str.length(); i++) {
			ch0 = ch1;
			ch1 = str.charAt(i);

			switch (i % 3) {
			case 0:
				pos = ch1 >> 2;
				res += code.charAt(pos);
				break;
			case 1:
				pos = ((ch0 & 3) << 4) + ((ch1 & ~3) >> 4);
				res += code.charAt(pos);
				break;
			case 2:
				pos = ((ch0 & 15) << 2) + ((ch1 & ~15) >> 6);
				res += code.charAt(pos);
				pos = ch1 & 63;
				res += code.charAt(pos);
				break;
			}
		}
		if (str.length() % 3 == 2) {
			ch1 = str.charAt(str.length() - 1);
			pos = (ch1 & 15) << 2;
			res += code.charAt(pos);
			res += '=';
		} else if (str.length() % 3 == 1) {
			ch1 = str.charAt(str.length() - 1);
			pos = (ch1 & 3) << 4;
			res += code.charAt(pos);
			res += "==";
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		int T = in.nextInt(), cas = 0;
		while (T-- > 0) {
			int k = in.nextInt();
			String s = new String(in.next());
			for (int i = 1; i <= k; i++)
				s = encode(s);
			out.println("Case #" + (++cas) + ": " + s);

		}
		out.flush();
		out.close();
	}

}

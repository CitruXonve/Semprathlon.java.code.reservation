
/** Sep 6, 2015 8:40:50 PM
 * PrjName:hdu5240
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	static ArrayList<Data> vec = new ArrayList<Data>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt(), cas = 0;
		while (T-- > 0) {
			int n = in.nextInt();
			int s = 0;
			boolean ans = true;
			vec.clear();
			for (int i = 1; i <= n; i++)
				vec.add(new Data(in.nextInt(), in.nextInt(), in.nextInt()));
			vec.sort(new Data.Comp());
			for (int i = 0; i < vec.size(); i++) {
				int r = vec.get(i).r;
				int e = vec.get(i).e;
				int l = vec.get(i).l;
				s += r;
				if (s > e) {
					ans = false;
					break;
				}
				s += l;
			}
			out.println("Case #" + (++cas) + ": " + (ans ? "YES" : "NO"));
		}
		out.flush();
		out.close();
	}

}

class Data {
	int r, e, l;

	Data(int _r, int _e, int _l) {
		r = _r;
		e = _e;
		l = _l;
	}

	static class Comp implements Comparator<Data> {
		// @Override
		public int compare(Data d1, Data d2) {
			return Integer.compare(d1.e, d2.e);
		}
	}
}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
	}

	public String nextLine() {
		String tmp = null;
		try {
			tmp = reader.readLine();
			tokenizer = new StringTokenizer(tmp);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
			return null;
		}
		return tmp;
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

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}

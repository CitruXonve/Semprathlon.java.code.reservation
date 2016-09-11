
/**
 * May 5, 2016 7:27:18 PM
 * PrjName: 0505-L
 * @semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	static Vector<Pair<Integer, Integer>> tm = new Vector<Pair<Integer, Integer>>(20);
	static int[][] sum = new int[110][20];

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		for (int i = 0; i < 20; i++)
			tm.add(i, Pair.make_pair(-1, -1));
		int n = in.nextInt();
		int t = in.nextInt();
		int m = in.nextInt();
		while (m-- > 0) {
			int time = in.nextInt();
			int id = in.nextInt();
			String p = in.next();
			String res = in.next();
			int k = p.charAt(0) - 'A';
			if (res.charAt(0) != 'Y') {
				// sum[id]++;
				continue;
			}

			if (time/* +20*sum[id] */ >= tm.get(k).left && sum[id][k] < 1) {
				tm.set(k, Pair.make_pair(time/* +20*sum[id] */, id));
				sum[id][k]++;
			}
		}
		for (int i = 0; i < n; i++) {
			out.print((char) ('A' + i) + " ");
			if (tm.get(i).left > -1)
				out.println(tm.get(i).left + " " + tm.get(i).right);
			else
				out.println("- -");
		}
		out.flush();
		out.close();
	}

}

class Pair<TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>> implements Comparable<Pair<TypeA, TypeB>> {
	TypeA left;
	TypeB right;

	public Pair() {
	}

	public Pair(TypeA first, TypeB second) {
		left = first;
		right = second;
	}

	public static <TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>> Pair<TypeA, TypeB> make_pair(
			TypeA first, TypeB second) {
		return new Pair<TypeA, TypeB>(first, second);
	}

	public String toString() {
		return "[" + left.toString() + "," + right.toString() + "]";
	}

	boolean equals(Pair<TypeA, TypeB> p) {
		return this.left.equals(p.left) && this.right.equals(p.right);
	}

	public int compareTo(Pair<TypeA, TypeB> p) {
		if (this.left.equals(p.left))
			return this.right.compareTo(p.right);
		return this.left.compareTo(p.left);
	}
}

class Pair_Comp<TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>>
		implements Comparator<Pair<TypeA, TypeB>> {
	public int compare(Pair<TypeA, TypeB> p1, Pair<TypeA, TypeB> p2) {
		return p1.compareTo(p2);
	}
}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
	}

	public boolean hasNext() {
		return tokenizer != null && tokenizer.hasMoreTokens() || nextLine() != null;
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
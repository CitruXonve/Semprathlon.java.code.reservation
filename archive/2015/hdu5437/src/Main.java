
/** Sep 13, 2015 12:39:06 PM
 * PrjName:0913-01
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	static Person[] per;
	static Pair[] cm;
	static PriorityQueue<Person> que = new PriorityQueue<Person>(new Person.Comp());
	static Vector<String> ans = new Vector<String>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			int m = in.nextInt();
			int q = in.nextInt();
			per = new Person[n];
			for (int i = 0; i < n; i++)
				per[i] = new Person(in.next(), in.nextInt(), i + 1);
			cm = new Pair[m];
			for (int i = 0; i < m; i++)
				cm[i] = new Pair(in.nextInt(), in.nextInt());
			Arrays.sort(cm, new Pair.Comp());
			que.clear();
			int t = 0;
			ans.clear();
			for (int i = 0; i < n; i++) {
				que.add(per[i]);
				if (t < m && cm[t].l == i + 1) {
					for (int j = 0; j < cm[t].r && !que.isEmpty(); j++)
						ans.add(que.poll().name);
					t++;
				}
			}
			while (!que.isEmpty())
				ans.add(que.poll().name);
			// for(String str:ans.toArray(new String[0]))
			// out.println(str);
			while (q-- > 0) {
				out.print(ans.get(in.nextInt() - 1) + (q > 0 ? " " : ""));
			}
			out.println();
		}
		out.flush();
		out.close();
	}
}

class Person {
	String name;
	int v, t;

	Person() {
	}

	Person(String _nm, int _v, int _t) {
		name = _nm;
		v = _v;
		t = _t;
	}

	static class Comp implements Comparator<Person> {

		public int compare(Person p1, Person p2) {
			// TODO Auto-generated method stub
			if (p1.v == p2.v)
				return Integer.compare(p1.t, p2.t);
			return -Integer.compare(p1.v, p2.v);
		}

	}
}

class Pair {
	int l, r;

	Pair() {
	}

	Pair(int a, int b) {
		l = a;
		r = b;
	}

	static class Comp implements Comparator<Pair> {

		public int compare(Pair o1, Pair o2) {
			// TODO Auto-generated method stub
			if (o1.l == o2.l)
				return Integer.compare(o1.r, o2.r);
			return Integer.compare(o1.l, o2.l);
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
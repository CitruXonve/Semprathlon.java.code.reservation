
/**
 * May 1, 2016 4:37:31 PM
 * PrjName: 0501-E
 * @semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {
	static int n, m, r1, c1, r2, c2;
	static int inf = 0x3fffffff;
	static int[][] a;
	static int[][][] d;
	static boolean[][][] v;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static Queue<Pair<Integer, Integer>> que = new LinkedList<Pair<Integer, Integer>>();

	static boolean can(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m && a[x][y] > 0;
	}

	static int spfa1() {
		v = new boolean[n][m][1];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				d[i][j][0] = inf;

		d[r1][c1][0] = a[r1][c1];
		
		int target=inf;
		
		que.clear();
		que.add(Pair.make_pair(r1, c1));
		while (!que.isEmpty()) {
			Pair<Integer, Integer> u = que.poll();
			for (int k = 0; k < 4; k++) {
				int dx = u.left + dir[k][0];
				int dy = u.right + dir[k][1];
				if (can(dx, dy) && d[u.left][u.right][0] + a[dx][dy] < d[dx][dy][0]) {
					d[dx][dy][0] = d[u.left][u.right][0] + a[dx][dy];
					
					if (dx==r2&&dy==c2)
						target=Math.min(target, d[dx][dy][0]);
					
					if (!v[dx][dy][0] && d[dx][dy][0]<target) {
						v[dx][dy][0] = true;
						que.add(Pair.make_pair(dx, dy));
					}
				}
			}
			v[u.left][u.right][0] = false;
		}
		return d[r2][c2][0] == inf ? -1 : d[r2][c2][0];
	}

	static int spfa2() {
		v = new boolean[n][m][4];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				d[i][j][0] = d[i][j][1] = d[i][j][2] = d[i][j][3] = inf;

		d[r1][c1][0] = d[r1][c1][1] = d[r1][c1][2] = d[r1][c1][3] = a[r1][c1];
		
		int target=inf;

		que.clear();
		for (int k = 0; k < 4; k++)
			que.add(new Pair<Integer, Integer>(r1, c1, k));

		while (!que.isEmpty()) {
			Pair<Integer, Integer> u = que.poll();
			for (int k = 0; k < 4; k++) {
				int dx = u.left + dir[k][0];
				int dy = u.right + dir[k][1];
				if (k != u.d && can(dx, dy) && d[u.left][u.right][u.d] + a[dx][dy] < d[dx][dy][k]) {
					d[dx][dy][k] = d[u.left][u.right][u.d] + a[dx][dy];
					
					if (dx==r2&&dy==c2)
						target=Math.min(target, d[dx][dy][k]);
					
					if (!v[dx][dy][k] && d[dx][dy][k]<target ) {
						v[dx][dy][k] = true;
							que.add(new Pair<Integer, Integer>(dx, dy, k));
					}
				}
			}
			v[u.left][u.right][u.d] = false;
		}
		int res = inf;
		for (int k = 0; k < 4; k++)
			if (d[r2][c2][k] != inf)
				res = Math.min(res, d[r2][c2][k]);
		return res == inf ? -1 : res;
	}

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int cas = 0;
		while (in.hasNext()) {
			n = in.nextInt();
			m = in.nextInt();
			r1 = in.nextInt();
			r1--;
			c1 = in.nextInt();
			c1--;
			r2 = in.nextInt();
			r2--;
			c2 = in.nextInt();
			c2--;

			a = new int[n][m];
			d = new int[n][m][4];

			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++) {
					String s = in.next();
					if (s.charAt(0) != '*')
						a[i][j] = Integer.parseInt(s);
				}

			/*
			 * for(int i=0;i<n;i++){ for(int j=0;j<m;j++){
			 * out.print(a[i][j]+"\t"); } out.println(); }
			 */
			out.println("Case " + (++cas) + ": " + spfa1() + " " + spfa2());
		}
		out.flush();
		out.close();
	}

}

class Pair<TypeA extends Comparable<TypeA>, TypeB extends Comparable<TypeB>> implements Comparable<Pair<TypeA, TypeB>> {
	TypeA left;
	TypeB right;
	int c, d;

	public Pair(TypeA first, TypeB second) {
		left = first;
		right = second;
	}

	public Pair(TypeA first, TypeB second, int _d) {
		left = first;
		right = second;
		d = _d;
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
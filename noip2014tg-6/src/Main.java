import java.util.Scanner;
import java.io.BufferedInputStream;
import java.math.BigInteger;

public class Main {
	final static BigInteger[] P = { new BigInteger("4969"),
			new BigInteger("4973"), new BigInteger("5233"),
			new BigInteger("5237") };
	final static int[] p = { 4969, 4973, 5233, 5237 };
	final static int maxn = 110;
	final static int bound = 10000;
	final static int maxm = 1000010;

	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedInputStream(System.in));
		int n = scan.nextInt();
		int m = scan.nextInt();
		int r[] = new int[maxm];
		BigInteger[] A = new BigInteger[maxn];
		BigInteger[][] a = new BigInteger[4][maxn];
		
		for (int i = 0; i <= n; i++) {
			A[i] = scan.nextBigInteger();
			a[0][i] = A[i].remainder(P[0]);
			a[1][i] = A[i].remainder(P[1]);
			a[2][i] = A[i].remainder(P[2]);
			a[3][i] = A[i].remainder(P[3]);
		}
		
		boolean[][] v = new boolean[4][bound];
		BigInteger[] s = new BigInteger[4];
		BigInteger[] t = new BigInteger[4];
		for (int x = 1; x <= p[3]; x++) {
			BigInteger X = BigInteger.valueOf(x);
			s[0] = s[1] = s[2] = s[3] = BigInteger.ZERO;
			t[0] = t[1] = t[2] = t[3] = BigInteger.ONE;
			for (int i = 0; i <= n; i++) {
				s[0] = s[0].add(t[0].multiply(a[0][i])).remainder(P[0]);
				t[0] = t[0].multiply(X).remainder(P[0]);
				s[1] = s[1].add(t[1].multiply(a[1][i])).remainder(P[1]);
				t[1] = t[1].multiply(X).remainder(P[1]);
				s[2] = s[2].add(t[2].multiply(a[2][i])).remainder(P[2]);
				t[2] = t[2].multiply(X).remainder(P[2]);
				s[3] = s[3].add(t[3].multiply(a[3][i])).remainder(P[3]);
				t[3] = t[3].multiply(X).remainder(P[3]);
			}
			if (s[0].compareTo(BigInteger.ZERO) == 0)
				v[0][x] = true;
			if (s[1].compareTo(BigInteger.ZERO) == 0)
				v[1][x] = true;
			if (s[2].compareTo(BigInteger.ZERO) == 0)
				v[2][x] = true;
			if (s[3].compareTo(BigInteger.ZERO) == 0)
				v[3][x] = true;
		}
		
		for (int x = 1; x <= m; x++)
			if (v[0][x % p[0]] && v[1][x % p[1]] && v[2][x % p[2]]
					&& v[3][x % p[3]])
				r[++r[0]] = x;

		System.out.println(r[0]);
		for (int i = 1; i <= r[0]; i++)
			System.out.println(r[i]);
		scan.close();
	}
}
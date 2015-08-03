/**
 * 2015��7��14�� ����4:51:05
 * PrjName:hdu2604
 * @ Semprathlon
 */
import java.io.*;

class Matrix {
	int n, m, mod;
	int[][] dat;

	Matrix(int n, int m, int mod) {
		this.n = n;
		this.m = m;
		this.mod = mod;
		this.dat = new int[n][m];
	}

	Matrix(Matrix mat) {
		this.n = mat.n;
		this.m = mat.m;
		this.mod = mat.mod;
		this.dat = new int[n][m];
		for (int i = 0; i < mat.n; i++)
			for (int j = 0; j < mat.m; j++)
				this.dat[i][j] = mat.dat[i][j];
	}

	static Matrix one(Matrix mat) {
		Matrix res = new Matrix(mat.n, mat.m, mat.mod);
		for (int i = 0; i < Math.min(mat.n, mat.m); i++)
			res.dat[i][i] = 1;
		return res;
	}

	Matrix add(Matrix c) {
		Matrix res = new Matrix(this);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				res.dat[i][j] += c.dat[i][j];
				res.dat[i][j] %= mod;
			}
		return res;
	}

	Matrix mul(Matrix c) {
		Matrix res = new Matrix(this.n, c.m, mod);
		for (int i = 0; i < this.n; i++)
			for (int j = 0; j < c.m; j++)
				for (int k = 0; k < this.m; k++) {
					res.dat[i][j] += this.dat[i][k] * c.dat[k][j];
					res.dat[i][j] %= mod;
				}

		return res;
	}

	Matrix pow(int m) {
		Matrix n = new Matrix(this);
		Matrix res = Matrix.one(n);
		while (m > 0) {
			if ((m & 1) > 0)
				res = res.mul(n);
			n = n.mul(n);
			m >>= 1;
		}
		return res;
	}
}

public class Main {
	static Matrix mat, p;

	static void init(int mod) {
		mat = new Matrix(4, 1, mod);
		mat.dat = new int[][] { { 9 }, { 6 }, { 4 }, { 2 } };
		p = new Matrix(4, 4, mod);
		p.dat = new int[][] { { 1, 0, 1, 1 }, { 1, 0, 0, 0 }, { 0, 1, 0, 0 },
				{ 0, 0, 1, 0 } };
	}

	static int solve(int l, int m) {
		mat = p.pow(l - 4).mul(mat);
		if (l > 4)
			return mat.dat[0][0];
		else if (l > 0)
			return mat.dat[4 - l][0];
		return 0;
	}

	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int l = (int) in.nval;
			in.nextToken();
			int m = (int) in.nval;
			init(m);
			out.println(solve(l, m) % m);	
		}
		out.flush();
		out.close();
	}
}

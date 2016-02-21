
/** Aug 31, 2015 9:57:30 PM
 * PrjName:hdu2089
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	final static int maxl = 7;
	static int[][] f;
	static int[] digit;

	static void init() {
		f = new int[maxl + 1][10];
		f[0][0] = 1;
		for (int i = 1; i <= maxl; i++)
			for (int j = 0; j < 10; j++)
				for (int k = 0; k < 10; k++)
					if (k != 4 && !(j == 6 && k == 2))
						f[i][j] += f[i - 1][k];
	}

	static int solve(int n) {
		// if (n==0) return 1;
		digit = new int[maxl + 1];
		while (n > 0) {
			digit[++digit[0]] = n % 10;
			n /= 10;
		}
		int res = 0;
		for (int i = digit[0]; i > 0; i--) {
			for (int j = 0; j < digit[i]; j++)
				if (j != 4 && !(j == 2 && digit[i + 1] == 6))
					res += f[i][j];
			if (digit[i] == 4 || digit[i] == 2 && digit[i + 1] == 6) break;
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out = new PrintWriter(System.out);
		init();
		/*for(int i=0;i<=maxl;i++){
			for(int j=0;j<10;j++) out.print(f[i][j]+" ");
			out.println();
		}
		out.flush();*/
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.nval;
			in.nextToken();
			int m = (int) in.nval;
			if (n == 0 && m == 0)
				break;
			out.println(solve(n)+" "+solve(m+1));
			out.println(solve(m + 1) - solve(n));
		}
		out.flush();
		out.close();
	}

}
/** Sep 19, 2015 3:18:41 PM
 * PrjName:0919-02-2
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static int x,m;
	final static double a1=5.0+2*Math.sqrt(6.0);
	final static double a2=5.0-2*Math.sqrt(6.0);
	static long pow_mod(long n, long m, long mod) {
		long res = 1L;
		n %= mod;
		while (m > 0L) {
			if ((m & 1L) > 0L)
				res = res * n % mod;
			n = n * n % mod;
			m >>= 1;
		}
		return res;
	}
	static double solve(double a,int x){
		if (x==0) return a*a%(double)m;
		double res=1,n=a;
		for(int i=1;i<=x;i++)
			n=n*n%(double)m;
		res=res*n%(double)m;
		return res;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			x=in.nextInt();
			m=in.nextInt();
			//double tmp=1+m*a+(double)m*(m-1)*a*a/2.0;
			double tmp=solve(5,x)+solve(Math.sqrt(24),x);
			out.println(tmp*2);
		}
		out.flush();
		out.close();
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

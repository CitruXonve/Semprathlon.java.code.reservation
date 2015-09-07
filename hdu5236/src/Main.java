/** Sep 7, 2015 6:37:43 PM
 * PrjName:hdu5236
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static double[] f;
	static int n,x;
	static double solve(int k){
		double res=k*x;
		if (n%k>0){
			//res+=f[n/k]*(k-1)+f[n%k];
			res+=f[n/k+1]*(n%k)+f[n/k]*(k-n%k);
		}
		else
			res+=f[n/k]*k;
		return res;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			n=in.nextInt();
			double p=in.nextDouble();
			x=in.nextInt();
			f=new double[n+1];
			for(int i=1;i<=n;i++)
				f[i]=(f[i-1]+1)/(1-p);
			double ans=f[n]+x;
			for(int i=2;i<=n;i++)
				ans=Math.min(ans, solve(i));
			out.println("Case #"+(++cas)+": "+String.format("%.6f", ans));
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

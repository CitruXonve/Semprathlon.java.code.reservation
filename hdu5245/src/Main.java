/** Sep 6, 2015 9:26:29 PM
 * PrjName:hdu5245
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static double pow(double n, int m) {
		double res = 1;
		while (m > 0) {
			if ((m & 1) > 0)
				res = res * n;
			n = n * n;
			m >>= 1;
		}
		return res;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int m=in.nextInt();
			int n=in.nextInt();
			int k=in.nextInt();
			double ans=0;
			for(int i=1;i<=m;i++)
				for(int j=1;j<=n;j++){
					double s=0;
					s+=(double)(i-1)*(i-1)*n*n;
					s+=(double)(m-i)*(m-i)*n*n;
					s+=(double)m*m*(j-1)*(j-1);
					s+=(double)m*m*(n-j)*(n-j);
					s-=(double)(i-1)*(i-1)*(j-1)*(j-1);
					s-=(double)(i-1)*(i-1)*(n-j)*(n-j);
					s-=(double)(m-i)*(m-i)*(j-1)*(j-1);
					s-=(double)(m-i)*(m-i)*(n-j)*(n-j);
					ans+=1.0-pow(s/n/n/m/m, k);
				}
			out.println("Case #"+(++cas)+": "+Math.round(ans));
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

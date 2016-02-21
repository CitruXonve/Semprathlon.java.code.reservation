/**
 * Nov 28, 2015 8:21:15 PM
 * PrjName: Bc64-03
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int maxn=57;
	static long[] a=new long[maxn];
	static long[] b=new long[maxn];
	static long fx(long n){
		if (n==1) return 1;
		if (n<1) return 0;
		int d=(int)(Math.log(n+1.0)/Math.log(2.0));
		return a[d]+fx(n-b[d]);
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			long n=in.nextLong();
			a[1]=1;
			b[1]=1;
			long m=1;
			for(int i=2;i<maxn;i++){
				b[i]=b[i-1]<<1|1;
				m<<=1;
				a[i]=(a[i-1]<<1)+m;
			}
			long tmp=fx(n);
//			out.println(tmp>1?tmp+1:1);
			out.println(tmp);
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

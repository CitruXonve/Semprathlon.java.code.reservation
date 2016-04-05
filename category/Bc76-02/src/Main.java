/**
 * Mar 19, 2016 7:43:41 PM
 * PrjName: Bc76-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static long mod=1000000007L;
	static int cal(long x,long n,long k){
		return ((x+x+k-1L)*k>>1)>n?1:0;
	}
	static int bisearch(int n,long k){
		int l=1,r=n;
		while(l<r){
			int m=(l+r+1)>>1;
			if (cal(m,n,k)>0)
				r=m-1;
			else
				l=m;
		}
		return l;
	}
	static long solve(int n,int k){
		if (((1L+k)*(long)k>>1)>(long)n)
			return -1;
		int tmp=bisearch(n, k);
		long res=1L;
		int sum=(tmp+tmp+k-1)*k>>1;
		for(int i=tmp;i<tmp+k;i++){
			if (tmp+k-i<=n-sum)
				res*=(long)(i+1);
			else
				res*=(long)i;
			res%=mod;
		}
		return res;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int k=in.nextInt();
			out.println(solve(n, k));
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


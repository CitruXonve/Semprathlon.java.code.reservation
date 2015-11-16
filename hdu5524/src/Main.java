/**
 * Nov 7, 2015 5:00:56 PM
 * PrjName: hdu5524
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=64;
	static long f[]=new long[maxn];
	static HashSet<Long> st=new HashSet<Long>();
	static void init(){
		f[0]=1L;
		for(int i=1;i<maxn;i++){
			f[i]=f[i-1]<<1;
			f[i-1]--;
		}
	}
	static void solve(long n){
		int l=0,r=maxn;
		while(l<r){
			int mid=(l+r+1)>>1;
			if (f[mid]>n)
				r=mid-1;
			else
				l=mid;
		}
//		return f[l];
		for(int i=0;i<l;i++)
			st.add(f[i]);
		if (f[l]<n){
			st.add(n-f[l-1]-1L);
			solve(n-f[l-1]-1L);
		}
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		while(in.nextLine()!=null){
			st.clear();
			long n=in.nextLong();
			solve(n);
			out.println(st.size());
			/*for(Long e:st.toArray(new Long[0]))
				out.print(e+",");
			out.println();*/
//			out.println(solve(n));
//			out.flush();
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

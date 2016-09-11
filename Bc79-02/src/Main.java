/**
 * Apr 9, 2016 7:24:06 PM
 * PrjName: Bc79-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static  int maxl=61;
	static long f[];
	static void init(){
		f=new long[maxl];
		f[0]=1;
		for(int i=1;i<maxl;i++)
			f[i]=f[i-1]<<1;
	}
	static void solve(long b,long d){
		int cnt=0;
		long x=0,y=0;
		int i=maxl-1;
		for(;i>=0;i--)
			if (f[i]>d) continue;
		for(;i>=0;i--)
			if (f[i]>b){
				x+=f[i];
			}
		
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		for(int i=0;i<maxl;i++)
			out.print(f[i]+" ");out.println();
		int T=in.nextInt();
		while(T-->0){
			long a=in.nextLong();
			long b=in.nextLong();
			long c=in.nextLong();
			long d=in.nextLong();
			if (b>d){
				long t=a;a=c;c=t;
				t=b;b=d;d=t;
			}
			
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

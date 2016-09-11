import java.io.*;
import java.util.*;
public class Main {
	static int[] a;
	final static long mod=1000000007;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			a=new int[n+1];
			long c1=0,c2=0,p=1;
			a[1]=in.nextInt();
			for(int i=2;i<=n;i++){
				a[i]=in.nextInt();
				p<<=1;p%=mod;
				c1<<=1;
				if (c1>=mod) c1-=mod;
				c2<<=1;
				if (c2>=mod) c2-=mod;
				if (a[i]<a[i-1]){
					c2+=p;//right
					if (c2>=mod) c2-=mod;
				}
				else if (a[i]>a[i-1]){
					c1+=p;//left
					if (c1>=mod) c1-=mod;
				}
			}
			out.println((c1+c2)%mod);
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

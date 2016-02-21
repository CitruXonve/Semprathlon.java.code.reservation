/**
 * Nov 28, 2015 8:04:23 PM
 * PrjName: Bc64-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a,s,f;
	static int fn(int x){
		return (1890*x+143)%10007;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			int n=in.nextInt();
			a=new int[n+1];
			s=new int[n+1];
			f=new int[n+1];
			for(int i=1;i<=n;i++){
				a[i]=in.nextInt();
				s[i]=s[i-1]+a[i];
			}
			int ans=s[n];
			for(int i=1;i<=n;i++){
				f[i]=Math.max(f[i-1]+fn(a[i]), s[i]);
				ans=Math.max(ans, f[i]+s[n]-s[i]);
			}
			out.println(ans);
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

/**
 * Mar 13, 2016 10:27:16 PM
 * PrjName: PREV-8
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=1000000;
	static boolean[] f;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int a=in.nextInt();
		int b=in.nextInt();
		f=new boolean[a*b+a+b];
		f[0]=true;
		int ans=1;
		for(int i=0;i<a*b;i++)
			if (f[i]){
				f[i+a]|=true;
				f[i+b]|=true;
			}
			else
				ans=Math.max(ans, i);
		out.println(ans);
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

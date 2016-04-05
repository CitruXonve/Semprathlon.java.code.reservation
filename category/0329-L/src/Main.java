/**
 * Mar 29, 2016 7:06:01 PM
 * PrjName: 0329-L
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	static int[] a;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			int x=0,y=0;
			a=new int[n];
			for(int i=0;i<m;i++){
				int k=in.nextInt();
				x=x*10+k;
			}
			for(int i=0;i<m;i++){
				int k=in.nextInt();
				y=y*10+k;
			}
			for(int i=0;i<n;i++)
				a[i]=in.nextInt();
			int ans=0;
			for(int i=0;i<n;i++){
				int s=0;
				for(int j=0;j<m;j++)
					s=s*10+a[(i+j)%n];
				if (x<=s&&s<=y) ans++;
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

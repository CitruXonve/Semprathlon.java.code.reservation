/**
 * Nov 14, 2015 7:17:47 PM
 * PrjName: Bc62-01
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Vector<Integer> vec=new Vector<Integer>();
	static Integer[] a;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int V=in.nextInt();
			vec.clear();
			for(int i=1;i<=n;i++)
				vec.add(in.nextInt());
			a=vec.toArray(new Integer[0]);
			Arrays.sort(a);
			int ans=0,has=0;
			for(int i=0;i<n;i++)
				if (has+a[i]<=V){
					has+=a[i];ans++;
				}
				else
					break;
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

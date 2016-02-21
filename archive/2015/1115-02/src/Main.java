/**
 * Nov 15, 2015 1:13:08 PM
 * PrjName: 1115-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static HashMap<Long, Long> mp=new HashMap<Long, Long>();
	static long f(long n){
		if (n<20150001L)
			return n+2014L;
		/*if (mp.containsKey(n))
			return mp.get(n);
		long tmp=f(f(n-2015));
		mp.put(n, tmp);
		return tmp;*/
		return 20152014L;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			long n=in.nextLong();
//			for(long n=0L;n<T;n++)
			out.println(f(n));
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

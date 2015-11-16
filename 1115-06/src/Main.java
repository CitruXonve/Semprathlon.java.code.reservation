/**
 * Nov 15, 2015 2:05:33 PM
 * PrjName: 1115-06
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] deg;
	static Integer[] a;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			int n=in.nextInt();
			int m=in.nextInt();
			deg=new int[n+1];
			for(int i=1;i<=m;i++){
				deg[in.nextInt()]++;
				in.nextInt();
			}
			a=new Integer[n];
			for(int i=0;i<n;i++)
				a[i]=i+1;
			Arrays.sort(a, new Comp());
			out.println(a[0]);
		}
		out.flush();
		out.close();
	}
	static class Comp implements Comparator<Integer>{
		public int compare(Integer a,Integer b){
			if (deg[a]==deg[b])
				return (int)(2.0*Math.signum(a-b));
			return -(int)(2.0*Math.signum(deg[a]-deg[b]));
		}
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


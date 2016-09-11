/**
 * Apr 10, 2016 2:36:27 PM
 * PrjName: 0410-I
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int minn=30,maxn=1510;
	static boolean isLeap(int y){
		return y%4==0&&y%100>0||y%400==0;
	}
	static HashSet<Integer> st=new HashSet<Integer>();
	static HashSet<Integer> st0;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		st.clear();
		
		for(int i=minn;i<=maxn;i++){
			int v=i*(2*i-1);
			if (v>=2016&&v<=990528&&isLeap(v))
				st.add(v);
		}
		st0=new HashSet<Integer>();
		for(int i=minn;i<=maxn;i++){
			int v=i*(i+1)/2;
			if (st.contains(v))
				st0.add(v);
		}
		Integer[] a=st0.toArray(new Integer[0]);
		Arrays.sort(a);
		for(Integer e:a)
			out.println(e);
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

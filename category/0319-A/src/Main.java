/**
 * Mar 19, 2016 4:37:10 PM
 * PrjName: 0319-A
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static HashSet<Integer> st=new HashSet<Integer>();
	static Vector<Integer> a=new Vector<Integer>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		st.clear();
		for(int i=1;i<=n;i++)
			st.add(in.nextInt());
		a.clear();
		for(Integer e:st){
			a.add(e);
		}
		boolean ans=false;
		for(int i=1;i<a.size()-1;i++)
			if (a.get(i)-a.get(i-1)<3&&a.get(i+1)-a.get(i)<3&&a.get(i+1)-a.get(i-1)<3){
				ans=true;break;
			}
		out.println(ans?"YES":"NO");
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

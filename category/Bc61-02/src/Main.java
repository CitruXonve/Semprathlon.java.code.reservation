/**
 * Oct 31, 2015 7:28:53 PM
 * PrjName: Bc61-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			int n=in.nextInt();
			int s=in.nextInt();
			int t=in.nextInt();
			if (s==t){
				out.println(n>1?-1:0);continue;
			}
			if (t==1){
				if (s==n)
					out.println(0);
				else if (s==2)
					out.println(1);
				else 
					out.println(2);
			}
			else if (t==n){
				if (s==1)
					out.println(0);
				else if (s==n-1)
					out.println(1);
				else
					out.println(2);
			}
			else{
				if (s==t+1||s==t-1||s==1||s==n)
					out.println(1);
				else 
					out.println(2);
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

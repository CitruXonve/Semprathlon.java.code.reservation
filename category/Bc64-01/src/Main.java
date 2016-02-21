/**
 * Nov 28, 2015 7:59:11 PM
 * PrjName: Bc64-01
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		String str=new String();
		while((str=in.nextLine())!=null){
			int len=str.length();
			char ch=str.charAt(len-1);
			if ((int)(ch-'0')%2==0||(int)(ch-'0')%5==0){
				out.println("YES");continue;
			}
			int s=0;
			for(char c:str.toCharArray())
				s+=(int)(c-'0');
			out.println(s%3==0?"YES":"NO");
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

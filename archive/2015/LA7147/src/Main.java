/**
 * Dec 1, 2015 8:20:12 PM
 * PrjName: LA7147
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			long n=in.nextLong();
			long m=in.nextLong();
			long a=in.nextLong();
			long b=in.nextLong();
			long c=in.nextLong();
			if (a<c){
				a^=c;c^=a;a^=c;
			}
			long ans1=Math.max(a, b)*(n-m-1);
			long ans2=Math.min(c, b)*(m-1);
			ans1+=Math.max(b*m, m/2*a+m/2*c+((m&1L)>0?Math.max(b, c):0));
			ans2+=Math.min(b*(n-m), (n-m)/2*a+(n-m)/2*c+((n-m&1L)>0?Math.min(b, a):0));
			out.println("Case #"+(++cas)+": "+ans1+" "+ans2);
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

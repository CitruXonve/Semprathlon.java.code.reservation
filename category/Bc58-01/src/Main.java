/** Oct 3, 2015 7:02:23 PM
 * PrjName:Bc58-01
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a,b;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			a=new int[n];
			b=new int[n];
			for(int i=0;i<n;i++)
				a[i]=in.nextInt();
			for(int i=0;i<n;i++)
				b[i]=in.nextInt();
			Arrays.sort(a);
			Arrays.sort(b);
			int suma=0,sumb=0;
			for(int i=0;i<m;i++)
				suma+=a[i];
			for(int i=n-1;i>n-1-m;i--)
				sumb+=b[i];
			out.println(suma>sumb?"YES":"NO");
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

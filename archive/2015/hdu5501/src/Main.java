/** Oct 13, 2015 9:41:08 PM
 * PrjName:hdu5501
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] w,v,d,f;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			w=new int[n+1];
			v=new int[n+1];
			d=new int[n+1];
			f=new int[m+1];
			for(int i=1;i<=n;i++){
				v[i]=in.nextInt();
				d[i]=in.nextInt();
				w[i]=in.nextInt();
			}
			for(int i=1;i<=n;i++)
			for(int j=m;j>=0;j--)
//			for(int j=1;j<=m;j++)
				
					if (j>=w[i])
					f[j]=Math.max(f[j], f[j-w[i]]+v[i]-d[i]*j);
			for(int j=0;j<=m;j++)
				out.print(f[j]+" ");out.println();
			out.println(f[m]);
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
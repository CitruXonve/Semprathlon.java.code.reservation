/** Oct 13, 2015 8:55:41 PM
 * PrjName:hdu5500
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a;
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			a=new int[n+1];
			for(int i=1;i<=n;i++)
				a[in.nextInt()]=i;
			int ans=0;
//			if (a[n]!=n&&a[n]!=1) ans++;
			int k=a[n];
			for(int i=n;i>1;i--)
				if (a[i-1]<k){
					k=a[i-1];n--;
				}
				else 
					break;
//			out.println(n);	
			for(int i=n-1;i>1;i--){
				if (a[i]==1)
					continue;
				boolean need=false;
				for(int j=1;j<i;j++)
					if (a[j]>a[i]) need|=true;
				for(int j=i+1;j<=n;j++)
					if (a[j]<a[i]) need|=true;
				if (need){
					ans++;
					for(int j=1;j<=n;j++)
						if (a[j]<a[i])
							a[j]++;
					a[i]=1;
				}
			}
			if (a[1]!=1) ans++;
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

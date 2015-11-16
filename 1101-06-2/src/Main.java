/**
 * Nov 1, 2015 2:08:51 PM
 * PrjName: 1101-06-2
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a;
	static boolean check(int a,int b,int c){
		return (c-b)*(b-a)<0&&(c-a)*(b-a)<0&&(c-a)*(c-b)>0;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			a=new int[n+1];
			for(int i=1;i<=n;i++)
				a[i]=in.nextInt();
			if (n<4){
				out.println("YES");
				continue;
			}
			int sum1=0,sum2=0;
			for(int i=2;i<n;i++){
				if (check(a[i-1],a[i],a[i+1]))
					sum1++;
				if (check(a[i+1],a[i],a[i-1]))
					sum2++;
			}
			out.println(sum1+" "+sum2);
			out.println(sum1==1&&sum2==n-3||sum1==n-3&&sum2==1||sum1==0||sum2==0?"YES":"NO");
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

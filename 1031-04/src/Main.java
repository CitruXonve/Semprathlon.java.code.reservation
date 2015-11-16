/**
 * Oct 31, 2015 8:16:12 PM
 * PrjName: 1031-04
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int kgcd(int a,int b)
	{
	    if(a==0) return b;
	    if(b==0) return a;
	    if((a&1)==0 && (b&1)==0)
	        return kgcd(a>>1,b>>1)<<1;
	    else if((b&1)==0) return kgcd(a,b>>1);
	    else if((a&1)==0) return kgcd(a>>1,b);
	    else return kgcd(Math.abs(a-b),Math.min(a,b));
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		
		while(T-->0){
			int n=in.nextInt();
			int a=in.nextInt();
			int b=in.nextInt();
			out.print("Case #"+(++cas)+": ");
			out.println(n/kgcd(a,b)%2==1?"Yuwgna":"Iaka");
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


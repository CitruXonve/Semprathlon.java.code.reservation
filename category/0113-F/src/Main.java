/**
 * Jan 13, 2016 7:04:58 PM
 * PrjName: 0113-F
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			long b=in.nextLong();
			int n=in.nextInt();
			long m=0,p=1;
			for(int i=1;i<=n;i++){
				m+=p;
				p*=b;
			}
			int upper=(int)Math.sqrt((double)m);
			boolean ans=(m==1L)?false:true;
			for(int i=2;i<=upper;i++)
				if (m%(long)i==0L){
					ans=false;break;
				}
//			out.println(m+" "+upper);
			out.println(ans?"YES":"NO");
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

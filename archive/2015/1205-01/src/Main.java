/**
 * Dec 5, 2015 7:03:33 PM
 * PrjName: 1205-01
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static String t1="ATCG";
	final static String t2="UAGC";		
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int l=in.nextInt();
			String s1=in.next();
			String s2=in.next();
			boolean ans=true;
			for(int i=0;i<l;i++){
				char ch1=s1.charAt(i);
				char ch2=s2.charAt(i);
				boolean tmp=false;
				for(int k=0;k<4;k++)
					if (t1.charAt(k)==ch1&&t2.charAt(k)==ch2)
					{
						tmp=true;break;
					}
				if (!tmp){
					ans=false;break;
				}
			}
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

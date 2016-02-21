/**
 * Dec 27, 2015 7:35:10 PM
 * PrjName: Cf337B
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Vector<Integer> v=new Vector<>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			int n=in.nextInt();
			int minc=Integer.MAX_VALUE;
			v.clear();
			for(int i=1;i<=n;i++){
				int c=in.nextInt();
				if (c<=minc){
					if (c<minc){
						minc=c;v.clear();
					}
					v.add(i);
				}
			}
			long ans=0L;
			if (v.size()==1)
				ans=n-1;
			else if (v.size()<n){
				for(int i=1;i<v.size();i++)
				ans=Math.max(ans, v.get(i)-v.get(i-1)-1);
				ans=Math.max(ans, n-(v.get(v.size()-1)-v.get(0)+1));
			}
			
			ans+=minc*(long)n;
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

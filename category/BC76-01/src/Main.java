/**
 * Mar 19, 2016 7:01:35 PM
 * PrjName: BC76-01
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static Map<Integer, Integer> mp=new HashMap<Integer,Integer>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			mp.clear();
			for(int i=1;i<=n;i++){
				int k=in.nextInt();
				int s=mp.containsKey(k)?mp.get(k):0;
				mp.put(k, s+1);
			}
			double ans=0;
			int tmp=n;
			for(Map.Entry<Integer, Integer> ent:mp.entrySet()){
				int s=ent.getValue();
				tmp-=s;
				if (tmp>0)
					ans+=s/(double)n*tmp/(double)(n-1);
			}
			out.println(String.format("%.6f", ans));
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

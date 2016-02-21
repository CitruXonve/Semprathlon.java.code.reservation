/** Sep 19, 2015 7:12:36 PM
 * PrjName:Bc56-01
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxn=110;
	static int cnt;
	static int[] w;
	static HashMap<Integer, Integer> mp=new HashMap<Integer,Integer>();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		//h=new int[maxn];
		w=new int[maxn];
		while(T-->0){
			int n=in.nextInt();
			cnt=0;
			mp.clear();
			Arrays.fill(w, 0);
			for(int i=1;i<=n;i++){
				int k=in.nextInt();
				if (mp.containsKey(k))
					w[mp.get(k)]+=in.nextInt();
				else{
					w[cnt]+=in.nextInt();
					mp.put(k, cnt++);
				}
			}
			int sum=0;
			for(int i=0;i<cnt;i++)
				sum+=(w[i]+63)/64;
			out.println((sum+35)/36);
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

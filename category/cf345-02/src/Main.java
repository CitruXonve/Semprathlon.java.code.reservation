/**
 * Mar 7, 2016 5:51:49 PM
 * PrjName: cf345-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a,s;
	static Map<Integer, Integer> mp=new HashMap<Integer,Integer>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		a=new int[n+1];
		s=new int[n+1];
		mp.clear();
		for(int i=1;i<=n;i++){
			a[i]=in.nextInt();
			int tmp=(mp.get(a[i])!=null)?mp.get(a[i]):0;
			mp.put(a[i], tmp+1);
		}
		int maxs=0;
		for(Iterator<Map.Entry<Integer, Integer>> it=mp.entrySet().iterator();it.hasNext();){
			Map.Entry<Integer, Integer> ent=it.next();
//			out.println(ent.getKey()+","+ent.getValue());
			s[ent.getValue()]++;
			maxs=Math.max(maxs, ent.getValue());
		}
		for(int i=maxs;i>1;i--)
			s[i-1]+=s[i];
		int ans=0;
		for(int i=maxs;i>=1;i--)
			ans+=s[i]-1;
		out.println(ans);
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

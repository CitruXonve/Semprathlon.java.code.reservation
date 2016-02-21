/** Sep 26, 2015 12:13:24 PM
 * PrjName:0926-10
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static Vector<Pair> vec=new Vector<Pair>();
	static Pair[] pr;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int n=in.nextInt();
			int a=in.nextInt();
			int b=in.nextInt();
			int l=in.nextInt();
			vec.clear();
			for(int i=1;i<=n;i++)
				vec.add(new Pair(in.nextInt(), in.nextInt()));
			pr=vec.toArray(new Pair[0]);
			Arrays.sort(pr, new Pair.comp());
			int last=0,tmp=0,ans=0;
			for(Pair p:pr){
				tmp+=b*(p.a-last);
				ans=Math.min(ans, tmp);
				tmp-=a*(p.b-p.a);
				ans=Math.min(ans, tmp);
				last=p.b;
			}
			out.println("Case #"+(++cas)+": "+(ans>=0?0:Math.abs(ans)));
		}
		out.flush();
		out.close();
	}

}
class Pair{
	int a,b;
	Pair(){}
	Pair(int _a,int _b){
		a=_a;b=_b;
	}
	static class comp implements Comparator<Pair>{
		public int compare(Pair p1,Pair p2){
			return Integer.compare(p1.a, p2.a);
		}
	};
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

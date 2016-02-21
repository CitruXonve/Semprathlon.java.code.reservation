/** Sep 19, 2015 12:10:39 PM
 * PrjName:0919-12
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static long[] t;
	static Vector<Long> v=new Vector<Long>();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int n=in.nextInt();
			long a=in.nextLong();
			long b=in.nextLong();
			t=new long[n];
			for(int i=0;i<n;i++)
				t[i]=in.nextLong();
			Arrays.sort(t);
			v.clear();
			if (n<=10)
				for(long e:t)
					v.add(e);
			else{
				v.add(t[0]);
				v.add(t[1]);
				v.add(t[n-2]);
				v.add(t[n-1]);
			}
			for(int i=0;i<n;i++)
				t[i]=Math.abs(t[i]);
			Arrays.sort(t);
			if (n>10){
				v.add(t[0]);
				v.add(t[1]);
			}
			long maxv=-1000000000000000L;
			for(int i=0;i<v.size();i++)
				for(int j=0;j<v.size();j++)
					if (i!=j)
						maxv=Math.max(maxv, a*v.get(i)*v.get(i)+b*v.get(j));
			out.println("Case #"+(++cas)+": "+maxv);
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

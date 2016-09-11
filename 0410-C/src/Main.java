import java.io.*;
import java.util.*;
public class Main {
	final static  int maxn=100010;
	final static long mod=1000000007;
	static long[] f=new long[maxn];
//	static Set<Integer> st=new HashSet<Integer>();
	static boolean[] vis=new boolean[maxn];
	static void init(){
		long p=2;
		f[2]=1;
		for(int i=3;i<maxn;i++){
			f[i]=(f[i-1]<<1)+p;
			if (f[i]>=mod) f[i]-=mod;
			if (f[i]>=mod) f[i]-=mod;
			p<<=1;
			if (p>=mod) p-=mod;
		}
		for(int i=2;i<maxn;i++){
			f[i]<<=1;
			if (f[i]>=mod) f[i]-=mod;
		}
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		int T=in.nextInt();
		while(T-->0){
			Arrays.fill(vis, false);
			int n=in.nextInt(),cnt=0;
//			st.clear();
			for(int i=0;i<n;i++){
				int k=in.nextInt();
//				st.add(k);
				if (!vis[k]){
					vis[k]=true;cnt++;
				}
			}
//			cnt=st.size();
//			out.println((f[n]<<1)%mod);
			out.println(f[cnt]);
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

/** Sep 19, 2015 7:24:54 PM
 * PrjName:Bc56-02
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxn=1010;
	final static int high=1000000000;
	final static int mod=1000000007;
	//static int[] s=new int[maxn];
	static int[][] f=new int[2][maxn];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int p=in.nextInt();
			//Arrays.fill(s, 0, p, 0);
			Arrays.fill(f[0], 0, p, 0);
			f[0][0]=1;
			int cnt=0;
			//for(int j=0;j<p;j++)out.print(f[j]+" ");out.println();
			for(int i=1;i<=n;i++){
				int k=in.nextInt();
				while(k<0) k+=p;
				k%=p;
				Arrays.fill(f[cnt^1], 0,p,0);
				for(int j=0;j<p;j++){
					f[cnt^1][(k+j)%p]+=f[cnt][j];
					f[cnt^1][(k+j)%p]%=mod;
				}
				for(int j=0;j<p;j++){
					f[cnt^1][j]+=f[cnt][j];
					f[cnt^1][j]%=mod;
				}
				cnt^=1;
				//for(int j=0;j<p;j++)out.print(f[cnt][j]+" ");out.println();
			}
			out.println(f[cnt][0]);
			
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

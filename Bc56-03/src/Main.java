/** Sep 19, 2015 8:18:00 PM
 * PrjName:Bc56-03
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static int x1,y1,x2,y2;
	static int n,m;
	static boolean ans;
	static int[][] mt;
	static int[] f;
	static boolean dfs(int step){
		if (f[step]==2&&(step&1)>0) ans=true;
		if (f[step]>0)
			return f[step]>1?true:false;
		for(int i=x1;i<=x2;i++)
			for(int j=y1;j<=y2;j++)
				if (mt[i][j]>0){
					int tmp=mt[i][j];
					mt[i][j]=0;
					if (!dfs(step+1)){
						mt[i][j]=tmp;
						f[step]=2;
						return true;
					}
					if (tmp>1){
						mt[i][j]=1;
						if (!dfs(step+1)){
							mt[i][j]=tmp;
							f[step]=2;
							return true;
						}
					}
					mt[i][j]=tmp;
				}
		f[step]=1;
		if (f[step]==2&&(step&1)>0) ans=true;
		return false;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			int q=in.nextInt();
			mt=new int[n+1][m+1];
			f=new int[n*m*2+100];
			for(int i=1;i<=n;i++)
				for(int j=1;j<=m;j++)
					mt[i][j]=in.nextInt();
			while(q-->0){
				int k=in.nextInt();
				if (k==1){
					x1=in.nextInt();
					y1=in.nextInt();
					x2=in.nextInt();
					y2=in.nextInt();
					Arrays.fill(f, 0);
					ans=false;
					dfs(0);
					out.println(ans?"Yes":"No");
				}
				else{
					int x,y,z;
					x=in.nextInt();
					y=in.nextInt();
					z=in.nextInt();
					mt[x][y]=z;
				}
			}
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

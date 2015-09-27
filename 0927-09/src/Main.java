/** Sep 27, 2015 12:05:29 PM
 * PrjName:0927-09
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] a;
	static int n,m;
	static double ans;
	//static PrintWriter out=new PrintWriter(System.out);
	static Vector<Integer> vec=new Vector<Integer>();
	static void dfs(int x,int y,Vector<Integer> path,int sum){
		if (x==n&&y==m){
			double res=0;
			
			for(int i=0;i<path.size();i++){
				//out.print(path.get(i)+" ");
				double tmp=(double)path.get(i)-sum/(double)(n+m-1);
				res+=tmp*tmp;
			}
			ans=Math.min(ans, res*(n+m-1));
			//out.println();
		}
		if (x<n){
			path.add(a[x+1][y]);
			dfs(x+1,y,path,sum+a[x+1][y]);
			path.remove(path.size()-1);
		}
		if (y<m){
			path.add(a[x][y+1]);
			dfs(x,y+1,path,sum+a[x][y+1]);
			path.remove(path.size()-1);
		}
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			a=new int[n+1][m+1];
			for(int i=1;i<=n;i++)
				for(int j=1;j<=m;j++)
					a[i][j]=in.nextInt();
			ans=10000000.0;
			vec.clear();
			vec.add(a[1][1]);
			dfs(1, 1, vec, a[1][1]);
			out.println("Case #"+(++cas)+": "+Math.round(ans));
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

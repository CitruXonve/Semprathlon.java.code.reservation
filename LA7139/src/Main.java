/**
 * Dec 4, 2015 9:03:20 PM
 * PrjName: LA7139
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] g;
	static void add(int r,int c1,int c2,int v){
		g[r][c1]+=v;
		g[r][c2]-=v;
	}
	static void debug(int n,int m,PrintWriter out){
		for(int i=1;i<=n+1;i++){
			for(int j=1;j<=m+1;j++)
				out.print(g[i][j]+" ");
			out.println();
		}
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			int k=in.nextInt();
			g=new int[n+2][m+2];
			int x=1,y=1; 
			for(int i=1;i<=k;i++){
				String s=in.next();
				int l=in.nextInt();
//				out.println(s+","+l);
				switch (s.charAt(0)) {
				case 'R':
					add(x,y,y+l,1);y+=l;break;
				case 'L':
					add(x, y-l, y, -1);y-=l;break;
				case 'U':
					x-=l;break;
				case 'D':
					x+=l;break;
				}
			}

			long ans=0;
			for(int i=1;i<=n;i++)
				for(int j=1;j<=m;j++){
					g[i][j]+=g[i][j-1];
					
					g[i][j]+=g[i-1][j];
					g[i][j]-=g[i-1][j-1];//ans+=Math.min(Math.abs(g[i][j]), Math.abs(g[i-1][j]));
//					g[i][j]=g[i][j]+g[i-1][j]+g[i][j-1]-g[i-1][j-1];
					ans+=g[i][j]*g[i][j];
					
				}
//			debug(n, m, out);
			out.println("Case #"+(++cas)+": "+ans);
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

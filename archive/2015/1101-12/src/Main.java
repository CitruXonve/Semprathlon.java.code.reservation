/**
 * Nov 1, 2015 3:50:39 PM
 * PrjName: 1101-12
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	static int[][] g;
	static int[][] dir=new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			g=new int[n+1][m+1];
			int ans=0;
			for(int i=1;i<=n;i++)
				for(int j=1;j<=m;j++){
					g[i][j]=in.nextInt();
					ans+=g[i][j];
				}
			ans*=6;
			for(int i=1;i<=n;i++)
				for(int j=1;j<=m;j++){
					if (g[i][j]!=0)
						ans-=(g[i][j]-1)*2+1;
					for(int k=0;k<4;k++){
						int dx=i+dir[k][0];
						int dy=j+dir[k][1];
						if (dx>0&&dx<=n&&dy>0&&dy<=m)
							ans-=Math.min(g[i][j], g[dx][dy]);
					}
				}
			out.println(ans);
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

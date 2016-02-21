/**
 * Dec 26, 2015 7:31:30 PM
 * PrjName: Bc67-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static boolean[][] a;
	static int[][] dir=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
	static int n,m;
	static boolean can(int x,int y){
		if (x<1||y<1||x>n||y>m)
			return false;
		return true;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			a=new boolean[n+2][m+2];
			int sum=0;
			for(int i=1;i<=n;i++)
				for(int j=1;j<=m;j++){
					a[i][j]=in.nextInt()==1;
					if (!a[i][j]) sum++;
				}
			boolean ans=true;
			for(int i=1;i<=n;i++)
				if (ans)
				for(int j=1;j<=m;j++){
					int tmp=0;
					if (!a[i][j]) continue;
					for(int k=0;k<4;k++){
						int x=i+dir[k][0];
						int y=j+dir[k][1];
						if (!can(x,y)) continue;
						tmp+=a[x][y]?0:1;
					}
					if (tmp%2==0){
						ans=false;break;
					}
				}
			out.println(sum!=1?"YES":"NO");
//			out.println(ans&&sum!=1?"YES":"NO");
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

/**
 * Mar 25, 2016 7:53:07 PM
 * PrjName: ECNA2015-D
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] g;
	static int n,m,cnt;
	static int[][] d=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
	static boolean can(int x,int y){
		return x>=0&&x<n&&y>=0&&y<m;
	}
	static void scan(int num){
		if (num==1){
			for(int i=0;i<n;i++){
				if (g[i][0]==-1){
					g[i][0]=1;cnt++;
				}
				if (g[i][m-1]==-1){
					g[i][m-1]=1;cnt++;
				}
			}
			for(int j=0;j<m;j++){
				if (g[0][j]==-1){
					g[0][j]=1;cnt++;
				}
				if (g[n-1][j]==-1){
					g[n-1][j]=1;cnt++;
				}
			}
		}
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
				if (g[i][j]==num-1)
					for(int k=0;k<4;k++){
						int dx=i+d[k][0];
						int dy=j+d[k][1];
						if (can(dx,dy)&&g[dx][dy]==-1){
							g[dx][dy]=num;cnt++;
						}
					}
	}
	static int width(int x){
		if (x>9) return 3;
		return 2;
	}
	static void print(int num,PrintWriter out){
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				for(int k=1;k<=width(num)-(""+g[i][j]).length();k++)
					out.print(".");
				out.print(g[i][j]>0?g[i][j]:".");
			}
			out.println();
		}				
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			n=in.nextInt();
			m=in.nextInt();
			g=new int[n][m];
			cnt=0;
			for(int i=0;i<n;i++){
				String s=in.next();
				for(int j=0;j<m;j++)
					if (s.charAt(j)=='.'){
						g[i][j]=0;
						cnt++;
					}
					else if (s.charAt(j)=='T')
						g[i][j]=-1;
			}
			int num=1;
			while(cnt<n*m){
				scan(num++);
			}
			print(num,out);
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

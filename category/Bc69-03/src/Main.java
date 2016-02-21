/**
 * Jan 23, 2016 8:06:17 PM
 * PrjName: Bc69-03
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] dir={{0,1},{0,-1},{-1,0},{1,0}};
	static char[][] g;
	static int n,m;
	static long sum;
	static boolean[][] vis;
	static Vector<Character> vec=new Vector<Character>();
	static void print(Vector<Character> v,PrintWriter out){
		for(int i=0;i<v.size();i++)
			out.print(v.get(i)+",");
		out.println();
	}
	static boolean calc(Vector<Character> v){
		long z=v.get(0)-'0',m=1;
		for(int i=1;i<v.size()-1;i+=2){
			switch (v.get(i)) {
			case '+':
				z+=(v.get(i+1)-'0')*m;
				break;
			case '-':
				z-=(v.get(i+1)-'0')*m;
				break;
			case '*':
				z*=v.get(i+1)-'0';
				break;
			case '/':
				m*=v.get(i+1)-'0';
				break;
			}
		}
		if (m==0) 
			return false;
		else
			return z==sum*m;
	}
	static boolean can(int x,int y){
		return x>=0&&x<n&&y>=0&&y<m;
	}
	static boolean dfs(Vector<Character> v,int x,int y,PrintWriter out){
		v.add(g[x][y]);
		boolean has=false,res=calc(v);
		for(int k=0;k<4;k++)
			if (!res){
				int dx=x+dir[k][0]*2;
				int dy=y+dir[k][1]*2;
				if (can(dx,dy)&&!vis[dx][dy]){
					has=true;
					vis[dx][dy]=true;
					v.add(g[(x+dx)>>1][(y+dy)>>1]);
					res|=dfs(v,dx,dy,out);
					v.remove(v.size()-1);
					vis[dx][dy]=false;
				}
			}
		if (!has){
			//print(v, out);
			res|=calc(v);
			v.remove(v.size()-1);
			return res;
		}
		
		v.remove(v.size()-1);
		return res;
	}
	static boolean solve(PrintWriter out){
		vis=new boolean[n][m];
		for(int i=0;i<n;i+=2)
			for(int j=0;j<m;j+=2){
				vec.clear();
				vis[i][j]=true;
				if (dfs(vec,i,j,out))
					return true;
				vis[i][j]=false;
			}
		return false;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			sum=in.nextLong();
			g=new char[n][m];
			for(int i=0;i<n;i++){
				String s=in.next();
				for(int j=0;j<m;j++)
					g[i][j]=s.charAt(j);
			}
			out.println(solve(out)?"Possible":"Impossible");
			
			
//			vec=new  Vector<Character>();
//			String s=in.next();
//			for(int i=0;i<s.length();i++)
//				vec.add(s.charAt(i));
//			out.println(calc(vec));
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

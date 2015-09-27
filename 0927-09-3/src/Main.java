import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** Sep 27, 2015 2:27:24 PM
 * PrjName:0927-09-3
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] a;
	static int n,m;
	static void solve(){
		PriorityQueue<P> que=new PriorityQueue<P>();
		que.add(new P(1,1,a[1][1],0));
	}
	public static void main(String[] args) {
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			a=new int[n+1][m+1];
			for(int i=1;i<=n;i++)
				for(int j=1;j<=m;j++)
					a[i][j]=in.nextInt();
			
		}
		out.flush();
		out.close();
	}
}
class P{
	int x,y,w,v;
	P(){};
	P(int _x,int _y,int _w,int _v){
		x=_x;y=_y;w=_w;v=_v;
	}
	static class comp implements Comparator<P>{
		public int compare(P p1,P p2){
			return Integer.compare(p1.v, p2.v);
		}
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

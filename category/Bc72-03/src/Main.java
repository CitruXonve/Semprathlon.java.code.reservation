/**
 * Feb 13, 2016 7:59:14 PM
 * PrjName: Bc72-03
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	static Edge[] edge;
	static int[] f;
	static int getf(int x){
		if (f[x]==x) return x;
		f[x]=getf(f[x]);
		return f[x];
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			edge=new Edge[m];
			f=new int[n+1];
			for(int i=1;i<=n;i++)
				f[i]=i;
			for(int i=0;i<m;i++){
				edge[i]=new Edge(in.nextInt(), in.nextInt(), in.nextInt());
				if (edge[i].v>edge[i].nx){
					int t=edge[i].v;edge[i].v=edge[i].nx;edge[i].nx=edge[i].v;
				}
			}
			Arrays.sort(edge, new Edge.Comp());
//			for(int i=0;i<m;i++)
//				out.println(edge[i].w);
			int ans=0x7fffffff;
			for(int i=0;i<m;i++){
				if (getf(edge[i].nx)==edge[i].nx){
					f[getf(edge[i].v)]=getf(edge[i].nx);
					ans&=edge[i].w;
				}
				else if (getf(edge[i].v)==edge[i].v){
					f[getf(edge[i].nx)]=getf(edge[i].v);
					ans&=edge[i].w;
				}
				out.println(ans);
			}
			out.println(ans==0x7fffffff?0:ans);
		}
		out.flush();
		out.close();
	}

}
class Edge{
    int v,w,nx;
    Edge(int _u,int _v,int _w){
        v=_u;w=_w;nx=_v;
    }
    static class Comp implements Comparator<Edge>{
    	public int compare(Edge e1,Edge e2){
    		return -Integer.compare(e1.w, e2.w);
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

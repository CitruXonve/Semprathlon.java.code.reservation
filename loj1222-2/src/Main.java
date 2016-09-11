
/**
 * May 4, 2016 7:33:25 PM
 * PrjName: loj1222-2
 * @semprathlon
 */
import java.io.*;
import java.util.*;

class Graph{
	int n;
	int[][] adj;
	Graph(int _n){
		n=_n;
		adj=new int[n][n];
	}
}

class Hungarian{
	Graph g;
	int n,obj;
	int[] mx,my;
	boolean[] vis;
	Hungarian(Graph _g,int _n,int _v) {
		n=_n;obj=_v;
		vis=new boolean[n];
		g=_g;
		mx=new int[n<<1|1];
		my=new int[n<<1|1];
	}
	boolean dfs(int u){
        for(int i=1;i<=obj;i++){
        	if (g.adj[u][i]>0&&!vis[i]){
        		vis[i]=true;
        		if (my[i]==-1||dfs(my[i])){
        			my[i]=u;
        			mx[u]=i;
        			return true;
        		}
        	}
        }
        return false;
    }
	int maxmatch(PrintWriter out){
        int res=0;
        Arrays.fill(mx, -1);
        Arrays.fill(my, -1);
        for(int i=1;i<=obj;i++){
        	if (mx[i]!=-1) continue;
            Arrays.fill(vis, false);
            if (dfs(i)){
            	out.println(i+","+mx[i]+","+my[i]);
//            	res+=g.adj[i][match[n+i]];
            	res++;
            }
        }
        return res;
    }
}

public class Main {

	static Graph g;
	static Hungarian work;
	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		while (T-- > 0) {
			int n = in.nextInt();
			g=new Graph(n+1);
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++)
					g.adj[i][j]=in.nextInt();
			/*for(int v:g.adj[1])
				out.print(v+",");*/
			work=new Hungarian(g, n<<2,n);
			out.println(work.maxmatch(out));
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

	public boolean hasNext() {
		return tokenizer != null && tokenizer.hasMoreTokens() || nextLine() != null;
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
/**
 * May 5, 2016 11:28:04 AM
 * PrjName: loj1154
 * @semprathlon
 */

import java.io.*;
import java.util.*;


public class Main {

	/**
	 * @param args
	 */
	final static int /*maxm=21010,*/maxn=210;
	static int[] x=new int[maxn];
	static int[] y=new int[maxn];
	static int[] s=new int[maxn];
	static int[] f=new int[maxn];
	final static double eps=1e-3;
	final static int inf=0x3fffffff;
	static int dcmp(double d) {
	    return (d>eps?1:0)-(d<-eps?1:0);
	}
	static double dist(double x1,double y1,double x2,double y2){
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	static Dinic dinic=new Dinic();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while (T-->0) {
	        int n=in.nextInt();
	        double d=in.nextDouble();
	        int sum = 0;
	        for (int i = 1; i <= n; i++) {
	            x[i]=in.nextInt();
	            y[i]=in.nextInt();
	            s[i]=in.nextInt();
	            f[i]=in.nextInt();
	            sum += s[i];
	        }

	        out.print("Case "+(++cas)+": ");
	        int ans = 0;
	        for (int k = 1; k <= n; k++) {
	            dinic.init();
	            for (int i = 1; i <= n; i++) {
	                
	                dinic.addEdge(1, i + 1, s[i]);
	                dinic.addEdge(i + 1, i + n + 1, f[i]);
	                for (int j = 1; j < i; j++)
	                    if (dist(x[i], y[i], x[j], y[j])<=d) {
	                        //							out.println("["+i+","+j+"] ");
	                        dinic.addEdge(i + n + 1, j + 1, inf);
	                        dinic.addEdge(j + n + 1, i + 1, inf);
	                    }
	            }
	            dinic.addEdge(k + 1, n + n + 2, inf);
//	            				out.print(dinic.maxflow(1,n+n+2)+" ");
	            if (dinic.maxflow(1, n + n + 2) == sum) {
	            	out.print((ans>0? " " : "")+(k-1));
	                ans++;
	            }
	        }
	        out.println(ans==0 ? "-1" : "");
	    }
		out.flush();
		out.close();
	}

}
class Dinic
{
    final int maxm=21010,maxn=210;
    final int inf=0x3fffffff;
    class Edge{
        int to, cap, flow, next;
        //Edge(){}
        /*Edge(int _v,int _f,int _w,int _nx){
            to=_v;
            cap=_f;
            flow=_w;
            next=_nx;
        }*/
        void modify(int _v,int _f,int _w,int _nx){
            to=_v;
            cap=_f;
            flow=_w;
            next=_nx;
        }
    };
    Edge[] edge;
    int[] head;
    int edgenum;
    Dinic(){
        edge=new Edge[maxm];
        for(int i=0;i<maxm;i++)
            edge[i]=new Edge();
        head=new int[maxn];
        dist=new int[maxn];
        vis=new boolean[maxn];
    }
    void init(){
        edgenum = 0;
        Arrays.fill(head, -1);
    }
    void addEdge(int u, int v, int w)
    {
        edge[edgenum].modify(v, w, 0, head[u]);
        head[u] = edgenum++;
        edge[edgenum].modify(u, 0, 0, head[v]);
        head[v] = edgenum++;
    }
    int[] dist; boolean[] vis;
    Queue<Integer> Q=new LinkedList<Integer>();
    boolean bfs(int s, int t)
    {
        Q.clear();
        Arrays.fill(dist, -1);
        Arrays.fill(vis, false);
        dist[s] = 0;
        Q.add(s);
        vis[s] = true;
        while(!Q.isEmpty())
        {
            int u = Q.poll();
            for(int i = head[u]; i != -1; i = edge[i].next)
            {
                Edge E = edge[i];
                if(!vis[E.to] && E.cap > E.flow)
                {
                    dist[E.to] = dist[u] + 1;
                    if(E.to == t) return true;
                    vis[E.to] = true;
                    Q.add(E.to);
                }
            }
        }
        return false;
    }
    int dfs(int x, int a, int t)
    {
        if(x == t || a == 0) return a;
        int flow = 0, f;
        for(int i = head[x]; i != -1; i = edge[i].next)
        {
            Edge E = edge[i];
            if(dist[E.to] == dist[x] + 1 && (f = dfs(E.to, Math.min(a, E.cap-E.flow), t)) > 0)
            {
                edge[i].flow += f;
                edge[i^1].flow -= f;
                flow += f;
                a -= f;
                if(a == 0) break;
            }
        }
        return flow;
    }
    int maxflow(int s, int t)
    {
        int flow = 0;
        while(bfs(s, t))
        {
            flow += dfs(s, inf, t);
        }
        return flow;
    }
};
/*class Edge {
	int v, f, w, nx;

	Edge(int _v, int _f, int _w, int _nx) {
		v = _v; f = _f; w = _w; nx = _nx;
	}
}*/
/*class Graph {
	int[] h;
	int n, m;
	Edge[] e;

	Graph(int _n, int _m) {
		n = _n; m = _m;
		h = new int[n];
		Arrays.fill(h, -1);
		h[0] = 0;
		e = new Edge[m];
	}

	void addedge(int u, int v, int f, int w) {
		e[h[0]] = new Edge(v, f, w, h[u]);
		h[u] = h[0]++;
	}
}*/
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
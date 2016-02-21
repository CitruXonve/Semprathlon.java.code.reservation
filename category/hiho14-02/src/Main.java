/** Aug 30, 2015 8:31:12 PM
 * PrjName:hiho14-02
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxn=200010;
	static Graph G=new Graph(maxn);
	static int[] dep;
	static int tmp,maxd;
	static void bfs(int st){
		Queue<Integer> que=new LinkedList<Integer>();
		que.add(st);
		while(!que.isEmpty()){
			int u=que.poll();
			for(int i=G.h[u];i>-1;i=G.edge[i].next){
				int v=G.edge[i].to;
				if (dep[v]>0) continue;
				dep[v]=dep[u]+1;
				que.add(v);
			}
		}
	}
	static boolean dfs(int st){
		tmp=Math.max(tmp, dep[st]);
		if (dep[st]==maxd)
			return true;
		for(int i=G.h[st];i>-1;i=G.edge[i].next){
			if (G.vis[i]) continue;
			G.vis[i]=true;
			if (dfs(G.edge[i].to)) return true; 
			G.vis[i]=false;
		}
		return false;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		{
			G.clear();
			dep=new int[n+1];
			dep[1]=1;
			for(int i=1;i<n;i++){
				int u=in.nextInt();
				int v=in.nextInt();
				G.add(u, v);
				//G.add(v, u);
			}
			bfs(1);
			int ans=0; 
			maxd=0;
			for(int i=1;i<=n;i++)
				maxd=Math.max(maxd, dep[i]);
			ans+=maxd-1;
			dfs(1);
			tmp=0;
			dfs(1);
			ans+=tmp-1;
			out.println(ans);
		}
		out.flush();
		out.close();
	}

}
class Edge{
	int to,next;
	Edge(int _u,int _v){
		to=_u;next=_v;
	}
}
class Graph{
	int[] h;
	int sz;
	Edge[] edge;
	boolean[] vis;
	Graph(int size){
		sz=size;
		vis=new boolean[sz+1];
		h=new int[sz+1];
		edge=new Edge[sz+1];
		Arrays.fill(h, -1);
		h[0]=0;
	}
	void clear(){
		edge=new Edge[sz+1];
		Arrays.fill(h, -1);
		Arrays.fill(vis, false);
		h[0]=0;
	}
	void add(int u,int v){
		edge[h[0]]=new Edge(v,h[u]);
		h[u]=h[0]++;
	}
}
class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;
 
    public InputReader(InputStream stream){
           reader = new BufferedReader(
                   new InputStreamReader(stream), 32768);
           tokenizer = null;
    }
 
    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(
                           reader.readLine());
            }catch (IOException e) {
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
 
}

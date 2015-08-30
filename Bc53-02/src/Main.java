/** Aug 29, 2015 7:59:14 PM
 * PrjName:Bc53-02
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxn=2010;
	static Graph G=new Graph(maxn);
	static int[] dep;
	static HashSet<Integer> st=new HashSet<Integer>();
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
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			int n=(int)in.nval;
			G.clear();
			dep=new int[n+1];
			dep[1]=1;
			for(int i=1;i<n;i++){
				in.nextToken();
				int u=(int)in.nval;
				in.nextToken();
				int v=(int)in.nval;
				G.add(u, v);
				G.add(v, u);
			}
			bfs(1);
			boolean ans=true;
			st.clear();
			int maxd=0;
			for(int i=1;i<=n;i++)
				maxd=Math.max(dep[i], maxd);
			for(int i=1;i<=n;i++)
				if (st.contains(dep[i])){
					if (dep[i]<maxd){
						ans=false;
						break;
					}
				}
				else
					st.add(dep[i]);
			if (maxd<3)
				ans=true;
			out.println(ans?"YES":"NO");
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
	Graph(int size){
		sz=size;
		h=new int[sz+1];
		edge=new Edge[sz+1];
		Arrays.fill(h, -1);
		h[0]=0;
	}
	void clear(){
		h=new int[sz+1];
		edge=new Edge[sz+1];
		Arrays.fill(h, -1);
		h[0]=0;
	}
	void add(int u,int v){
		edge[h[0]]=new Edge(v,h[u]);
		h[u]=h[0]++;
	}
}

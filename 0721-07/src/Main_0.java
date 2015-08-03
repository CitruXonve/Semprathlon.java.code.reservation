/**
 * 2015年7月22日 上午10:07:44
 * PrjName:0721-07
 * @ Semprathlon
 */
/*import java.io.*;
import java.util.*;

class Edge{
	int to,cap,rev,next;
	Edge(){}
	Edge(int t,int w,int r,int nt){
		to=t;cap=w;rev=r;next=nt;
	}
	Edge(Edge e){
		this.to=e.to;
		this.cap=e.cap;
		this.rev=e.rev;
		this.next=e.next;
	}
	void set(int t,int w,int r,int nt){
		to=t;cap=w;rev=r;next=nt;
	}
	void set(Edge e){
		this.to=e.to;
		this.cap=e.cap;
		this.rev=e.rev;
		this.next=e.next;
	}
}
class Queue<T>{
	T[] data;
	int size,h,r;
	Queue(Class<T> c,int sz){
		size=sz;
		@SuppressWarnings("unchecked")
		final T[] tmp=(T[])Array.newInstance(c, sz);
		data=tmp;
	}
	Queue(int sz){
		size=sz;
		data=(T[])new Object[sz];
	}
	void clear(){
		h=r=0;
	}
	boolean empty(){
		return h==r;
	}
	boolean full(){
		return (r+1)%size==h;
	}
	void push(T n){
		data[r]=n;
		r=(r+1)%size;
	}
	T pop(){
		T tmp=data[h];
		h=(h+1)%size;
		return tmp;
	}
}

public class Main_0 {
	static int[] head,dis,U;
	static boolean[] vis;
	static Edge[] G,V;
	static int n,m;
	static Queue<Integer> que;
	static void bfs(int st){
		Arrays.fill(dis, -1);
		que.clear();
		dis[st]=0;
		vis[st]=true;
		que.push(st);
		while(!que.empty()){
			int u=que.pop();
			for(int i=head[u];i>-1;i=G[i].next){
				int v=G[i].to;
				if (G[i].cap>0&&!vis[v]){
					dis[v]=dis[u]+1;
					que.push(v);
					vis[v]=true;
				}
			}
		}
	}
	static int dfs(int u,int d){
		if (u==n) return d;
		int res=0;
		for(int i=head[u];i>-1;i=G[i].next){
			int v=G[i].to;
			if (G[i].cap>0&&dis[v]==dis[u]+1){
				int tmp=dfs(v,Math.min(d,G[i].cap));
				G[i].cap-=tmp;
				G[G[i].rev].cap+=tmp;
				d-=tmp;
				res+=tmp;
			}
		}
		return res;
	}
	static int max_flow(int s,int t){
		int res=0;
		for(;;){
			Arrays.fill(vis, false);
			bfs(s);
			if (!vis[t]) return res;
			res+=dfs(s,Integer.MAX_VALUE);
		}
	}
	static void addedge(int from,int to,int cap){
		G[head[0]]=new Edge(to, cap, head[0]+1, head[from]);
		head[from]=head[0]++;
		G[head[0]]=new Edge(from,cap,head[0]-1,head[to]);
		head[to]=head[0]++;
	}
	static void spfa(int st){
		que.clear();
		que.push(st);
		dis[st]=0;
		vis[st]=true;
		while(!que.empty()){
			int u=que.pop();
			for(int i=head[u];i>-1;i=G[i].next){
				int v=G[i].to;
				if (!vis[v]){ 
					que.push(v);
					vis[v]=true;
				}
				dis[v]=Math.min(dis[v], dis[u]+G[i].cap);
			}
			vis[st]=false;
		}
	}
	static int spfa2(int s,int t){
		Arrays.fill(dis, Integer.MAX_VALUE);
		Arrays.fill(vis, false);
		que.push(s);
		dis[s]=0;
		vis[s]=true;
		while(!que.empty()){
			int u=que.pop();
			for(int i=head[u];i>-1;i=G[i].next){
				int v=G[i].to;
				if (!vis[v]){ 
					que.push(v);
					vis[v]=true;
				}
				dis[v]=Math.min(dis[v], dis[u]+1);
			}
			vis[s]=false;
		}
		return dis[t];
	}
	static void init(){
		G=new Edge[(m<<1)+1];
		head=new int[(m<<1)+1];
		dis=new int[n+1];
		vis=new boolean[n+1];
		Arrays.fill(head, -1);
		Arrays.fill(dis, Integer.MAX_VALUE);
		head[0]=1;
		//que=new Queue(Integer.class,(m<<1)+1);
		que=new Queue<Integer>((m<<1)+1);
	}
	public static void main(String[] args) throws IOException,InterruptedException {
		// TODO Auto-generated method stub
		StreamTokenizer cin = new StreamTokenizer(new BufferedInputStream(System.in)); 
		//InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(cin.nextToken()!=StreamTokenizer.TT_EOF){
			n=(int)cin.nval;
			cin.nextToken();
			m=(int)cin.nval;
			init();
			for(int i=1;i<=m;i++){
				cin.nextToken();
				int u=(int)cin.nval;
				cin.nextToken();
				int v=(int)cin.nval;
				cin.nextToken();
				int w=(int)cin.nval;
				addedge(u, v, w);
				//addedge(v, u, w);
			}
			spfa(1);
			//out.println(dis[n]);
			//max_flow(1, n);
			//init2();
			U=new int[m<<1];
			V=new Edge[m<<1];
			for(int u=1;u<=n;u++)
				for(int i=head[u];i>-1;i=G[i].next){
					int v=G[i].to;
					if (dis[u]+G[i].cap==dis[v]){
						U[++U[0]]=u;
						V[U[0]]=new Edge(G[i]);
					}
				}
			init();
			for(int i=1;i<=U[0];i++)
				addedge(U[i],V[i].to,1);
			out.println(max_flow(1, n)+" "+(m-spfa2(1,n)));
			for(int i=1;i<=n;i++)
				for(int j=head[i];j>-1;j=G[j].next)
					out.println(j+"\t:"+i+" "+G[j].to+" "+G[j].cap+" "+G[j].rev+" "+G[j].next);
			
		}
		out.flush();
		out.close();
	}

}

class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream){
           reader = new BufferedReader(new InputStreamReader(stream), 32768);
           tokenizer = null;
    }

    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
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

}*/
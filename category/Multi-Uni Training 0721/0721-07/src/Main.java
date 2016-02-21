/**
 * 2015年7月22日 上午10:07:44
 * PrjName:0721-07
 * @ Semprathlon
 */
import java.io.*;
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
	/*Queue(Class<T> c,int sz){
		size=sz;
		@SuppressWarnings("unchecked")
		final T[] tmp=(T[])Array.newInstance(c, sz);
		data=tmp;
	}*/
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

public class Main {
	static int[] head,dis,cnt;
	static boolean[] vis;
	static Edge[] G;
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
			int u=que.pop();vis[u]=false;
			for(int i=head[u];i>-1;i=G[i].next){
				int v=G[i].to;
				if (dis[u]+G[i].cap<dis[v]){
                    dis[v]=dis[u]+G[i].cap;
                    if (!vis[v]){ 
                    	que.push(v);
                    	vis[v]=true;
                    }
				}
			}
		}
	}
	static int spfa2(int s,int t){
		Arrays.fill(cnt, Integer.MAX_VALUE);
		Arrays.fill(vis, false);
		que.clear();
		que.push(s);
		cnt[s]=0;
        vis[s]=true;
        while(!que.empty()){
            int u=que.pop();vis[u]=false;
            for(int i=head[u];i>-1;i=G[i].next){
                int v=G[i].to;
                if (dis[u]+G[i].cap!=dis[v]) continue;
                if (!vis[v]){ 
                    que.push(v);
                    vis[v]=true;
                }
                cnt[v]=Math.min(cnt[v], cnt[u]+1);
            }
            
        }
        return cnt[t];
	}
	static void init(){
		G=new Edge[(m<<1)+1];
		head=new int[(m<<1)+1];
		dis=new int[n+1];
		cnt=new int[n+1];
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
			Dinic dinic=new Dinic(1, n);
			for(int u=1;u<=n;u++)
				for(int i=head[u];i>-1;i=G[i].next){
					int v=G[i].to;
					if (dis[u]+G[i].cap==dis[v])
						dinic.add(u, v, 1);
				}
			/*init();
			for(int i=1;i<=U[0];i++)
				addedge(U[i],V[i].to,1);*/
			out.println(dinic.maxflow()+" "+(m-spfa2(1,n)));
			/*for(int i=1;i<=n;i++)
				for(int j=head[i];j>-1;j=G[j].next)
					out.println(j+"\t:"+i+" "+G[j].to+" "+G[j].cap+" "+G[j].rev+" "+G[j].next);*/
			
		}
		out.flush();
		out.close();
	}

}

class Dinic{
    public Dinic(int sourse , int meet){
                 this.sourse = sourse ; 
                 this.meet = meet ;
                 Arrays.fill(g,  0) ;
                 id = 1 ;
    }

    static final  int  maxn = 2008 , maxm = 500000 ;
    static class Edge{
           int v , f ,next ;
           Edge(){}
           Edge(int _v , int _f , int _next){
                 this.v = _v ;
                 this.f = _f ;
                 this.next = _next ;
           }
    };
    int  sourse , meet ;
    int  id ;
    static Edge[] e = new Edge[maxm*2 + 10] ;
    static int[] g = new int[maxn + 10] ;

    public void  add(int u , int v , int f){
              e[++id] = new Edge(v , f ,g[u]) ;
              g[u] = id ;
              e[++id] = new Edge(u , 0 , g[v]) ;
              g[v] = id ;
    }

    Queue<Integer> que  = new Queue<Integer>(maxm);
    static boolean[] vis = new boolean[maxn + 10] ;
    static int[]  dist = new int[maxn + 10] ;

    void bfs(){
             Arrays.fill(dist, 0) ;
             while(! que.empty()) que.pop() ;
             que.push(sourse) ;
             vis[sourse] = true ;
             while(! que.empty()){
                  int u = que.pop() ;
                  for(int i = g[u] ; i > 0 ; i = e[i].next){
                       int v = e[i].v ;
                       if(e[i].f  > 0 && !vis[v]){
                             que.push(v) ;
                             dist[v] = dist[u] + 1 ;
                             vis[v] = true ;
                       }
                  }
             }
    }

    int  dfs(int u , int delta){
             if(u == meet) return delta ;
             int ans = 0 ;
             for(int i = g[u] ; i > 0  && delta > 0  ; i = e[i].next){
                   int  v = e[i].v ;
                   if(e[i].f  > 0 && dist[v] == dist[u] + 1){
                        int d = dfs(v , Math.min(delta , e[i].f)) ;
                        e[i].f -= d ;
                        e[i^1].f += d ;
                        delta -= d ;
                        ans += d ;
                   }
             }
             return ans ;
    }

    public int  maxflow(){
                 int ans = 0 ;
                 while(true){
                     Arrays.fill(vis, false) ;
                     bfs() ;
                     if(! vis[meet]) return ans  ;
                     ans += dfs(sourse , Integer.MAX_VALUE) ;
                 }
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

}
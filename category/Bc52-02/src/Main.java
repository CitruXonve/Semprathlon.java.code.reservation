/** Aug 22, 2015 7:35:54 PM
 * PrjName:Bc52-02
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
class Edge{
	int v,w,nx;
	Edge(int _u,int _v,int _w){
		v=_u;w=_v;nx=_w;
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
	void add(int u,int v,int w){
		edge[h[0]]=new Edge(v,w,h[u]);
		h[u]=h[0]++;
	}
}
public class Main {

	/**
	 * @param args
	 */
	static Graph G;
	static int[][] mt;
	static int[][] f;
	static int n,m;
	final static int inf=0xffffff;
	static int spfa(int ed,int sz){
		int[] f=new int[sz+1];
		boolean[] vis=new boolean[sz+1];
		Arrays.fill(f, inf);
		Queue<Integer> que=new LinkedList<Integer>();
		que.add(1);
		vis[1]=true;
		f[1]=0;
		while(!que.isEmpty()){
			int u=que.peek();
			for(int i=1;i<=n;i++)
				if(!vis[i]&&f[u]+mt[u][i]<f[i]){
					f[i]=f[u]+mt[u][i];
					vis[i]=true;
					que.add(i);
				}
			que.poll();
			vis[u]=false;
		}
		return f[ed];
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			G=new Graph(n<<2);
			mt=new int[n+1][n+1];
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++)
					mt[i][j]=inf;
			for(int i=1;i<=m;i++){
				int u=in.nextInt();
				int v=in.nextInt();
				int w=in.nextInt();
				
				mt[u][v]=Math.min(mt[u][v], w);
				mt[v][u]=Math.min(mt[v][u], w);
			}
			for(int i=1;i<=n;i++)
				for(int j=1;j<=n;j++)
					if (mt[i][j]<inf)
						G.add(i, j, mt[i][j]);
			f=new int[1<<n|1][n+1];
			for(int i=2;i<=(1<<n);i++)
				Arrays.fill(f[i], inf);
			int all=(1<<n)-1;
			for(int i=1;i<all;i++){
				for(int j=0;j<n;j++){
					if (((1<<j)&i)>0) continue;
					int tmp=inf,p=0;			
					for(int k=0;k<n;k++)
						if (((1<<k)&i)>0){
							tmp=Math.min(tmp, mt[k+1][j+1]);p=k+1;
						}
					if (p>0){
						f[(1<<j)|i][j+1]=Math.min(f[(1<<j)|i][j+1], f[i][p]+tmp);
						
					}
						
				}	
				
				for(int j=0;j<n;j++){
					if (((1<<j)&i)==0) continue;
					int tmp=inf,p=0;			
					for(int k=0;k<n;k++)
						if (((1<<k)&i)>0){
							tmp=Math.min(tmp, mt[k+1][j+1]);p=k+1;
						}
					if (p>0)
						f[i][j+1]=Math.min(f[i][j+1], f[i][p]+tmp);
				}	
				//out.println(i+" "+f[i]);
			}
			int ans=inf;
			for(int i=1;i<=n;i++){
				//out.println(i+" "+f[all][i]+" "+spfa(i, n));
				ans=Math.min(ans, f[all][i]+spfa(i, n));
			}
				
			//out.println(ans);
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
 
}
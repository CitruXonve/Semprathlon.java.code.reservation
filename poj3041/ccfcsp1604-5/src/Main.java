import java.io.*;
import java.util.*;

/**
 * 
 */

/**
 * @author semprathlon
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main().run();
	}

	
	final int inf=0x3fffffff;
	final int maxn=500;
	
	InputReader in;
	PrintWriter out;
	int n,m,p;
	String devs;
	Vector<Integer> users=new Vector<>();
	int[]  u,v,w,len,dis,pre,way;
	Vector<Integer>[] adj=(Vector<Integer>[])new Vector[maxn];
	boolean[] vis;
	
	void input(){
		n=in.nextInt();
		m=in.nextInt();
		p=in.nextInt();
		devs=in.next();
		
		users.clear();
		
		
		u=new int[m+2];
		v=new int[m+2];
		w=new int[m+2];
		
		for(int i=0;i<n;i++)
			if (devs.charAt(i)=='1')
				users.add(i+1);
		
		for(int i=0;i<=n;i++)
			adj[i].clear();
		
		for(int i=1;i<=m;i++){
			u[i]=in.nextInt();
			v[i]=in.nextInt();
			w[i]=in.nextInt();
			adj[u[i]].add(i);
			adj[v[i]].add(-i);
		}
	}
	
	int prim(){
		int res=0;
		dis=new int[n+1];
		len=new int[n+1];
		way=new int[n+1];
		pre=new int[n+1];
		Arrays.fill(dis, inf);
		
		vis=new boolean[n+1];
		
		dis[users.get(0)]=0;
		for(int i=0;i<users.size()-1;i++){
			
			spfa(users.get(i));
			for(int j=0;j<=n;j++)
//				dis[j]=Math.min(dis[j], len[j]);
				if (len[j]<dis[j]){
					dis[j]=len[j];
					way[j]=pre[j];
				}
			
//			out.print(users.get(i)+":\t");
//			for(int j=0;j<len.length;j++)
//				out.print(len[j]+",");out.println();
			
			int next=0;
			for(int j=0;j<users.size();j++){
				if (i==j) continue;
				if (dis[users.get(j)]>0&&dis[users.get(j)]<dis[next])
					next=users.get(j);
			}
			
			if (next>0){
//				out.println(next+":"+dis[next]);
				res+=dis[next];
				dis[next]=0;
			}
		}
		return res;
	}
	
	void spfa(int src){
		Arrays.fill(len, inf);
		Arrays.fill(pre, 0);
		Queue<Integer> que=new LinkedList<>();
		Arrays.fill(vis, false);
		vis[src]=true;
		len[src]=0;
		pre[src]=src;
		que.add(src);
		while(!que.isEmpty()){
			int cur=que.poll();
			for(int i=0;i<adj[cur].size();i++){
				int e=adj[cur].get(i);
//				try {
					int next=(e>0?v[e]:u[-e]);
					if (len[cur]+w[Math.abs(e)]<len[next]){
						len[next]=len[cur]+w[Math.abs(e)];
						pre[next]=cur;
						if (!vis[next]){
							vis[next]=true;
							que.add(next);
						}
					}
//				} catch (Exception e2) {
//					// TODO: handle exception
//					System.err.println(e2.getMessage());
//					System.out.println(m+","+v.length);
//				}
				
				
			}
			vis[cur]=false;
		}
	}
	
	public void run(){
		in=new InputReader(System.in);
		out=new PrintWriter(System.out);
		for(int i=0;i<maxn;i++)
				adj[i]=new Vector<Integer>();
		
		int T=in.nextInt();
		while(T-->0){
			
			
			input();
			
			out.println(prim());
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
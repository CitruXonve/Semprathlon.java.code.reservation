/**
 * May 5, 2016 7:52:44 PM
 * PrjName: 0505-I
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] c=new int[3][10];
	static int x,y;
	
	final static  int inf = 0x3fffffff;
	final static int maxn=100010;
	static int src, sink;
	static Queue<Integer> que;
	static boolean[] inQue=new boolean[maxn];
	static int[] dis=new int[maxn];
	static int[] cnt=new int[maxn];

	static void init(int _u, int _v) {
		src = _u; sink = _v;
	}

	static void solve() {
		que = new LinkedList<Integer>();
		que.add(src);
		Arrays.fill(dis, inf);
		dis[src] = 0;
		Arrays.fill(cnt, inf);
		cnt[src]=0;
		Arrays.fill(inQue, false);
		inQue[src] = true;
		while (!que.isEmpty()) {
			int u = que.poll();
			for(int i=0;i<10;i++){
				int v=u*10+i;
				if (v>sink) break;
				if (dis[u]+c[0][i]<dis[v]){
					dis[v]=dis[u]+c[0][i];
					cnt[v]=cnt[u]+1;
					if (!inQue[v]){
						inQue[v]=true;
						que.add(v);
					}
				}
				else if (dis[u]+c[0][i]==dis[v]){
					cnt[v]=Math.min(cnt[v], cnt[u]+1);
				}
			}
			
			for(int i=0;i<10;i++){
				int v=u+i;
				if (v>sink) break;
				if (dis[u]+c[1][i]<dis[v]){
					dis[v]=dis[u]+c[1][i];
					cnt[v]=cnt[u]+1;
					if (!inQue[v]){
						inQue[v]=true;
						que.add(v);
					}
				}
				else if (dis[u]+c[1][i]==dis[v]){
					cnt[v]=Math.min(cnt[v], cnt[u]+1);
				}
			}
			
			for(int i=0;i<10;i++){
				int v=u*i;
				if (v>sink) break;
				if (dis[u]+c[2][i]<dis[v]){
					dis[v]=dis[u]+c[2][i];
					cnt[v]=cnt[u]+1;
					if (!inQue[v]){
						inQue[v]=true;
						que.add(v);
					}
				}
				else if (dis[u]+c[2][i]==dis[v]){
					cnt[v]=Math.min(cnt[v], cnt[u]+1);
				}
			}
			
			/*for (int i = g.h[u]; i != -1; i = g.e[i].nx) {
				if (g.e[i].f > 0 && dis[u] + g.e[i].w < dis[g.e[i].v]) {
					dis[g.e[i].v] = dis[u] + g.e[i].w;
					prev[g.e[i].v] = u;
					pree[g.e[i].v] = i;
					if (!inQue[g.e[i].v]) {
						inQue[g.e[i].v] = true;
						que.add(g.e[i].v);
					}
				}
			}*/
			inQue[u] = false;
		}
	}
	
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int cas=0;
		while(in.hasNext()){
			x=in.nextInt();
			y=in.nextInt();
			for(int i=0;i<3;i++)
				for(int j=0;j<10;j++)
					c[i][j]=in.nextInt();
			init(x,y);
			solve();
			out.println("Case "+(++cas)+": "+dis[y]+" "+cnt[y]);
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

/**
 * 2015年7月28日 下午12:06:51
 * PrjName:0728-11
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] head,v;
	static int n,m;
	static Edge[] E;
	static void add(int u,int v){
		E[head[0]]=new Edge(v,head[u]);
		head[u]=head[0]++;
	}
	static void init(){
		head=new int[m+1];
		Arrays.fill(head, -1);
		head[0]=1;
		
		E=new Edge[(m+1)<<1];
		v=new int[n+1];
	}
	static void dfs(int u){
		for(int i=head[u];i>-1;i=E[i].next){
			int v=E[i].t;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StreamTokenizer cin=new StreamTokenizer(new BufferedInputStream(System.in));
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(cin.nextToken()!=StreamTokenizer.TT_EOF){
			n=(int)cin.nval;
			cin.nextToken();
			m=(int)cin.nval;
			init();
			int h=Integer.MAX_VALUE;
			for(int i=1;i<=m;i++){
				cin.nextToken();
				int u=(int)cin.nval;
				cin.nextToken();
				int v=(int)cin.nval;
				add(u,v);
				h=Math.min(h, u);
			}
			
		}
	}

}

class Edge{
	int t,next;
	Edge(int v,int to){
		this.t=v;
		this.next=to;
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
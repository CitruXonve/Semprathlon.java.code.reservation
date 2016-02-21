/**
 * 2015年7月30日 下午12:05:27
 * PrjName:0730-01
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=100010;
	static boolean[] f,v;
	static int[] g;
	static void dfs(int k,int st,int sum){
		if (k==st){
			f[sum]=true;
			return;
		}
		for(int i=(k==0?1:0);i<=9;i++)
			if (!v[i]){
				v[i]=true;
				dfs(k+1,st,sum*10+i);
				v[i]=false;
			}
	}
	static void init(){
		f=new boolean[maxn];
		for(int k=1;k<=5;k++){
			v=new boolean[10];
			dfs(0,k,0);
		}
		f[0]=true;f[1]=true;
		g=new int[maxn];
		g[0]=1;
		for(int i=1;i<maxn;i++) 
			if (f[i])
				g[i]=g[i-1]+1;
			else
				g[i]=g[i-1];
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		/*for(int i=0;i<maxn;i++) 
			if (f[i])
				out.print(i+" ");*/
		int T=in.nextInt();
		while(T-->0){
			int a=in.nextInt();
			int b=in.nextInt();
			out.println(g[b]-g[a-1]);
		}
		out.flush();
		out.close();
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

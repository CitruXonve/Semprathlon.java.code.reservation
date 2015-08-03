/**
 * 2015年7月23日 下午12:28:05
 * PrjName:0723-06
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
class Pair<A,B>{
	final A f;
	final B s;
	Pair(A first,B second){
		f=first;s=second;
	}
	Pair<A,B> m_p(A first,B second){
		return new Pair(first,second);
	}
}

public class Main {
	static int[] deg,sum;
	static int M,n,m;
	//static boolean[] vis;
	static Vector<Pair<Integer,Integer>> edge;
	static HashSet<Integer> mp;
	static PrintWriter out=new PrintWriter(System.out);
	static int hashCode(int[] sum){
		int res=0;
		for(int k:sum){
			res*=19;
			res+=k+9;
		}
		return res;
	}
	static boolean check0(int[] sum){
		for(int i=1;i<=n;i++)
			if ((deg[i]&1)>0)
				return false;
		return true;
	}
	static boolean check(int[] sum){
		for(int i=1;i<=n;i++)
			if (sum[i]!=(deg[i]>>1))
				return false;
		return true;
	}
	static void prt(int[] sum){
		for(int i=1;i<=n;i++)
			out.print(sum[i]+" ");
		out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			m=in.nextInt();
			M=(m<<1)+1;
			deg=new int[n+2];
			//vis=new boolean[M];
			edge=new Vector<Pair<Integer,Integer>>();
			for(int i=1;i<=m;i++){
				int u=in.nextInt();
				int v=in.nextInt();
				edge.add(new Pair<Integer,Integer>(u,v));
				deg[u]++;
				deg[v]++;
			}
			if (m==0){
				out.println(1);
				continue;
			}
			if (!check0(deg)){
				out.println(0);
				continue;
			}
			//prt(deg);
			//vis=new boolean[M];
			sum=new int[n+1];
			int ans=0;
			mp=new HashSet<Integer>();
			int mm=m>>1;
			for(int k=1;k<(1<<mm)-1;k++){
				Arrays.fill(sum, 0);
				//Arrays.fill(vis, false);
				for(int i=0;i<mm;i++)
					if (((1<<i)&k)>0){
						//vis[i]=true;
						int u=edge.get(i).f;
						int v=edge.get(i).s;
						sum[u]++;
						sum[v]++;
					}
				
				if (check(sum)){
					//out.print(k+":");prt(sum);
					ans++;
				}
				else{
					mp.add(hashCode(sum));
					out.print(k+":");prt(sum);
				}
			}
			out.println(ans);
			for(int k=1;k<(1<<(m-mm))-1;k++){
				Arrays.fill(sum, 0);
				for(int i=1;i<=n;i++)
					sum[i]=deg[i]>>1;
				for(int i=mm;i<m;i++)
					if (((1<<(i-mm))&k)>0){
						int u=edge.get(i).f;
						int v=edge.get(i).s;
						sum[u]--;
						sum[v]--;
					}
				if (mp.contains(hashCode(sum))){
					out.print(k+":");prt(sum);
					ans++;
				}
			}
			//out.println("ans="+ans);
			out.println(ans);
			
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
/**
 * Mar 20, 2016 7:07:16 PM
 * PrjName: 0320-01
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=5,maxs=12;
	static int[] f=new int[maxn];
	static int[] g=new int[maxn];
	static int ans=0;
	static HashSet<Integer> st=new HashSet<Integer>();
	static PrintWriter out=new PrintWriter(System.out);
	static boolean[] vis=new boolean[maxs];
	static boolean check(int[] g){
		int tmp=0;
		for(int i=0;i<maxn;i++)
			tmp=tmp*13+g[i];
		if (st.contains(tmp)) return false;
		st.add(tmp);
		return true;
	}
	static boolean check0(int x){
		int a=f[x];
		return a>0&&a%4>0&&vis[a-1]||a>3&&vis[a-4]||a<maxs-1&&a%4<3&&vis[a+1]||a<maxs-4&&vis[a+4];
	}
	static void dfs(int step){
		if (step==maxn){
			g=f.clone();
			Arrays.sort(g);
			if (!check(g)) return;
			for(int i=0;i<maxn;i++)
				out.print(g[i]+" ");
			out.println();
			ans++;
			return;
		}
		for(int i=0;i<maxs;i++){
			if (!vis[i]){
				vis[i]=true;
				f[step]=i;
				if (step==0||step>0&&check0(step)) dfs(step+1);
				vis[i]=false;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		dfs(0);
		out.println(ans);
		out.flush();
		out.close();
	}

}
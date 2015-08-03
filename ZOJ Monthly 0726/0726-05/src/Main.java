
/**
 * 2015年7月26日 下午1:04:40
 * PrjName:0726-05
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a,f;
	static int[][] E;
	static int maxstep=10000;
	static int n,m;
	final static int pri=17;
	static int hashCode(int[] a){
		int s1=0,s2=0;
		int len=a.length;
		/*for(int i=0;i<len;i++){
			s1*=19;
			s1+=a[i]%19;
		}*/
		for(int i=0;i<len;i++){
			s2*=pri;
			s2+=Math.abs(a[i])%pri;
		}
		return s2;
	}
	static int spfa(int[] st){
		HashMap<Integer, Integer> mp=new HashMap<Integer, Integer>();
		BitSet vis=new BitSet();
		Queue<int[]> que=new LinkedList<int[]>();
		que.add(st);
		mp.put(hashCode(st), 0);
		vis.set(hashCode(st));
		while(!que.isEmpty()){
			int[] u=que.poll();
			int p=mp.get(hashCode(u));
			if (p>=maxstep) return -1;
			for(int i=1;i<=m;i++){
				int[] v=u.clone();
				v[E[0][i]]--;
				v[E[1][i]]++;
				int hv=hashCode(v);
				if (hv==0) return p+1;
				if (que.contains(v)){
					int q=mp.get(hv);
					if (p+1<q){
						//vis.replace(hv, p+1);
						mp.remove(hv);
						mp.put(hv, p+1);
						if (!vis.get(hv)){
							que.add(v);
							vis.set(hv);
						}
					}
				}
				else{
					mp.put(hv, p+1);
					if (!vis.get(hv)){
						que.add(v);
						vis.set(hv);
					}
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			n=(int)in.nval;
			in.nextToken();
			m=(int)in.nval;
			a=new int[n+1];
			for(int i=1;i<=n;i++){
				in.nextToken();
				int x=(int)in.nval;
				in.nextToken();
				int y=(int)in.nval;
				a[i]=y-x;
			}
			E=new int[2][m+1];
			for(int i=1;i<=m;i++){
				in.nextToken();
				int u=(int)in.nval;
				in.nextToken();
				int v=(int)in.nval;
				E[0][i]=u;E[1][i]=v;
				//a[u]--;a[v]++;out.println(hashCode(a));
			}
			int res=spfa(a);
			out.println(res);
			out.flush();
		}
		out.close();
	}

}

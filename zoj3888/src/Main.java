/**
 * 2015年7月30日 上午10:02:07
 * PrjName:zoj3888
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] f;
	final static int maxn=50010;
	static Vector<Vector<Integer>> a=new Vector<Vector<Integer>>();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		for(int i=1;i<maxn;i++)
			a.add(new Vector<Integer>());
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			int n=(int)in.nval;
			in.nextToken();
			int m=(int)in.nval;
			in.nextToken();
			int q=(int)in.nval;
			for(int i=1;i<=n;i++)
				a.get(i-1).clear();
			for(int i=1;i<=m;i++){
				in.nextToken();
				int u=(int)in.nval;
				in.nextToken();
				int v=(int)in.nval;
				a.get(u-1).add(v);
			}
			f=new int[n+2][2];
			f[n+1][0]=f[n+1][1]=n+1;
			for(int i=n;i>0;i--){
				f[i][0]=Math.min(i, f[i+1][0]);
				f[i][1]=Math.min(i, f[i+1][1]);
				Vector<Integer> v=a.get(i-1);
				for(int j=0;j<v.size();j++)
					if (v.get(j)<=f[i][0]){
						f[i][1]=f[i][0];f[i][0]=v.get(j);
					}
					else
						f[i][1]=Math.min(f[i][1], v.get(j));
			}
			//for(int i=1;i<=n;i++) out.print(f[i][0]+" ");out.println();
			//for(int i=1;i<=n;i++) out.print(f[i][1]+" ");out.println();
			while(q-->0){
				in.nextToken();
				int p=(int)in.nval;
				out.println(p-f[p][1]);
			}
		}
		out.flush();
		out.close();
	}

}
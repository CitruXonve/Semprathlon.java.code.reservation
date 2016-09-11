/**
 * May 1, 2016 1:48:45 PM
 * PrjName: 0501-D
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=550;
	static int[][] a=new int[maxn][maxn];
	static int[] sum;
	static Vector<Integer> rs=new Vector<Integer>();
	static Vector<Integer> cs=new Vector<Integer>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n,m,cas=0;
		while(in.hasNext()){
			n=in.nextInt();
			m=in.nextInt();
			sum=new int[n+m];
			for(int i=0;i<n;i++){
				for(int j=0;j<m;j++){
					a[i][j]=in.nextInt();
					sum[i]+=a[i][j];
					sum[n+j]+=a[i][j];
				}
			}
			
			rs.clear();
			cs.clear();
			
			int r0=0,c0=0;
			for(int i=0;i<n;i++)
				for(int j=0;j<m;j++)
					if (sum[i]+sum[n+j]-a[i][j]>sum[r0]+sum[n+c0]-a[r0][c0]){
						r0=i;c0=j;
						rs.clear();
						cs.clear();
						rs.add(i);
						cs.add(j);
					}
					else if (sum[i]+sum[n+j]-a[i][j]==sum[r0]+sum[n+c0]-a[r0][c0]){
						rs.add(i);
						cs.add(j);
					}
			
			int maxr=0,maxc=0;
			for(int i=n-1;i>=0;i--)
				if (sum[i]>=sum[maxr])
					maxr=i;
			for(int j=m-1;j>=0;j--)
				if (sum[n+j]>=sum[n+maxc])
					maxc=j;
			
			boolean res=false;
			for(int i=0;i<rs.size();i++)
				if (rs.get(i)==maxr&&cs.get(i)==maxc){
					res=true;break;
				}
			
//			out.println(rs.size()+","+maxr+","+maxc);
			
			if (res)
				out.println("Case "+(++cas)+": Weak");
			else 
				out.println("Case "+(++cas)+": Strong");
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
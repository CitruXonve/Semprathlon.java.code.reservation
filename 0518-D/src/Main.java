/**
 * May 18, 2016 6:54:39 PM
 * PrjName: 0518-D
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=10010;
	final static int maxm=100010;
	static int[] f=new int[maxn];
	static int[] v=new int[maxm];
	static int[] u=new int[maxm];
	static int[] ans=new int[maxm];
	static int getf(int x){
		if (f[x]==x) return f[x];
		f[x]=getf(f[x]);
		return f[x];
	}
	static boolean union(int x,int y){
		int a=getf(x);
		int b=getf(y);
		if (a!=b){
			f[a]=b;
			return true;
		}
		return false;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		int m=in.nextInt();
		for(int i=0;i<=n;i++)
			f[i]=i;
		for(int i=1;i<=m;i++){
			u[i]=in.nextInt();
			v[i]=in.nextInt();
		}
		int cnt=n;
		for(int i=m;i>=1;i--){
			ans[i]=cnt;
			if (union(u[i],v[i]))
				cnt--;
		}
		for(int i=1;i<=m;i++)
			out.println(ans[i]);
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
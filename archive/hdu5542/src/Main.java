/**
 * Dec 11, 2015 9:40:37 PM
 * PrjName: hdu5542
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=1010;
    static int[] a=new int[maxn];
    static int[] mp=new int[maxn];
    static Integer[] b=new Integer[maxn];
    static int[][] f;
    static BIT[] tr=new BIT[maxn];
    final static int mod=1000000007;
    static class Comp implements Comparator<Integer>{
        public int compare(Integer n1,Integer n2){
            return Integer.compare(a[n1], a[n2]);
        }
    }
    public static void main(String[] args) throws IOException{
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        for(int i=0;i<maxn;i++)
            tr[i]=new BIT(maxn);

        int T=in.nextInt(),cas=0;
        while(T-->0){
            int n=in.nextInt();
            int m=in.nextInt();
            f=new int[n][n];
            for(int i=0;i<n;i++){
                a[i]=in.nextInt();
                b[i]=i;
                tr[i].clear();
            }
            Arrays.sort(b, 0, n, new Comp());
            int cnt=0;
            for(int i=0;i<n;i++)
                mp[b[i]]=++cnt;
            for(int i=0;i<n;i++){
                f[i][0]=0;
                f[i][1]=1;
                tr[1].add(mp[i], 1);
                for(int j=2;j<=Math.min(i+1, m);j++){
                    f[i][j]+=tr[j-1].sum(mp[i]-1);
                    if (f[i][j]>=mod) f[i][j]-=mod;
                    tr[j].add(mp[i], f[i][j]);
                }
                
            }
            int ans=0;
            for(int i=m-1;i<n;i++){
                ans+=f[i][m];
                if (ans>=mod) ans-=mod;
            }
            out.println("Case #"+(++cas)+": "+ans);
        }
        out.flush();
        out.close();
    }

}
class BIT {
    int[] data;
    int sz;
    int mod=1000000007;
    
    BIT() {
    }

    BIT(int _sz) {
        sz = _sz;
        data = new int[sz + 1];
    }

    void clear(){
    	Arrays.fill(data, 0);
    }
    
    int lowbit(int x) {
        return x & (-x);
    }

    void add(int p, int v) {
        while (p <= sz) {
            data[p] += v;
            if (data[p]>=mod) data[p]-=mod;
            p += lowbit(p);
        }
    }

    int sum(int p) {
        int res = 0;
        while (p > 0) {
            res += data[p];
            if (res>=mod) res-=mod;
            p -= lowbit(p);
        }
        return res;
    }

}
class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
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


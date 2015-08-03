/**
 * 2015年7月27日 下午1:27:47
 * PrjName:hdu5239
 * @ Semprathlon
 */
import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
public class Main {
    final static long mod=9223372034707292160L;
    final static int maxn=100010;
    static SegTree tr;
    static long s;
    static long[] a=new long[maxn];
    static long Mod(long n){
    	/*if (n>=mod||n<0L){
    		n+=mod;n+=mod;
    	}*/
    	if (n<0L) n+=mod;
    	return n;
    }
    public static long Mul(long n,long m){
        long res=0L;
        while(m>0){
            if ((m&1)>0) res=Mod(res-mod+n);
            n=Mod(n-mod+n);
            m>>=1;
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt(),cas=0;
        
        while(T-->0){
            int n=in.nextInt();
            int m=in.nextInt();
            for(int i=1;i<=n;i++)
                a[i]=in.nextLong();
            tr=new SegTree(1, n, a);
            s=0;
            out.println("Case #"+(++cas)+":");
            for(int i=1;i<=m;i++){
                int x=in.nextInt();
                int y=in.nextInt();
                tr.update(x,y);
                //s=Mod(s);
                out.println(s);
            }
        }
        out.flush();
        out.close();
    }

}

class SegTree{
    SegTree lch,rch;
    int l,r,mid;
    long v;
    boolean is;
    SegTree(int x,int y){
        l=x;r=y;mid=(x+y)>>1;
        v=0;
        is=false;
        if (x+1<y){
            lch=new SegTree(x, mid);
            rch=new SegTree(mid+1, y);
        }
    }
    SegTree(int x,int y,long[] a){
        l=x;r=y;mid=(x+y)>>1;
        is=false;
        if (x<y){
            lch=new SegTree(x, mid, a);
            rch=new SegTree(mid+1, y, a);
            v=0;
        }
        else
            v=a[mid];
    }
    void update(int x,int y){
        if (x<=l&&r<=y&&is){
        	Main.s=Main.Mod(Main.s-Main.mod+v);
        	return;
        }
        if (l==r){
        	Main.s=Main.Mod(Main.s-Main.mod+v);
            long tmp=v;
            v=Main.Mul(tmp,tmp);
            if (tmp==v)
                is=true;
            return;
        }
        if (x<=mid) lch.update(x,Math.min(y, mid));
        if (mid<y) rch.update(Math.max(mid+1, x),y);
        if (lch.is&&rch.is){
            is=true;v=lch.v+rch.v; 
        }
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
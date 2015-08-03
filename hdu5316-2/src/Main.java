/**
 * 2015��7��29�� ����10:35:39
 * PrjName:hdu5316-2
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;

public class Main {
	final static long inf=0x4000000000000000L;
    final static int maxn=100010;
    static int[] a=new int[maxn+1];
    static SegTree tr=new SegTree(1, maxn);
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt();
        while(T-->0){
            int n=in.nextInt();
            int m=in.nextInt();
            for(int i=1;i<=n;i++) 
                a[i]=in.nextInt();
            tr.reset(1,1, n, a);
            for(int i=1;i<=m;i++){
                int k=in.nextInt();
                int x=in.nextInt();   
                if (k==0){
                    //out.println(tr.query(x, y));
                    int y=in.nextInt();
                    long ans=-inf;
                    long[] res=tr.query(1,x, y);
                    for(int j=0;j<4;j++)
                        ans=Math.max(ans, res[j]);
                    out.println(ans);
                }
                else{
                    long y=in.nextLong();
                    tr.update(1,x, y);
                }
                    
            }
        }
        out.flush();
        out.close();
    }

}

class SegTree{
	final static long inf=0x4000000000000000L;
    int[] l,r,mid;
    long[][] s;
    SegTree(int x,int y){
        l=new int[(y-x+2)<<2];
        r=new int[(y-x+2)<<2];
        mid=new int[(y-x+2)<<2];
        s=new long[(y-x+2)<<2][4];
        build(1,x,y);
    }
    void build(int k,int x,int y){
        /*l=x;r=y;*/mid[k]=(x+y)>>1;
        if (x<y){
            build(k<<1,x, mid[k]);
            build(k<<1|1,mid[k]+1, y);
        }
        //s0=s1=s2=s3=-inf;
    }
    void reset(int k,int x,int y,int[] a){
        l[k]=x;r[k]=y;mid[k]=(x+y)>>1;
        if (x<y){
            reset(k<<1,x, mid[k], a);
            reset(k<<1|1,mid[k]+1, y, a);
            up(s[k],s[k<<1],s[k<<1|1]);
        }
        else{
            if ((mid[k]&1)>0){//odd
                s[k][0]=a[mid[k]];
                s[k][1]=s[k][2]=s[k][3]=-inf;
            }
            else{//even
                s[k][3]=a[mid[k]];
                s[k][0]=s[k][1]=s[k][2]=-inf;
            }
        }
            
    }
    void up(long[] k,long[] l,long[] r){
    	k[0]=k[1]=k[2]=k[3]=-inf;
        k[0]=Math.max(k[0], l[0]+r[2]);//odd-odd
        k[0]=Math.max(k[0], l[1]+r[0]);
        
        k[1]=Math.max(k[1], l[1]+r[1]);//odd-even
        k[1]=Math.max(k[1], l[0]+r[3]);
        
        k[2]=Math.max(k[2], l[2]+r[2]);//even-odd
        k[2]=Math.max(k[2], l[3]+r[0]);
        
        k[3]=Math.max(k[3], l[3]+r[1]);//even-even
        k[3]=Math.max(k[3], l[2]+r[3]);
        
        k[0]=Math.max(k[0], l[0]);
        k[0]=Math.max(k[0], r[0]);
        
        k[1]=Math.max(k[1], l[1]);
        k[1]=Math.max(k[1], r[1]);
        
        k[2]=Math.max(k[2], l[2]);
        k[2]=Math.max(k[2], r[2]);
        
        k[3]=Math.max(k[3], l[3]);
        k[3]=Math.max(k[3], r[3]);
    }
    long[] query(int k,int x,int y){
        long[] res=new long[4];
        if (x==l[k]&&r[k]==y){
            res=s[k].clone();
        }
        else{
            long[] L,R;
            if (x<=mid[k]) L=query(k<<1,x,Math.min(y, mid[k])).clone();
            else L=new long[]{-inf,-inf,-inf,-inf};
            if (mid[k]<y) R=query(k<<1|1,Math.max(mid[k]+1, x),y).clone();
            else R=new long[]{-inf,-inf,-inf,-inf};
            up(res,L,R);
        }
        return res;
    }
    void update(int k,int x,long d){
        if (l[k]==r[k]){
            if ((mid[k]&1)>0){//odd
                s[k][0]=d;
                s[k][1]=s[k][2]=s[k][3]=-inf;
            }
            else{//even
                s[k][3]=d;
                s[k][0]=s[k][1]=s[k][2]=-inf;
            }
            return;
        }
        if (x<=mid[k]) update(k<<1,x,d);
        if (mid[k]<x) update(k<<1|1,x,d);
        up(s[k],s[k<<1],s[k<<1|1]);
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
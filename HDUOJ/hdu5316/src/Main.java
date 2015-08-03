/**
 * 2015年7月28日 下午7:52:05
 * PrjName:hdu5316
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static long[] a;
	static SegTree tr;
	final static int maxn=100000;
	final static long inf=0x4000000000000000L;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		tr=new SegTree(1, maxn);
		a=new long[maxn+1];
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			for(int i=1;i<=n;i++) 
				a[i]=in.nextLong();
			tr.reset(1, n, a);
			for(int i=1;i<=m;i++){
				int k=in.nextInt();
				int x=in.nextInt();				
				if (k==0){
					//out.println(tr.query(x, y));
					int y=in.nextInt();
					long ans=-inf;
					long[] res=tr.query(x, y);
					for(int j=0;j<4;j++)
						ans=Math.max(ans, res[j]);
					out.println(ans);
				}
				else{
					long y=in.nextLong();
					tr.update(x, y);
				}
					
			}
		}
		out.flush();
		out.close();
	}

}

class SegTree{
    SegTree lch,rch;
    int l,r,mid;
    long s0,s1,s2,s3,v0,v1;
    SegTree(int x,int y){
        /*l=x;r=y;*/mid=(x+y)>>1;
        if (x+1<y){
            lch=new SegTree(x, mid);
            rch=new SegTree(mid+1, y);
        }
        //s0=s1=s2=s3=-inf;
    }
    void reset(int x,int y,long[] a){
        l=x;r=y;mid=(x+y)>>1;
        if (x+1<y){
            lch.reset(x, mid, a);
            rch.reset(mid+1, y, a);
            this.up();
            v0=v1=0;
        }
        else if (x+1==y&&(x&1)>0){//x:odd y::even        	
        	s1=s2=-Main.inf;
        	v0=s0=a[l];v1=s3=a[r];
        	s1=Math.max(s1, s0);
        	s1=Math.max(s1, s3);
        	s1=Math.max(s1, s0+s3);
        }
        else if (x+1==y&&(y&1)>0){//x:even y::odd        	
        	s1=s2=-Main.inf;
        	v0=s3=a[l];v1=s0=a[r];
        	s2=Math.max(s2, s0);
        	s2=Math.max(s2, s3);
        	s2=Math.max(s2, s0+s3);
        }
        else{
        	if ((mid&1)>0){//odd
        		v0=s0=a[mid];v1=0;
        		s1=s2=s3=-Main.inf;
        	}
        	else{//even
        		v0=s3=a[mid];v1=0;
        		s0=s1=s2=-Main.inf;
        	}
        }
            
    }
    void up(){
    	this.s0=this.s1=this.s2=this.s3=-Main.inf;
    	this.s0=Math.max(this.s0, lch.s0+rch.s2);//odd-odd
    	this.s0=Math.max(this.s0, lch.s1+rch.s0);
    	
    	this.s1=Math.max(this.s1, lch.s1+rch.s1);//odd-even
    	this.s1=Math.max(this.s1, lch.s0+rch.s3);
    	
    	this.s2=Math.max(this.s2, lch.s2+rch.s2);//even-odd
    	this.s2=Math.max(this.s2, lch.s3+rch.s0);
    	
    	this.s3=Math.max(this.s3, lch.s3+rch.s1);//even-even
    	this.s3=Math.max(this.s3, lch.s2+rch.s3);
    	
    	this.s0=Math.max(this.s0, lch.s0);
    	this.s0=Math.max(this.s0, rch.s0);
    	
    	this.s1=Math.max(this.s1, lch.s1);
    	this.s1=Math.max(this.s1, rch.s1);
    	
    	this.s2=Math.max(this.s2, lch.s2);
    	this.s2=Math.max(this.s2, rch.s2);
    	
    	this.s3=Math.max(this.s3, lch.s3);
    	this.s3=Math.max(this.s3, rch.s3);
    }
    long[] query(int x,int y){
    	long[] res=new long[4];
    	if (x==l&&r==y){
    		res[0]=s0;
    		res[1]=s1;
    		res[2]=s2;
    		res[3]=s3;
    	}
    	else{
    		res[0]=res[1]=res[2]=res[3]=-Main.inf;
    		long[] L,R;
    		if (x<=mid) L=query(x,Math.min(y, mid)).clone();
    		else L=new long[]{-Main.inf,-Main.inf,-Main.inf,-Main.inf};
    		if (mid<y) R=query(Math.max(mid+1, x),y).clone();
    		else R=new long[]{-Main.inf,-Main.inf,-Main.inf,-Main.inf};
    		
    		res[0]=Math.max(res[0], L[0]+R[2]);//odd-odd
        	res[0]=Math.max(res[0], L[1]+R[0]);
        	
        	res[1]=Math.max(res[1], L[1]+R[1]);//odd-even
        	res[1]=Math.max(res[1], L[0]+R[3]);
        	
        	res[2]=Math.max(res[2], L[2]+R[2]);//even-odd
        	res[2]=Math.max(res[2], L[3]+R[0]);
        	
        	res[3]=Math.max(res[3], L[3]+R[1]);//even-even
        	res[3]=Math.max(res[3], L[2]+R[3]);
        	
        	res[0]=Math.max(res[0], L[0]);
        	res[0]=Math.max(res[0], R[0]);
        	
        	res[1]=Math.max(res[1], L[1]);
        	res[1]=Math.max(res[1], R[1]);
        	
        	res[2]=Math.max(res[2], L[2]);
        	res[2]=Math.max(res[2], R[2]);
        	
        	res[3]=Math.max(res[3], L[3]);
        	res[3]=Math.max(res[3], R[3]);
    	}
    	return res;
    }
    void update(int x,long d){
        if (l+1==r){
        	if (x==l) v0=d;
        	if (x==r) v1=d;
        	if ((x&1)>0){//x:odd y::even        	
            	s1=s2=-Main.inf;
            	s0=v0;s3=v1;
            	s1=Math.max(s1, s0);
            	s1=Math.max(s1, s3);
            	s1=Math.max(s1, s0+s3);
            }
            else{//x:even y::odd        	
            	s1=s2=-Main.inf;
            	s3=v0;s0=v1;
            	s2=Math.max(s2, s0);
            	s2=Math.max(s2, s3);
            	s2=Math.max(s2, s0+s3);
            };
            return;
        }
        if (l==r){
        	v0=d;v1=0;
        	if ((mid&1)>0){//odd
        		s0=d;
        		s1=s2=s3=-Main.inf;
        	}
        	else{//even
        		s3=d;
        		s0=s1=s2=-Main.inf;
        	}
        	return;
        }
        if (x<=mid) lch.update(x,d);
        if (mid<x) rch.update(x,d);
        this.up();
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
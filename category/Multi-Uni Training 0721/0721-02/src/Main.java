/**
 * 2015年7月21日 下午1:40:29
 * PrjName:0721-02
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int maxm=20,maxn=100010;
	static int[] a;
	static int[][] maxsum,minsum;
	static int n,k;
	
	static void RMQ(){ //预处理->O(nlogn){
	    for(int i = 1; i != maxm; ++i)
	        for(int j = 1; j <= n; ++j)
	            if(j + (1 << i) - 1 <= n){
	                maxsum[i][j] = Math.max(maxsum[i - 1][j], maxsum[i - 1][j + (1 << i >> 1)]);
	                minsum[i][j] = Math.min(minsum[i - 1][j], minsum[i - 1][j + (1 << i >> 1)]);
	            }
	}
	
	static int query(int src,int des){
	    int k = (int)(Math.log(des - src + 1.0) / Math.log(2.0));
	    int maxres = Math.max(maxsum[k][src], maxsum[k][des - (1 << k) + 1]);
	    int minres = Math.min(minsum[k][src], minsum[k][des - (1 << k) + 1]);
	    return maxres-minres;
	}
	
	static int bisearch(int p){
	    int l=p,r=n;
	    int res=p;
	    while(l<=r){
	        int mid=(l+r)>>1;
	        int dif=query(p,mid);
	        if (dif<k){
	            res=mid;l=mid+1;
	        }
	        else
	            r=mid-1;
	    }
	    return res-p+1;
	}
	
	public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt();
        while(T-->0){
        	n=in.nextInt();
        	k=in.nextInt();
        	a=new int[n+1];
        	maxsum=new int[maxm][n+1];
        	minsum=new int[maxm][n+1];
        	for(int i=1;i<=n;i++){
        		a[i]=in.nextInt();
        		maxsum[0][i]=minsum[0][i]=a[i];
        	}
        	RMQ();
        	long res=0L;
        	for(int i=1;i<=n;i++)
        		//out.print(bisearch(i)+" ");
        		res+=bisearch(i);
        	out.println(res);
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
/** Aug 27, 2015 9:25:32 PM
 * PrjName:hdu3388
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static Vector<Integer> v1=new Vector<Integer>();
	static Vector<Integer> v2=new Vector<Integer>();
	static HashSet<Integer> st=new HashSet<Integer>();
	static Integer[] fac=new Integer[0];
	static Vector<Integer> get_prime_factor(int n){
        Vector<Integer> res=new Vector<Integer>();
        res.clear();
        for(int i=2;i*i<=n;i++)
            if (n%i==0){
                res.add(i);
                while(n%i==0)
                    n/=i;
            }
        if (n>1) res.add(n);
        return res;
    }
	static long check(long n,int low){
	    long sum=0L;
	    for(int i=low;i<fac.length;i++)
	        sum+=n/fac[i].longValue()-check(n/fac[i].longValue(),i+1);
	    return sum;
	}
	/*static long check(long n){
		long res=0L;
		int m=fac.length;
		for(int i=1;i<(1L<<m);i++){
			long tmp=1L;
			boolean tag=false;
			for(int j=0;j<m;j++)
				if (((1L<<j)&i)>0L){
					tmp*=fac[j].longValue();
					tag^=true;
				}
			res+=tag?n/tmp:-n/tmp;
		}
		return n-res;
	}*/
	static long bisearch(long low,long high,long key){
		long l=low,r=high,mid;
		while(l<=r){
			mid=(l+r)>>1;
			//out.println(l+" "+r+" "+mid+" "+check(mid)+" "+key);
			if (mid-check(mid,0)>=key)
				r=mid-1;
			else
				l=mid+1;
		}
		return l;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int m=in.nextInt();
			int n=in.nextInt();
			int k=in.nextInt();
			v1=get_prime_factor(m);
			v2=get_prime_factor(n);
			st.clear();
			for(Integer e:v1.toArray(new Integer[0]))
				st.add(e);
			for(Integer e:v2.toArray(new Integer[0]))
				st.add(e);
			//for(Integer e:st.toArray(new Integer[0])) out.println(e);
			fac=st.toArray(new Integer[0]);
			out.println("Case "+(++cas)+": "+bisearch(1, 0x3f3f3f3f3f3f3f3fL, k));
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

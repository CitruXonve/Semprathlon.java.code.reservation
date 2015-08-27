/** Aug 26, 2015 9:40:09 PM
 * PrjName:hdu4135
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int maxn=1001;
	static int[] pri,fstp;
	static Vector<Integer> vec=new Vector<Integer>();
	static void get_prime(){
		pri=new int[maxn];
		fstp=new int[maxn];
		for(int i=2;i<maxn;i++){
			if (fstp[i]==0){
				pri[++pri[0]]=i;
			}
			for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){
				int k=i*pri[j];
				fstp[k]=pri[j];
				//if (fstp[i]==pri[j]){
				if (i%pri[j]==0)
					break;
			}
		}
	}
	static Vector<Integer> get_prime_factor(int n){
		Vector<Integer> res=new Vector<Integer>();
		res.clear();
		for(int i=1;i<=pri[0]&&pri[i]*pri[i]<=n;i++)
			if (n%pri[i]==0){
				res.add(pri[i]);
				while(n%pri[i]==0)
					n/=pri[i];
			}
		if (n>1) res.add(n);
		return res;
	}
	/*static Vector<Integer> get_prime_factor(int n){
		Vector<Integer> res=new Vector<Integer>();
		int last=1;
		while(n>1&&fstp[n]>0){
			if (fstp[n]!=last)
				res.add(fstp[n]);
			last=fstp[n];
			n/=fstp[n];
		}
		if (n>1&&n!=last) res.add(n);
		return res;
	}*/
	static long solve(long n,Vector<Integer> vec){
		long res=0L;
		final int m=vec.size();
		for(long i=1L;i<(1L<<m);i++){
			boolean tag=false;
			long tmp=1L;
			for(int j=0;j<m;j++)
				if (((1L<<j)&i)>0){
					tag^=true;
					tmp*=vec.get(j).longValue();
				}
			res+=tag?n/tmp:-n/tmp;
		}
		return n-res;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		get_prime();
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			long a=in.nextLong();
			long b=in.nextLong();
			int n=in.nextInt();
			//get_prime_factor(n,vec);
			vec=get_prime_factor(n);
			/*for(int i=0;i<vec.size();i++)
			out.print(vec.get(i)+" ");
			out.println();*/
			//out.println(solve(b,vec)+" "+solve(a-1,vec));
			out.println("Case #"+(++cas)+": "+(solve(b,vec)-solve(a-1,vec)));
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

/** Sep 5, 2015 7:30:23 PM
 * PrjName:Bc54-02
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxn=109000;
	static int[] a;
	static int[] pri,phi,fstp;
	static void get_prime(){
		pri=new int[maxn];
		fstp=new int[maxn];
		phi=new int[maxn];
		phi[1]=1;
		for(int i=2;i<maxn;i++){
			if (fstp[i]==0){
				pri[++pri[0]]=i;
				phi[i]=i-1;
			}
			for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){
				int k=i*pri[j];
				fstp[k]=pri[j];
				//if (fstp[i]==pri[j]){
				if (i%pri[j]==0){
					phi[k]=phi[i]*pri[j];
					break;
				}
				else
					phi[k]=phi[i]*(pri[j]-1);
			}
		}
	}
	static int get_fstp(int n){
		if (n<maxn)
			return fstp[n]>0?fstp[n]:n;
		for(int i=2;i<maxn;i++)
			if (n%i==0)
				return fstp[i]>0?fstp[i]:i;
		return 0;
	}
	static Vector<Integer> get_prime_factor(int n){
		Vector<Integer> res=new Vector<Integer>();
		while(n>1&&fstp[n]>0){
			res.add(fstp[n]);
			n/=fstp[n];
		}
		if (n>1) res.add(n);
		return res;
	}
	static Vector<Integer> v=new Vector<Integer>();
	static Integer[] f;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		get_prime();
		
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			v.clear();
			a=new int[n+1];
			for(int i=1;i<=n;i++){
				a[i]=in.nextInt();
				if (a[i]==1) continue;
				int tmp=get_fstp(a[i]);
				v.add(tmp);
				if (a[i]/tmp>1) v.add(get_fstp(a[i]/tmp));
			}
			/*v.sort(new Comparator<Integer>() {
				public int compare(Integer o1,Integer o2){
					return Integer.compare(o1, o2);
				}
			});*/
			//out.println(v.get(0));
			f=v.toArray(new Integer[0]);
			Arrays.sort(f);
			if (v.size()<2)
				out.println(-1);
			else 
				out.println(f[0]*f[1]);
		}
		out.flush();
		out.close();
	}

}
class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;
 
    public InputReader(InputStream stream){
           reader = new BufferedReader(
                   new InputStreamReader(stream), 32768);
           tokenizer = null;
    }
 
    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(
                           reader.readLine());
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

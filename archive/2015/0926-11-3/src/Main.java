/** Sep 26, 2015 3:52:30 PM
 * PrjName:0926-11-3
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static HashMap<Long, Long> mp=new HashMap<Long,Long>();
	static HashSet<Long> st=new HashSet<Long>();
	
	static long pow_mod(long n, long m, long mod) {
        long res = 1L;
        n %= mod;
        while (m > 0L) {
            if ((m & 1L) > 0L)
                res = res * n % mod;
            n = n * n % mod;
            m >>= 1;
        }
        return res;
	}
	static long ab;
	static boolean check(long a,long ta,long b,long k1,long b1,long k2,long c){
		long aa=ta*ab%c;
		if ((k2&1L)>0L){
			if ((aa+1)%c!=0||(aa+b)%c!=0)
				return false;
		}
		else{
			if ((aa+b)%c!=0)
				return false;
		}
		return true;
	}
	
	static boolean period(long a,long mod,long b,long k1,long b1,long k2){
		a%=mod;
		long ref=1,res=0;
		long tmp=ref;
		//mp.put(res, ref);
		while(!mp.containsValue(tmp)){
			mp.put(res, tmp);
			tmp*=a;tmp%=mod;res++;
			if (!check(a,tmp,b,k1,b1,k2,mod))
				return false;
		}
		//mp.put(0L, ref);
		return true;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int cas=0;
		while(in.nextLine()!=null){
			int c=in.nextInt();
			int k1=in.nextInt();
			int b1=in.nextInt();
			int k2=in.nextInt();
			//long st=System.currentTimeMillis();
			out.println("Case #"+(++cas)+":");
			//long now=System.currentTimeMillis();
			boolean has=false;
			if (c>1){
				out.println(1+" "+(c-1));
				has=true;
			}
			
			
			for(int i=2;i<c;i++){
				mp.clear();
				
				
				/*for(int j=0;j<mp.size();j++)
					out.print(mp.get(j+0L)+" ");
				out.println();*/
				ab=pow_mod(i, b1, c);
				if (period(pow_mod(i, k1, c), c,c-1,k1,b1,k2)){
					out.println(i+" "+(c-1));
					has=true;
				}
			}
			if (!has) out.println(-1);
			//out.println("time="+(System.currentTimeMillis()-now));
			//out.println(System.currentTimeMillis()-st);
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
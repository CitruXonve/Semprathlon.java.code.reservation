/**
 * Nov 7, 2015 8:55:12 PM
 * PrjName: hdu5524-2
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int maxn=64;
	static long[] p2;
	static HashSet<Long> st=new HashSet<Long>();
	static PrintWriter out=new PrintWriter(System.out);
	static void solve(long n){
        if (n>0L){
            st.add(n);
//            out.println(l+","+r);
            int i=1;
            if (n>1L){
                for(;i<maxn-1;i++){
                    
                    if (p2[i]<n&&n<=p2[i+1])
                        break;
                    st.add(p2[i]);
                }
                if (n-p2[i]<p2[i]){
                	st.add(p2[i-1]);
                    solve(n-1-p2[i-1]);
                }
                else{
                    solve(n-1-p2[i]);
                    st.add(p2[i]);
                }
            }
        }
    }
	static void init(){
		p2=new long[maxn];
		p2[0]=1L;
		for(int i=1;i<maxn;i++){
			p2[i]=p2[i-1]<<1;
			p2[i-1]--;
		}
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		
		init();
		while(in.nextLine()!=null){
			long n=in.nextLong();
			
			st.clear();solve(n);
			/*int i=0;
			for(;i<maxn-1;i++)
				if (p2[i]<n&&n<=p2[i+1])
					break;
			solve(1,p2[i]);
			solve(n-p2[i]+1,n);*/
			out.println(st.size());
//			out.print(n+","+st.size()+":");
			/*for(Long e:st.toArray(new Long[0]))
				out.print(e+" ");
			out.println();*/
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

import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=10000010;
    static int[] pri=new int[maxn];
    static int[] fstp=new int[maxn];
    static int[] miu=new int[maxn];
    static long solve(int n,int m,int k){
    	n/=k;m/=k;
        long ans=0;
        /*for(int i=1;i<=n;i++)
            ans-=(long)miu[i]*(n/i)*(n/i);
        ans/=2;*/
        for(int i=1;i<=n;i++)
            ans+=(long)miu[i]*(long)(n/i)*(long)(m/i);
        return ans;
    }
    static void get_prime(){
        miu[1]=1;
        for(int i=2;i<maxn;i++){
            if (fstp[i]==0){
                pri[++pri[0]]=i;
                miu[i]=-1;
            }
            for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){
                int k=i*pri[j];
                fstp[k]=pri[j];
                if (i%pri[j]==0){
                    miu[k]=0;
                    break;
                }
                else{
                    miu[k]=-miu[i];
                }
            }
        }
    }
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
//		PrintWriter out=new PrintWriter(System.out);
//		Scanner in=new Scanner(System.in);
		get_prime();
		int T=in.nextInt();
//		out.println(solve(10,10,1));out.println(solve(10,10,4));out.println(solve(10,10,9));
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			if (n==1||m==1){
				System.out.println(0);continue;
			}
			if (n>m){
				int t=n;n=m;m=t;
			}
			long ans=0;
			int upper=(int)Math.sqrt(n);
			for(int i=1;i<=upper;i++){
//				ans+=solve(n,m,i*i);
//				out.print(ans+" ");
				int tn=n/(i*i),tm=m/(i*i);
				for(int j=1;j<=tn;j++)
		            ans+=(long)miu[j]*(long)(tn/j)*(long)(tm/j);
			}
			System.out.println((long)n*(long)m-ans);
		}
//		out.flush();
//		out.close();
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

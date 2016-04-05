import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=100010;
	static int[] pri,phi,fstp,miu;
    static void get_prime(){
        pri=new int[maxn];
        fstp=new int[maxn];
        phi=new int[maxn];
        miu=new int[maxn];
        phi[1]=1;
        miu[1]=1;
        for(int i=2;i<maxn;i++){
            if (fstp[i]==0){
                pri[++pri[0]]=i;
                phi[i]=i-1;
                miu[i]=-1;
            }
            for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){
                int k=i*pri[j];
                fstp[k]=pri[j];
                //if (fstp[i]==pri[j]){
                if (i%pri[j]==0){
                    phi[k]=phi[i]*pri[j];
                    miu[k]=0;
                    break;
                }
                else{
                    phi[k]=phi[i]*(pri[j]-1);
                    miu[k]=-miu[i];
                }
            }
        }
    }
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		get_prime();
		while(T-->0){
			in.nextInt();
			int n=in.nextInt();
			in.nextInt();
			int m=in.nextInt();
			int k=in.nextInt();
			if (k==0){
				out.println("Case "+(++cas)+": 0");
				continue;
			}
			n/=k;m/=k;
			if (n>m){
				int t=n;n=m;m=t;
			}
			long ans=0;
			for(int i=1;i<=n;i++)
				ans-=(long)miu[i]*(n/i)*(n/i);
			ans/=2;
			for(int i=1;i<=n;i++)
				ans+=(long)miu[i]*(n/i)*(m/i);
			out.println("Case "+(++cas)+": "+ans);
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

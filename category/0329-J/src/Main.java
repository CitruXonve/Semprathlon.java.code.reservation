/**
 * Mar 29, 2016 6:36:11 PM
 * PrjName: 0329-J
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=1010;
	static int[] pri,fstp;
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
            }
        }
    }
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		get_prime();
//		out.println(pri[0]);
		while(T-->0){
			int n=in.nextInt();
			boolean ans=false;
			for(int i=1;i<=pri[0];i++){
				if (pri[i]>(n+3)/3||ans)break;
				for(int j=i;j<=pri[0];j++){
					if (pri[j]>(n+2)/2||ans) break;
					int k=n-pri[i]-pri[j];
					if (fstp[k]==0){
						out.println(pri[i]+" "+pri[j]+" "+k);
						ans=true;
						break;
					}
				}
			}
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

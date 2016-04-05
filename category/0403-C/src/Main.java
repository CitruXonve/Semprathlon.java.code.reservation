/**
 * Apr 3, 2016 5:46:27 PM
 * PrjName: 0403-C
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	final static int maxn=500010;
	static int[] s=new int[maxn];
	static int[] f=new int[maxn];
	static int[] m=new int[maxn];
	static int[] a=new int[maxn];
	
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			int n=in.nextInt();
			Arrays.fill(f, 0);
			int ans=0;
			for(int i=1;i<=n;i++){
				in.nextLine();
				m[i]=in.nextInt();
				boolean has=false;
				Arrays.fill(s, 0);
				for(int j=1;j<=m[i];j++){
					a[j]=in.nextInt();
					if (f[a[j]]>0){
						has=true;
						s[f[a[j]]]++;
					}
				}
//				for(int j=1;j<=m[i];j++)
//					out.print(f[a[j]]+" ");
				if (has){
					boolean res=true;
					for(int j=1;j<=m[i];j++)
						if (m[f[a[j]]]!=s[f[a[j]]]){
							res=false;break;
						}
					if (res){
						for(int j=1;j<=m[i];j++)
							f[a[j]]=i;
						ans++;
					}
				}
				else{
					for(int j=1;j<=m[i];j++)
						f[a[j]]=i;
					ans++;
				}
//				out.println(i+":"+ans);
			}
			out.println(ans);
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

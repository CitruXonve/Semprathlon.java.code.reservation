/**
 * Apr 3, 2016 4:09:44 PM
 * PrjName: 0403-E
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a,f;
	static int ans,cnt;
	static PrintWriter out=new PrintWriter(System.out);
	static void dfs(int t,int step,int n){
		//if (/*step>=n||*/t>=300){
			int tmp=0,k=0;
			for(int i=0;i<n;i++){
//				out.print(f[i]+"-");
				if (f[i]<0) continue;
				
				tmp+=f[i]+a[i];
				k++;
			}//out.println(tmp+","+k);
			if (k>cnt){
				cnt=k;ans=tmp;
			}
			else if (k==cnt){
				ans=Math.min(ans, tmp);
			}
			//return;
		//}
		for(int i=step;i<n;i++)
			if (f[i]<0&&t+a[i]<=300){
				f[i]=t;
				dfs(t+a[i],i+1,n);
				f[i]=-1;
			}
			/*else{ 
				dfs(t,i+1,n);
			}*/
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		
		while(in.nextLine()!=null){
			int n=in.nextInt();
			int m=in.nextInt();
			a=new int[n];
			f=new int[n];
			Arrays.fill(f, -1);
			for(int i=0;i<n;i++)
				a[i]=in.nextInt();
			
			int t=a[m];a[m]=-1;
			Arrays.sort(a);
			m=0;a[m]=t;
			
			ans=a[m];cnt=1;
			f[m]=0;
			if (a[m]>300){
				out.println("0 0");
				continue;
			}
			if (a[m]==300){
				out.println("1 "+ans);
				continue;
			}
			//dfs(a[m],0,n);
			
			t=a[m];
			for(int i=1;i<n;i++)
	 			if (t+a[i]<=300){
	 				ans+=t+a[i];cnt++;
	 				t+=a[i];
	 			}
			
			out.println(cnt+" "+ans);
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

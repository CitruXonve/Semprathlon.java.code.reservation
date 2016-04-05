/**
 * Apr 3, 2016 6:30:05 PM
 * PrjName: 0403-C-2
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
	static int[] f,m,s,a;
//	static boolean[] vis;
	final static int maxn=500010;
	static Map<Integer, Integer> mp=new HashMap<Integer, Integer>();
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextLine()!=null){
			int n=in.nextInt();
			m=new int[n+1];
			f=new int[maxn];
			a=new int[maxn];
			int ans=0;
			for(int j=1;j<maxn;j++)
				f[j]=j;
			
			for(int i=1;i<=n;i++){
				m[i]=in.nextInt();

				mp.clear();
				for(int j=1;j<=m[i];j++){
					a[j]=in.nextInt();
					mp.put(a[j], 0);
				}
				boolean has=true;
				for(Map.Entry<Integer, Integer> ent:mp.entrySet()){
					if (ent.getValue()==1) continue;
					ent.setValue(1);
					int k=ent.getKey();
					int p=k;
					
					while(p!=f[k]){
						if (mp.get(f[k])!=null){
							mp.put(f[k], 1);k=f[k];
						}
						else{
							has=false;break;
						}
						
					}
					if (!has) break;
				}
				if (has){
					ans++;
					for(int j=1;j<=m[i];j++)
						f[a[j]]=a[(j==m[i]?1:j+1)];
//					for(int j=1;j<=m[i];j++)
//						out.print(f[a[j]]+",");
				}
				
				/*int k=f[1];
				int p=k;
				
				while(p!=f[k]){
					out.print(f[k]+",");
				}*/
				
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

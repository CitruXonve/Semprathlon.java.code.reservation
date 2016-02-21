/**
 * Feb 13, 2016 7:01:48 PM
 * PrjName: Bc72-01
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int maxc=26;
	static int f[][]=new int[3][maxc];
	static boolean check1(int a,int c,int x){
		for(int i=0;i<maxc;i++){
			if (f[a][i]<1) continue;
			if (f[a][i]*x>f[c][i]) return false;
		}
		return true;
	}
	static int check2(int a,int b,int c,int x,int y){
		int res=0;
		for(int i=0;i<maxc;i++){
			if (f[a][i]*x+f[b][i]*y>f[c][i]) return 1;
			if (f[a][i]*x+f[b][i]*y<f[c][i]) res=-1;
		}
		return res;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			int a=in.nextInt();
			int b=in.nextInt();
			int c=in.nextInt();
			for(int i=0;i<maxc;i++)
				f[0][i]=f[1][i]=f[2][i]=0;
			for(int i=1;i<=a;i++){
				String str=in.next();
				char ch=str.charAt(0);
//				out.print(ch);out.flush();
				f[0][ch-'A']=in.nextInt();
			}
			for(int i=1;i<=b;i++){
				String str=in.next();
				char ch=str.charAt(0);
				f[1][ch-'A']=in.nextInt();
			}
			for(int i=1;i<=c;i++){
				String str=in.next();
				char ch=str.charAt(0);
				f[2][ch-'A']=in.nextInt();
			}
			int ansx=0,ansy=0;
			for(int x=1;x<=100;x++){
				if (!check1(0, 2, x)||ansx>0&&ansy>0) break;
				for(int y=1;y<=100;y++)
					if (check2(0, 1, 2, x, y)==0){
						ansx=x;ansy=y;break;
					}
			}
			if (ansx>0&&ansy>0)
				out.println(ansx+" "+ansy);
			else
				out.println("NO");
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

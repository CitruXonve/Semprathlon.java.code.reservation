/**
 * Mar 12, 2016 7:13:10 PM
 * PrjName: Bc75-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] a=new int[10];
	static int[][] f=new int[10][10];
	static boolean[] v=new boolean[10];
	static int n;
	static void init(){
		f[2][8]=f[8][2]=f[4][6]=f[6][4]=f[1][9]=f[9][1]=f[3][7]=f[7][3]=5;
		f[1][3]=f[3][1]=2;
		f[7][9]=f[9][7]=8;
		f[1][7]=f[7][1]=4;
		f[3][9]=f[9][3]=6;
	}
	static boolean check0(){
		for(int i=1;i<=n;i++)
			if (a[i]<1||a[i]>9) return false;
		return true;
	}
	static boolean check1(){
		for(int i=1;i<=n;i++)
			for(int j=i+1;j<=n;j++)
				if (a[i]==a[j]) return false;
		return true;
	}
	static boolean check2(){
		for (int i=1;i<n;i++){
			if (f[a[i]][a[i+1]]>0&&!v[f[a[i]][a[i+1]]]) return false;
			v[a[i]]=true;
		}
		return true;
	}
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		int T=in.nextInt();
		while(T-->0){
			n=in.nextInt();
			int cnt=0;
			for(int i=1;i<=n;i++){
				/*int tmp=in.nextInt();
				if (tmp>=1&&tmp<=9)
					a[++cnt]=tmp;*/
				a[i]=in.nextInt();
			}
//			n=cnt;
			Arrays.fill(v, false);
			if (!check0()||n<4||!check1()||!check2())
				out.println("invalid");
			else
				out.println("valid");
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

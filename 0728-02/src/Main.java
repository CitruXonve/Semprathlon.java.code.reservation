/**
 * 2015年7月28日 下午12:23:38
 * PrjName:0728-02
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[] f;
	
	final static int maxn=1000001;
	static void prime(){
		f=new int[maxn];
		int maxi=(int)(Math.sqrt(maxn)+0.5);
		for(int i=2;i<maxn;i++)
			if (f[i]==0){
				f[i]=1;
				int maxj=maxn/i;
				for(int j=2;j<maxn/i;j++)
					f[i*j]++;
			}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		prime();
		int maxf=0;
		for(int i=10000;i<10101;i++) out.print(i+"\t");
		out.println();
		for(int i=10000;i<10101;i++) /*maxf=Math.max(maxf, f[i]); */out.print(f[i]+"\t");
		//out.println(maxf);
		out.flush();
		int T=in.nextInt();
		while(T-->0){
			int L=in.nextInt();
			int R=in.nextInt();
		}
	}

}

class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream){
           reader = new BufferedReader(new InputStreamReader(stream), 32768);
           tokenizer = null;
    }

    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (IOException e) {
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

}
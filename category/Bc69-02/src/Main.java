/**
 * Jan 23, 2016 7:46:40 PM
 * PrjName: Bc69-02
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
    static int[] mon=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
    static boolean IsnotLeap(int y){
        return y%4==0&&y%100!=0||y%400==0;
    }
    static int days(int y,int m){
        if (m==2&&IsnotLeap(y))
            return 29;
        else if (m==2&&!IsnotLeap(y))
            return 28;
        else
            return mon[m-1];
            
    }
    static boolean check1(String s){
        for(int i=7;i<10;i++){
            if ((s.charAt(i)<<1)!=s.charAt(i-1)+s.charAt(i+1)||Math.abs(s.charAt(i-1)-s.charAt(i))>1||Math.abs(s.charAt(i+1)-s.charAt(i))>1)
                return false;
        }
        return true;
    }
    static boolean checkd1(int y,int m,int d){
        return y>=1980&&y<=2016&&m>=1&&m<=12&&d>=1&&d<=days(y,m);
    }
    static boolean check2(String s){
        int y=Integer.parseInt(s.substring(3, 7));
        int m=Integer.parseInt(s.substring(7, 9));
        int d=Integer.parseInt(s.substring(9, 11));
        return checkd1(y,m,d);
    }
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
                while(T-->0){
                    int n=in.nextInt();
                    long a=in.nextLong();
                    long b=in.nextLong();
                    long ans=0;
                    for(int i=1;i<=n;i++){
                        String s=in.next();
                        if (check1(s)||check2(s))
                            ans+=a;
                        else
                            ans+=b;
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

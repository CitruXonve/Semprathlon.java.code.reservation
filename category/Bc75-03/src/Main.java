/**
 * Mar 12, 2016 7:37:46 PM
 * PrjName: Bc75-03
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
    final static int maxn=2010;
    static long[][] f=new long[maxn][10];
    final static long mod=1000000007L;
    static void init(){
        f[1][0]=26;//f[2][1]=26;//f[3][2]=26;//f[0][0]=1;f[0][1]=1;f[0][2]=1;
        for(int i=2;i<maxn;i++){
            f[i][0]+=f[i-1][0]*25L%mod;f[i][0]%=mod;
            f[i][0]+=f[i-1][1]*25L%mod;f[i][0]%=mod;
            f[i][0]+=f[i-1][2]*25L%mod;f[i][0]%=mod;
            
            f[i][1]+=f[i-1][0];f[i][1]%=mod;
            f[i][2]+=f[i-1][1];f[i][2]%=mod;
        }
    }
    public static void main(String[] args) throws IOException{
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        init();
        int T=in.nextInt();
        while(T-->0){
            int n=in.nextInt();
            long ans=f[n][0]+f[n][1];ans%=mod;
            ans+=f[n][2];ans%=mod;
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
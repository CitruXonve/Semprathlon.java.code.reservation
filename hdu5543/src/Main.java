/**
 * Dec 5, 2015 5:21:06 PM
 * PrjName: hdu5543
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
    static int a[];
    static long v[],f[][][];
    public static void main(String[] args) throws IOException{
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt(),cas=0;
        while(T-->0){
            int n=in.nextInt();
            int l=in.nextInt();l<<=1;
            long maxv=0;
            a=new int[n+1];
            v=new long[n+1];
            f=new long[3][n+1][l+1];
            for(int i=1;i<=n;i++){
                a[i]=in.nextInt();
                v[i]=in.nextLong();
                a[i]<<=1;
                maxv=Math.max(maxv, v[i]);
            }
            for(int i=1;i<=n;i++)
                for(int j=l;j>=0;j--){
                    if (j>=a[i])
                        for(int k=0;k<3;k++)
                            f[k][i][j]=Math.max(f[k][i][j], f[k][i-1][j-a[i]]+v[i]);
                    if (j>=(a[i]>>1)){
                        f[1][i][j]=Math.max(f[1][i][j], f[0][i-1][j-(a[i]>>1)]+v[i]);
                        f[2][i][j]=Math.max(f[2][i][j], f[1][i-1][j-(a[i]>>1)]+v[i]);
                    }
                }
            
            long ans=maxv;
            ans=Math.max(ans, f[0][n][l]);
            ans=Math.max(ans, f[1][n][l]);
            ans=Math.max(ans, f[2][n][l]);
            out.println("Case #"+(++cas)+": "+ans);
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


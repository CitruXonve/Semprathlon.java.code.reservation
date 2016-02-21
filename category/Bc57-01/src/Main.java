/** Sep 26, 2015 7:02:44 PM
 * PrjName:Bc57-01
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
    //static int[][] g;
    static int[] r,c;
    //static HashMap<Integer, Integer> mp=new HashMap<Integer,Integer>();
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt();
        while(T-->0){
            int n=in.nextInt();
            int m=in.nextInt();
            int k=in.nextInt();
            int q=in.nextInt();
            r=new int[n+1];
            c=new int[m+1];
            //g=new int[n+1][m+1];
            while(k-->0){
                int x=in.nextInt();
                int y=in.nextInt();
                r[x]++;c[y]++;
                //g[x][y]++;
            }
            for(int i=1;i<=n;i++){
            	if (r[i]>1) r[i]=1;
                r[i]+=r[i-1];
            }
            for(int j=1;j<=m;j++){
            	if (c[j]>1) c[j]=1;
                c[j]+=c[j-1];
            }
            /*for(int i=1;i<=n;i++)
                for(int j=1;j<=m;j++)
                    g[i][j]+=g[i-1][j]+g[i][j-1]-g[i-1][j-1];*/
            while(q-->0){
                int x1=in.nextInt();
                int y1=in.nextInt();
                int x2=in.nextInt();
                int y2=in.nextInt();
                int res1=r[x2]-r[x1-1];
                int res2=c[y2]-c[y1-1];
                //int res3=g[x2][y2]-g[x2][y1-1]-g[x1-1][y2]+g[x1-1][y1-1];
                //out.println(res1+" "+(x2-x1+1)+" "+res2+" "+(y2-y1+1));
                if (y1==y2&&res1==x2-x1+1||x1==x2&&res2==y2-y1+1||res1==x2-x1+1&&res2==y2-y1+1/*||res3>0*/)
                    out.println("Yes");
                else 
                    out.println("No");
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

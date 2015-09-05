/** Sep 5, 2015 9:45:04 PM
 * PrjName:hdu1204
 * @author Semprathlon
 */
import java.awt.Toolkit;
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxn=101;
	static double[][] c;
	static void init(){
		c=new double[maxn][maxn];
		c[0][0]=1;
		for(int i=1;i<maxn;i++){
			c[i][0]=c[i][i]=1;
			for(int j=1;j<i;j++)
				c[i][j]=c[i-1][j]+c[i-1][j-1];
		}
	}
	static double pow(double n,int m){
        double res=1;
        while(m>0){
            if ((m&1)>0) res=res*n;
            n=n*n;
            m>>=1;
        }
        return res;
    }
	static String str;
	static StringTokenizer tokenizer;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out=new PrintWriter(System.out);
		init();
		while((str=br.readLine())!=null){
			//out.println(str);
			tokenizer=new StringTokenizer(str);
			int n=Integer.parseInt(tokenizer.nextToken());
			int m=Integer.parseInt(tokenizer.nextToken());
			double p=Double.parseDouble(tokenizer.nextToken());
			double q=Double.parseDouble(tokenizer.nextToken());
			//if (br.readLine()==null) break
			out.println(n+" "+m+" "+p+" "+q);
			double ans=0;
			for(int i=0;i<n;i++)
				ans+=c[i][i+m-1]*pow(p*(1-q), m)*pow(q*(1-p), i);
			out.printf("%.9f\n", c[n][m]);
		}
		out.flush();
		out.close();
	}

}
class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;
 
    public InputReader(InputStream stream){
           reader = new BufferedReader(
                   new InputStreamReader(stream), 32768);
           tokenizer = null;
    }
 
    
    
    public String next(){
        while(tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(
                           reader.readLine());
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
 
    public double nextDouble(){
    	return Double.parseDouble(next());
    }
}

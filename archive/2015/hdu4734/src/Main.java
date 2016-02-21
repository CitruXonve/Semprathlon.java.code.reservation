/** Aug 22, 2015 5:23:42 PM
 * PrjName:hdu4734
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static int[][] f;
	static int[] digit;
	final static int maxm=4600;
	static int fun(int n){
		int res=0,tmp=1;
		while(n>0){
			res+=n%10*tmp;
			tmp<<=1;
			n/=10;
		}
		return res;
	}
	static void init(){
		int tmp=1;
		f=new int[10][maxm+1];
		f[0][0]=1;
		for(int i=1;i<10;i++){
			for(int j=0;j<=maxm;j++)
				for(int k=0;k<=9;k++)
					if (j+tmp*k<=maxm)
						f[i][j+tmp*k]+=f[i-1][j];
			tmp<<=1;
		}
		for(int i=0;i<10;i++)
			for(int j=1;j<=maxm;j++) f[i][j]+=f[i][j-1];
	}
	static int[] getdg(int n){
		int[] res=new int[10];
		while(n>0){
			res[++res[0]]=n%10;
			n/=10;
		}
		return res;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int A=in.nextInt();
			int B=in.nextInt();
			int m=fun(A);
			digit=getdg(B);
			int ans=0,tmp=1<<digit[0];
			for(int i=digit[0];i>=1;i--){
				tmp>>=1;
				for(int k=0;k<digit[i];k++){
					
					if (m-k*tmp>=0){
						ans+=f[i-1][m-k*tmp];
					}
				}
				m-=digit[i]*tmp;
				if (m<0) break;
				
			}
			if (m>=0) ans++;
			out.println("Case #"+(++cas)+": "+ans);
		}
		out.flush();
		out.close();
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
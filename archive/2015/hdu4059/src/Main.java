/** Aug 27, 2015 2:45:37 PM
 * PrjName:hdu4059
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
    static int maxn=105;
    static Vector<Integer> vec=new Vector<Integer>();
    static Vector<Integer> get_prime_factor(int n){
        Vector<Integer> res=new Vector<Integer>();
        res.clear();
        for(int i=2;i*i<=n;i++)
            if (n%i==0){
                res.add(i);
                while(n%i==0)
                    n/=i;
            }
        if (n>1) res.add(n);
        return res;
    }
    final static long mod=1000000007L;
    static long pow(long n,long m,long mod){
        long res=1L;
        while(m>0L){
            if ((m&1)>0L) res=res*n%mod;
            n=n*n%mod;
            m>>=1;
        }
        return res;
    }
    static long div(long a,long b,long mod){
        long phi=mod-1L;
        return a*pow(b,phi-1,mod)%mod;
    }
    static long sum(int n,long mod){
        long res=n;
        res*=2*n+1;res%=mod;
        res*=n+1;res%=mod;
        res*=(3L*n*n+3*n-1)%mod;res%=mod;
        return div(res,30,mod);
    }
    static long solve(Vector<Integer> vec,int n){
        long res=0L;
        int m=vec.size();
        for(int i=1;i<(1L<<m);i++){
            boolean tag=false;
            int tmp=1;
            for(int j=0;j<m;j++)
                if (((1L<<j)&i)>0){
                    tmp*=vec.get(j);
                    tmp%=mod;
                    tag^=true;
                }
            res=res+(tag?1:-1)*(pow(tmp,4,mod)*sum(n/tmp,mod)%mod);
            res%=mod;
        }
        return res;
        //return (sum(n,mod)%mod-res+mod)%mod;
    }
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        //long start=System.currentTimeMillis();
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        /*for(int i=1;i<=pri[0];i++){
            out.print(pri[i]+" ");
            if (i%20==0)
                out.println();
        }*/
        //out.println(System.currentTimeMillis()-start);
        int T=in.nextInt();
        while(T-->0){
            int n=in.nextInt();
            Vector<Integer> v=get_prime_factor(n);
            /*for(int i=0;i<v.size();i++){
                out.print(v.get(i)+" ");
            }*/
            //out.println();
            out.println((sum(n,mod)-solve(v,n)+mod)%mod);
        }
        //out.println(System.currentTimeMillis()-start);
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
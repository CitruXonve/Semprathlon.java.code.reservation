/**
 * 2015年7月21日 下午12:29:24
 * PrjName:0721-01
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
    final static int maxN=100010;
    final static int maxn=400;
    final static int mod=1000000007;
    static int[] a,f;
    static HashSet<Integer> h;
    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        StreamTokenizer cin = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        while(cin.nextToken()!=StreamTokenizer.TT_EOF){
            int n=(int)cin.nval;
            a=new int[n+1];
            h=new HashSet<Integer>();
            for(int i=1;i<=n;i++){
            	a[i]=in.nextInt();
            	h.add(a[i]);
            }
                
            f=new int[maxN];
            
            int res=0;
            for(int i=n;i>=1;i--){
            	if (a[i]==1) continue;
                //out.print(f[a[i]]+" ");
                res+=n-f[a[i]]+1;
                res%=mod;
                //for(Iterator<Integer> it=h.iterator();it.hasNext();){
                	//int j=it.next().intValue();
                for(int j=1;j<=maxn;j++){
                	if (h.contains(j)&&a[i]%j==0)
                    {
                        f[j]++;
                        if (a[i]/j!=j)
                        	f[a[i]/j]++;
                    }
                }
                    
            }
            out.println(res%mod);
            out.flush();
        }
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
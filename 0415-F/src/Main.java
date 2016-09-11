/** 
 * Apr 15, 2016 6:49:41 PM 
 * PrjName: 0415-F 
 * @semprathlon 
 */  
  
import java.io.*;  
import java.util.*;  
public class Main {  
//    static Map<Integer,Integer> mp=new HashMap<Integer,Integer>();  
    public static void main(String[] args) throws IOException{  
        InputReader in=new InputReader(System.in);  
        PrintWriter out=new PrintWriter(System.out);  
        int T=in.nextInt();  
        while(T-->0){  
            int n=in.nextInt();  
            int m=in.nextInt();  
//            mp.clear();  
            int cnt=0;  
            int ans=0x7fffffff;  
            for(int i=n;i>=1;){  
//                mp.put(i, cnt++); 
            	int sum=0;
            	for(int j=m;j>=1;){  
                    if (i==j){  
                        ans=Math.min(ans,sum+cnt);
                    }  
                    sum++;  
                    j>>=1;  
                }  
            	cnt++;
                i>>=1;  
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
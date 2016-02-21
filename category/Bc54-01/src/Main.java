/** Sep 5, 2015 7:07:52 PM
 * PrjName:Bc54-01
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	final static int maxn=116;
	static String s;
	static Vector<String>[] vec=new Vector[maxn];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		for(int i=0;i<maxn;i++)
			vec[i]=new Vector<String>();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		while(T-->0){
			for(int i=0;i<maxn;i++)
				vec[i].clear();
			int n=Integer.parseInt(br.readLine());
			for(int i=1;i<=n;i++){
				s=new String(br.readLine());
				int y=0,tmp=1,j;
				for(j=s.length()-1;j>0;j--)
					if (s.charAt(j)>='0'&&s.charAt(j)<='9'){
						y+=tmp*(s.charAt(j)-'0');
						tmp*=10;
					}
					else 
						break;
				s=s.substring(0, j);
				vec[y-1900].add(s);
				//out.println(s+" "+y);
			}
			for(int i=maxn-1;i>=0;i--)
				for(int j=0;j<vec[i].size();j++)
					out.println(vec[i].get(j));
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
 
}

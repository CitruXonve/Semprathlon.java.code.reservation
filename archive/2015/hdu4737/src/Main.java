/** Aug 22, 2015 4:28:18 PM
 * PrjName:hdu4737
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static int[] a;
	static Queue<Integer> que=new LinkedList<Integer>();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			a=new int[n+1];
			que.clear();
			int ans=0;
			for(int i=1;i<=n;i++){
				a[i]=in.nextInt();
				int k=que.size();
				for(int j=0;j<k;j++){
					int tmp=que.poll()|a[i];
					if (tmp<m) que.add(tmp);
				}
				if (a[i]<m) que.add(a[i]);
				ans+=que.size();
			}
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
/** Aug 20, 2015 1:50:49 PM
 * PrjName:0820-09
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static String s1,s2;
	static int pri=29;
	static long hash(String s,int pos,long mask){
		long res=mask;
		res*=pri;
		res+=(long)(s.charAt(pos)-'a');
		return res;
	}
	static long[] lh1,lh2,rh1,rh2;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			s1=in.next();
			s2=in.next();
			int len1=s1.length();
			int len2=s2.length();
			lh1=new long[len1+2];
			rh1=new long[len1+2];
			lh2=new long[len2+2];
			rh2=new long[len2+2];
			lh1[0]=rh1[0]=0;
			for(int i=0;i<len1;i++){
				lh1[i+1]=hash(s1,i,lh1[i]);
				rh1[i+1]=hash(s1,len1-i-1,rh1[i]);
			}
				
			lh2[0]=rh2[0]=0;
			for(int i=0;i<len2;i++){
				lh2[i+1]=hash(s2,i,lh2[i]);
				rh2[i+1]=hash(s2,len2-i-1,rh2[i]);
			}
			boolean found=false;
			for(int i=1;i<=len1;){
				if (lh1[i]==lh2[i]&&rh1[len1-i]==rh2[len2-i]){
					found=true;break;
				}
				int j=i+1;
				while(j<len2-len1+i&&s2.charAt(j-1)==s2.charAt(j)) j++;
				if (lh1[i]==lh2[i]&&rh1[len1-i]==rh2[len2-j]&&s2.charAt(j-1)!=s1.charAt(i-1)){
					found=true;break;
				}
				i=j;
			}
			out.println(found?"Yes":"No");
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
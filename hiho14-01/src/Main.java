/** Aug 30, 2015 7:17:23 PM
 * PrjName:hiho14-01
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	static ArrayList<Integer> qry=new ArrayList<Integer>();
	static ArrayList<Integer> l=new ArrayList<Integer>();
	static ArrayList<Integer> h=new ArrayList<Integer>();
	static Integer[] L=new Integer[0];
	static Integer[] H=new Integer[0];
	static Integer[] Q=new Integer[0];
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int n=in.nextInt();
		qry.clear();
		l.clear();
		h.clear();
		for(int i=1;i<=n;i++){
			in.next();
			String sgn=in.next();
			int k=in.nextInt();
			if (sgn.matches(">=")){
				l.add(k);//out.println(1);
			}
			else if (sgn.matches(">")){
				l.add(k+1);//out.println(2);
			}
			else if (sgn.matches("<=")){
				h.add(k);//out.println(3);
			}
			else if(sgn.matches("<")){
				h.add(k-1);//out.println(4);
			}
			else if (sgn.matches("=")){
				qry.add(k);//out.println(5);
			}
		}
		L=l.toArray(new Integer[0]);
		H=h.toArray(new Integer[0]); 
		Q=qry.toArray(new Integer[0]);
		Arrays.sort(Q);
		Arrays.sort(L);
		Arrays.sort(H);
		int ans=1;
		/*if (H.length<1)*/{
		for(int i=0;i<L.length;i++){
			int tmp=1,k;
			for(k=0;k<Q.length;k++)
				if (L[i]<=Q[k]){
					tmp+=i+1;break;
				}
			for(k++;k<Q.length;k++)
				if (Q[k]==Q[k-1])
					tmp++;
			ans=Math.max(tmp, ans);
		}
		}
		/*if (L.length<1)*/{
		for(int j=0;j<H.length;j++){
			int tmp=1,k;
			for(k=0;k<Q.length;k++)
				if (Q[k]<=H[j]){
					tmp+=H.length-j;break;
				}
			for(k++;k<Q.length;k++)
				if (Q[k]==Q[k-1])
					tmp++;
			ans=Math.max(tmp, ans);
		}
		}
		for(int i=0;i<L.length;i++)
			for(int j=0;j<H.length;j++){
				if (L[i]>H[j]) continue;
				int tmp=1,k;
				for(k=i+1;k<L.length;k++)
					if (L[k]<=H[j]) tmp++;
				for(k=j-1;k>=0;k--)
					if (H[k]>=L[i]) tmp++;
				for(k=0;k<Q.length;k++)
					if (L[i]<=Q[k]&&Q[k]<=H[j]){
						tmp+=i+1+H.length-j;break;
					}
				for(k++;k<Q.length;k++)
					if (Q[k]==Q[k-1])
						tmp++;
				ans=Math.max(tmp, ans);
			}
		out.println(ans);
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

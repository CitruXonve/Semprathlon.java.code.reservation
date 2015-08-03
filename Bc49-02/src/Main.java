/**
 * 2015年8月1日 下午7:47:15
 * PrjName:Bc49-02
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static long pri=29L;
	static long lh(String s,long res,int p){
		return res*pri+(long)(s.charAt(p)-'a');
	}
	static long rh(String s,long res,int p,long m){
		return res+(long)(s.charAt(p)-'a')*m;
	}
	static BitSet v=new BitSet(20010);
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			String s=new String(in.next());
			int len=s.length();
			//s=s+"#"+(new StringBuffer(s).reverse().toString());
			//out.println(s);
			boolean ans=false;
			long l1=0L,r1=0L,tmp=1L;
			for(int i=0;i<len;i++){
				if (ans) break;
				l1=lh(s, l1, i);
				r1=rh(s, r1, i, tmp);
				//out.println(i+" "+l1+" "+r1);
				tmp*=pri;
				//if ((i&1)>0) continue;
				if (l1==r1){
					//out.println("f"+i);
					long l2=0L,r2=0L,tmp2=1L;
					v.clear();
					for(int j=i+1;j<len;j++){
						l2=lh(s,l2,j);
						r2=rh(s,r2,j,tmp2);
						tmp2*=pri;
						//if (((j-i+1)&1)>0) continue;
						if (l2==r2) v.set(j);
					}
					
					l2=0L;r2=0L;tmp2=1L;
					for(int j=len-1;j>i;j--){
						l2=lh(s,l2,j);
						r2=rh(s,r2,j,tmp2);
						tmp2*=pri;
						//if (((len-j+1)&1)>0) continue;
						if (l2==r2&&v.get(j-1)){
							ans=true;break;
						}
					}
				}
			}
			out.println(ans?"Yes":"No");
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
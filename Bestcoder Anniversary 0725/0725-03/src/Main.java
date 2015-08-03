import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 2015年7月25日 下午8:24:01
 * PrjName:0725-03
 * @ Semprathlon
 */
public class Main {
	//static int[] f;
	static Vector<Integer> v=new Vector<Integer>();
	static int maxn=1000000010;
	//static int maxn=100;
	static void init(){
		//f=new int[maxn];
		//for(int i=1;i<=6;i++) f[i]=i;
		for(int i=1;i<maxn;i++){
			int tmp=3*i*(i-1)+1;
			if (tmp>maxn) break;
			//f[tmp]=1;f[tmp+1]=2;
			v.add(tmp);
		}
	}
	static int bis(int n){
		int l=0,r=v.size(),mid=0;
		while(l<r-1){
			mid=(l+r)>>1;
			if (v.get(mid-1)<=n)
				l=mid;
			else
				r=mid-1;
		}
		if (v.get(l)>n) return v.get(l-1);
		else return v.get(l);
	}
	static int solve(int n){
		int res=0;
		while (n>0){
			res++;
			n-=bis(n);
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		init();
		//out.println(v.size());
		int T=in.nextInt();
		while(T-->0){
			int n=in.nextInt();
			out.println(solve(n));
			//out.println(bis(n));
		}
		
		
		//for(int i=0;i<v.size();i++) out.print(v.get(i)+"  ");
		/*int T=in.nextInt();
		while(T-->0){
			
		}*/
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
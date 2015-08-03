/**
 * 2015年8月1日 下午7:15:14
 * PrjName:Bc49-01
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static ArrayList<Integer> v=new ArrayList<Integer>();
	static int[] a,f;
	static int lowbit(int x){
		return x&(-x);
	}
	static int get(int x){
		int res=0;
		while(x>0){
			res++;
			x-=lowbit(x);
		}
		return res;
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt();
		while(T-->0){
			v.clear();
			int n=in.nextInt();
			int m=in.nextInt();
			for(int i=1;i<=n;i++)
				v.add(in.nextInt());
			Collections.sort(v);
			f=new int[1<<n];
			Arrays.fill(f, -1);
			f[0]=m;
			//out.println(n);
			int ans=Integer.MAX_VALUE;
			for(int i=0;i<(1<<n);i++)
				if (f[i]>0)
					for(int j=0;j<n;j++)
						if (((1<<j)&i)==0){
							int k=i|(1<<j);
							f[k]=f[i]%v.get(j);
							if (f[k]==0)
								ans=Math.min(ans, get(k));
						}
			if (ans==Integer.MAX_VALUE) out.println(-1);
			else out.println(ans);
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
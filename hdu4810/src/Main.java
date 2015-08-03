/**
 * 2015年7月16日 下午2:27:22
 * PrjName:hdu4810
 * @ Semprathlon
 */
import java.io.*;
class Comb{
	int[][] dat;
	int n,mod;
	Comb(int size,int m){
		n=size;mod=m;
		dat=new int[n+1][n+1];
		dat[0][0]=dat[1][0]=dat[1][1]=1;
		for(int i=2;i<=n;i++){
			dat[i][0]=1;
			for(int j=1;j<=n;j++)
				dat[i][j]=(dat[i-1][j-1]+dat[i-1][j])%mod;
		}
	}
	int get(int n,int m){
		return dat[n][m];
	}
}
class Digit{
	int[] dat;
	int n;
	int lowbit(int x){
		return x&(-x);
	}
	Digit(int size){
		n=size;
		dat=new int[n+1];
	}
	void insert(int sum){
		for(int i=0;i<=n;i++)
			if ((sum&(1<<i))>0)
				dat[i]++;
	}
	int get(int k){
		return dat[k];
	}
}
public class Main {
	final static int mod=1000003;
	final static int bit=31;
	final static int maxn=1000;
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		Comb c=new Comb(maxn,mod);
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			Digit digit=new Digit(bit);
			int n=(int)in.nval;
			for(int i=1;i<=n;i++){
				in.nextToken();
				digit.insert((int)in.nval);
			}
			long[] ans=new long[n+1];
			for(int i=1;i<=n;i++)
				for(int j=0;j<=bit;j++)
					for(int k=1,s=digit.get(j);k<=i;k+=2){
						ans[i]+=(long)c.get(s, k)*(long)c.get(n-s, i-k)*((1L<<j)%(long)mod);
						ans[i]%=(long)mod;
					}
			for(int i=1;i<n;i++)
				out.print(ans[i]+" ");
			out.println(ans[n]);
		}
		out.flush();
		out.close();
	}

}

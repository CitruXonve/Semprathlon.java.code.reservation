/**
 * 2015年7月26日 下午2:12:18
 * PrjName:0726-05-2
 * @ Semprathlon
 */
import java.io.*;
public class Main {
	static int[] a,f;
	static int n,m;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			n=(int)in.nval;
			in.nextToken();
			m=(int)in.nval;
			a=new int[n+1];
			int ans=0,sum1=0,sum2=0;
			for(int i=1;i<=n;i++){
				in.nextToken();
				int x=(int)in.nval;
				in.nextToken();
				int y=(int)in.nval;
				ans+=Math.abs(x-y);
				sum1+=x;
				sum2+=y;
			}
			for(int i=1;i<=m;i++){
				in.nextToken();
				int u=(int)in.nval;
				in.nextToken();
				int v=(int)in.nval;
			}
			if (sum1==sum2)
				out.println(ans>>1);
			else
				out.println(-1);
		}
		out.flush();
		out.close();
	}

}

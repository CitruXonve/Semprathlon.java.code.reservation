/**
 * 2015年7月23日 下午3:37:58
 * PrjName:0723-02
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int n,m;
	static int Get(int x,int y){
		int dx=x==((n+1)>>1)?(n+1)>>1:(x>=((n+2)>>1)?n+1-x:x);
		int dy=y==((m+1)>>1)?(m+1)>>1:(y>=((m+2)>>1)?m+1-y:y);
		return Math.min(dx, dy);
	}
	static int GetMax(int n,int m){
		if (n%2>0&&m%2>0) return Get((n+1)>>1, (m+1)>>1);
		if (n%2==0&&m%2>0) return Get((n+2)>>1, (m+1)>>1);
		if (n%2>0&&m%2==0) return Get((n+1)>>1, (m+2)>>1);
		return Get((n+2)>>1, (m+2)>>1);
	}
	static int GetMax2(int n,int m){
		if (n%2>0&&m%2>0) return Get((n+3)>>1, (m+3)>>1);
		if (n%2==0&&m%2>0) return Get((n+2)>>1, (m+1)>>1);
		if (n%2>0&&m%2==0) return Get((n+1)>>1, (m+2)>>1);
		return Get((n+2)>>1, (m+2)>>1);
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			n=(int)in.nval;
			in.nextToken();
			m=(int)in.nval;
			in.nextToken();
			int x=(int)in.nval;
			in.nextToken();
			int y=(int)in.nval;
			int t0=Get(x,y);
			int t1=GetMax(n,m);
			int t2=GetMax2(n,m);
			//out.println(t0+" "+t1+" "+t2);
			if (t0==t1) out.println(t2);
			else out.println(t1);
		}
		out.flush();
		out.close();
	}

}

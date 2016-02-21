/**
 * 2015年7月26日 下午12:25:02
 * PrjName:0726-02
 * @ Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static boolean[] f;
	static final int maxn=101;
	static void init(){
		f=new boolean[maxn];
		f[0]=false;
		f[1]=f[2]=f[3]=f[4]=true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			int n=(int)in.nval;
			if (n==0) out.println("fail");
			else out.println("win");
			
		}
		out.flush();
		out.close();
	}

}

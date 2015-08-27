/** Aug 22, 2015 7:03:37 PM
 * PrjName:Bc52-01
 * @author Semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedInputStream(System.in));
		PrintWriter out=new PrintWriter(System.out);
		while(in.nextToken()!=StreamTokenizer.TT_EOF){
			int x=(int)in.nval;
			in.nextToken();
			int y=(int)in.nval;
			in.nextToken();
			int w=(int)in.nval;
			in.nextToken();
			int n=(int)in.nval;
			if (n==1){
				out.println(0);continue;
			}
			if (w>x){
				out.println((n-1)*(x+y));continue;
			}
			else if (w==x){
				out.println((n-1)/2*(x+y)+(n-1)%2*x);continue;
			}
			else{
				int tmp=(x+w)/w;
				out.println((n-1)/tmp*(x+y)+(n-1)%tmp*w);continue;
			}
		}
		out.flush();
		out.close();
	}

}

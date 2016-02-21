/** Aug 29, 2015 7:39:29 PM
 * PrjName:Bc53-01
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
			int n=(int)in.nval;
			in.nextToken();
			int m=(int)in.nval;
			boolean has=false;
			//out.println(n+"-"+m);
			for(int i=1;i<=m;i++){
				in.nextToken();
				int u=(int)in.nval;
				in.nextToken();
				int v=(int)in.nval;
				if (u==1&&v==n||u==n&&v==1)
					has=true;
			}
			if (has)
				out.println(1+" "+n*(n-1)/2);
			else
				out.println(1+" "+1);
		}
		out.flush();
		out.close();
	}

}

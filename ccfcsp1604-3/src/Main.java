import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 */

/**
 * @author semprathlon
 *
 */
public class Main {

	/**
	 * @param args
	 */
	
	static void solve(InputReader in,PrintWriter out,Stack<String> s){
		String line=in.nextLine();
		
//		boolean relative=false;
		
		String[] spl=line.split("/.*?");
		
//		for(String dir:spl){
//			if (dir.compareTo("..")==0){
//				relative=true;break;
//			}
//		}
		
		int first=-1;
		for(int i=0;i<spl.length;i++)
			if (spl[i].compareTo(".")!=0&&spl[i].compareTo("..")!=0)
			{
				first=i;break;
			}
		
		if (line.charAt(0)=='/'&&first<=0) s.clear();
		if (spl.length>0&&spl[0].compareTo(".")!=0){
				
				
//				if (!relative||spl[0].compareTo("..")!=0) s.clear();
				
				
				if(spl[0].compareTo("..")==0&&!s.empty()) s.pop();
		}
		
		
		
		for(String dir:spl){
//			out.print(dir+",");
			if (dir.compareTo(".")==0||dir.compareTo("")==0) continue;
			if (dir.compareTo("..")==0){
				if (!s.empty())s.pop();
				continue;
			}
			if (dir.length()>0)
			s.push(dir);
		}
		
		int cnt=0;
		for(String dir:s.toArray(new String[0])){
			out.print("/"+dir);cnt++;
		}
		out.println(cnt==0?"/":"");
		
//		if (!s.empty()) s.pop();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		Stack<String> s=new Stack<>();
		int n=in.nextInt();
		String line=in.nextLine();
		for(String dir:line.split("/.*?")){
			if (dir.length()>0)
			s.push(dir);
		}
//					Stack<String> s0=(Stack<String>)s.clone();
//			int cnt=0;
//			for(String dir:s.toArray(new String[0])){
//				out.print("/"+dir);cnt++;
//			}
//			out.println(cnt==0?"/":"");
		for(int i=0;i<n;i++){

			
			
			solve(in, out, s);
		}
		out.flush();
		out.close();
	}

}
class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public boolean hasNext() {
        return tokenizer != null && tokenizer.hasMoreTokens() || nextLine() != null;
    }

    public String nextLine() {
        String tmp = null;
        try {
            tmp = reader.readLine();
            tokenizer = new StringTokenizer(tmp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return tmp;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
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

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}
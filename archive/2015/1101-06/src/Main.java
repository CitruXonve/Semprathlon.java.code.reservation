/**
 * Nov 1, 2015 1:15:14 PM
 * PrjName: 1101-06
 * @semprathlon
 */

import java.io.*;
import java.util.*;
public class Main {
    static int[] a1,a2;
    public static void main(String[] args) throws IOException{
        InputReader in=new InputReader(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int T=in.nextInt();
        while(T-->0){
            int n=in.nextInt(),top1=0,top2=0;
            a1=new int[n+2];a1[0]=-1;
            a2=new int[n+2];a2[0]=0x7fffffff;
            for(int i=1;i<=n;i++){
                int k=in.nextInt();
//                if (top2<3){
                if (k>=a1[top1])
                    a1[++top1]=k;
                else{
                    int l=1,r=top1;
                    while(l<=r){
                        int mid=(l+r)>>1;
                        if (a1[mid]>k)
                            r=mid-1;
                        else
                            l=mid+1;
//                        out.println(l+" "+r);
                    }
                    a1[l]=k;
                }
//                }
                
//                if (top1<3){
                if (k<=a2[top2])
                    a2[++top2]=k;
                else{
                    int l=1,r=top2;
                    while(l<=r){
                        int mid=(l+r)>>1;
                        if (a2[mid]<k)
                            r=mid-1;
                        else
                            l=mid+1;
//                        out.println(l+" "+r);
                    }
                    a2[l]=k;
                }
//                }
            }
//            out.println(top1+" "+top2);
            out.println(n<4||top1==n-1||top2==n-1||top1==n||top2==n?"YES":"NO");
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


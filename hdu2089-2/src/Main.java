/** Sep 1, 2015 8:57:53 PM
 * PrjName:hdu2089-2
 * @author Semprathlon
 */

import java.io.*;
import java.util.*;

public class Main {

    /**
     * @param args
     */
    final static int maxl = 8;
    static int[][] f,g;
    static int[] digit;

    static void init() {
        f = new int[maxl + 1][10];
        f[0][0]=1;
        g = new int[maxl + 1][10];
        g[0][0]=1;
    }
    
    static void getd(int n){
        digit = new int[maxl + 1];
        while (n > 0) {
            digit[++digit[0]] = n % 10;
            n /= 10;
        }
    }
    static int dfs(int d,int r,boolean c){
        if (d==0){
        	if (c) f[d+1][r]++;
            return 0;
        }
        if (!(f[d+1][r]>0)){
        	int add=0;
            int up=c?9:digit[d];
        	for(int i=0;i<=up;i++)
                if (!(r==6&&i==2)){
                    if(!(f[d][i]>0))
                        dfs(d-1,i,c||i!=up);
                    if (i!=4)
                        add+=f[d][i];
                }
            f[d+1][r]+=add;
        }
        int res=digit[d]==4||digit[d]==2&&digit[d+1]==6?0:dfs(d-1,0,false);
        for(int i=0;i<digit[d];i++)
        	if (i!=4&&!(i==2&&digit[d+1]==6))
        		res+=f[d][i];
        /*if (digit[d]>0&&!(g[d][digit[d]]>0)){
        	int i=0;
        	for(;i<9&&g[d][i]>0;i++){}
        	for(;i<digit[d];i++)
        		if (i==0) 
        			g[d][i]=f[d][i];
        		else if (i==4||i==2&&digit[d+1]==6)
        			g[d][i]=g[d][i];
        		else
        			g[d][i]=g[d][i-1]+f[d][i];
        }
        if (digit[d]>0) res+=g[d][digit[d]-1];*/
        return res;
    }
    static int solve(int n) {
        int res = 0;
        getd(n);
        return dfs(digit[0],0,true);
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        PrintWriter out = new PrintWriter(System.out);
        init();
        
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            if (n == 0 && m == 0)
                break;
            //out.println(solve(n)+" "+solve(m+1));
            out.println(solve(m + 1) - solve(n));
            //out.flush();
        }
        /*for(int i=0;i<=maxl;i++){
            for(int j=0;j<10;j++) out.print(f[i][j]+" ");
            out.println();
        }*/
        out.flush();
        out.close();
    }

}
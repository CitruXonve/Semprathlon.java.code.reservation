/**
 * 2015��7��18�� ����9:55:52
 * PrjName:hdu5115
 * @ Semprathlon
 */
import java.io.*;
public class Main {
	final static long inf=0x7FFFFFFFFFFFFFFFL;
	static long min(long a,long b){
		return Math.min(a, b);
	}
	public static void main(String[] args)  throws IOException{
		// TODO Auto-generated method stub
		StreamTokenizer in=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out=new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		int T=(int)in.nval,cas=0;
		int[] a,b;
		long[][] f;
		while(T-->0){
			in.nextToken();
			int n=(int)in.nval;
			a=new int[n+2];
			b=new int[n+2];
			f=new long[n+2][n+2];
			for(int i=1;i<=n;i++){
				in.nextToken();
				a[i]=(int)in.nval;
			}
			for(int i=1;i<=n;i++){
				in.nextToken();
				b[i]=(int)in.nval;
			}
			f[1][1]=a[1]+b[2];
			f[n][n]=a[n]+b[n-1];
			for(int i=2;i<n;i++)
				f[i][i]=a[i]+b[i-1]+b[i+1];
			for(int i=1;i<=n;i++)
				for(int j=i+1;j<=n;j++)
					f[i][j]=inf;
			for(int j=0;j<=n;j++)
				for(int i=1;i+j<n+1;i++)
					for(int k=i;k<=i+j;k++){
						f[i][i+j]=min(f[i][i+j],f[i][k-1]+f[k+1][i+j]+a[k]+b[i-1]+b[i+j+1]);
						//out.print(f[i][i+j]+" ");
					}
			/*for(int i=0;i<=n+1;i++){out.println();
				for(int j=0;j<=n+1;j++)
					out.print(f[i][j]+"\t");	
			}*/
						
			
			/*for(int i=0;i<n;i++)
				for(int j=1;j<n-i;j++){
					if (j==1)
						min(f[j][j+i],f[j+1][j+i]+a[j]);
					if (j>1)
						min(f[j][j+i],f[j+1][j+i]+a[j]+b[j-1]);
					if (i+j<n)
						min(f[j][j+i],f[j][j+i-1]+a[j+i]+b[j+i+1]);
					if (i+j==n)
						min(f[j][j+i],f[j][j+i-1]+a[j+i]);
					for(int k=j+1;k<j+i;k++)
						min(f[j][j+i],f[j][k-1]+a[k]+f[k+1][j+i]);
				}*/
			out.println("Case #"+(++cas)+": "+f[1][n]);
			out.flush();
		}
		
		out.close();
	}

}

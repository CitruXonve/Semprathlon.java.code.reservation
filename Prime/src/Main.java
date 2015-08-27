import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import org.omg.PortableInterceptor.INACTIVE;

/** Aug 26, 2015 9:01:29 PM
 * PrjName:Prime
 * @author Semprathlon
 */

public class Main {
	static int maxn=40000001;
	static int[] pri,phi,fstp;
	static void get_prime(){
		pri=new int[maxn];
		fstp=new int[maxn];
		phi=new int[maxn];
		phi[1]=1;
		for(int i=2;i<maxn;i++){
			if (fstp[i]==0){
				pri[++pri[0]]=i;
				phi[i]=i-1;
			}
			for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){
				int k=i*pri[j];
				fstp[k]=pri[j];
				//if (fstp[i]==pri[j]){
				if (i%pri[j]==0){
					phi[k]=phi[i]*pri[j];
					break;
				}
				else
					phi[k]=phi[i]*(pri[j]-1);
			}
		}
	}
	static Vector<Integer> get_prime_factor(int n){
		Vector<Integer> res=new Vector<Integer>();
		int last=1;
		while(n>1&&fstp[n]>0){
			if (fstp[n]!=last)
				res.add(fstp[n]);
			last=fstp[n];
			n/=fstp[n];
		}
		if (n>1&&n!=last) res.add(n);
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		get_prime();
		Scanner sc=new Scanner(System.in);
		PrintWriter out=new PrintWriter(System.out);
		/*for(int i=2;i<101;i++){
			out.println(i+" "+phi[i]);
		}*/
		while(sc.hasNextInt()){
			Vector<Integer> v=get_prime_factor(sc.nextInt());
			for(int i=0;i<v.size();i++)
				out.print(v.get(i)+" ");
			out.println();
			out.flush();
		}
		out.close();
	}

}

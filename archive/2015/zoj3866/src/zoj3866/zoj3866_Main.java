package zoj3866;
import java.util.Scanner;

public class zoj3866_Main {
	final static double pi=Math.acos(-1.0);
	static double sqr(double x){
		return x*x;
	}
	static double cube(double x){
		return x*x*x;
	}
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		int T=scan.nextInt();
		while(T-->0){
			double r=scan.nextDouble();
			double h=scan.nextDouble();
			double d=scan.nextDouble();
			double V=4*pi*cube(d)/3.0+2*pi*d*sqr(r)+sqr(pi)*sqr(d)*r+pi*sqr(r+d)*h;
			double S=2*sqr(pi)*d*r+4*pi*sqr(d)+2*pi*sqr(r)+2*pi*(r+d)*h;
			System.out.println(String.format("%.9f", V)+" "+String.format("%.9f", S));
		}
	}
}

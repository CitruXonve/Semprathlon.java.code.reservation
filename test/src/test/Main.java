package test;
/**
 * 2015年7月27日 下午1:21:28
 * PrjName:test
 * @ Semprathlon
 */
import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
public class Main {
	static BigInteger mod=new BigInteger("9223372034707292160");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		BigInteger k=new BigInteger("7");
		for(int i=1;i<=n;i++){
			k=k.multiply(k).remainder(mod);
			System.out.println(k);
		}
	}

}

/**
 * @date 2015-05-30
 * @author Semprathlon
 */
import java.util.*;
import java.math.*;
 
public class Main {
    static int maxn=42;
    static BigInteger[] sum=new BigInteger[maxn];
     
    static int found_pow2(long n){
        return (int)(Math.log((double)n)/Math.log(2.0));
    }
 
    static void init(){
        for(int i=0;i<maxn;i++)
            sum[i]=new BigInteger("3").pow(i).add(new BigInteger("2"));
    }
    static BigInteger Bisearch(long l,long r,BigInteger ls,BigInteger rs,long key){
        while(l+1L<r){
            long mid=(l+r)>>1L;
            if(key<mid){
                r=mid;
                rs=ls.add(rs.subtract(ls).divide(new BigInteger("3")));
            }
            else{
                l=mid;
                ls=ls.add(rs.subtract(ls).divide(new BigInteger("3")));
            }
        }
        return ls;
    }
    static BigInteger solve(long n){
        if (n<0L) return BigInteger.ZERO;
        if (n==0L) return BigInteger.ONE;
        int k=found_pow2(n+1);
        BigInteger ls=sum[k];
        BigInteger rs=sum[k+1];
        return Bisearch(1L<<k,1L<<(k+1),ls,rs,n+1).subtract(BigInteger.valueOf(2));
    }
    public static void main(String[] args) {
        init();
        Scanner scan=new Scanner(System.in);
        while(scan.hasNextLong()){
            long n=scan.nextLong();
            long m=scan.nextLong();
            if (n==0L&&m==0L) break;
            System.out.println(solve(m).subtract(solve(n-1)));
        }
        scan.close();
    }
}
import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String[] args){
		BigInteger n;
		Scanner scan=new Scanner(System.in);
		BigInteger two=BigInteger.ONE.add(BigInteger.ONE);
		while(scan.hasNextBigInteger()){
			
			n=scan.nextBigInteger();
			System.out.println(n.multiply(n.add(BigInteger.ONE)).divide(two));
		}
	}
}

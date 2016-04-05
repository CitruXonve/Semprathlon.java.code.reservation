/**
 * Mar 28, 2016 9:49:24 PM
 * PrjName: acd1099
 * @semprathlon
 */

import java.util.*;
public class Main {
	static Heap h;
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		while(in.hasNext()){
			int n=in.nextInt();
			int k=in.nextInt();
			h=new Heap(k+1);
			for(int i=0;i<n;i++){
				h.push(in.nextInt());
				if (h.size()>k) h.pop();
			}
			System.out.println(h.top());
		}
	}

}


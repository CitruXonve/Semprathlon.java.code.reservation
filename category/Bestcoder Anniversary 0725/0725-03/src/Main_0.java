

/**
 * 2015年7月25日 下午8:24:01
 * PrjName:0725-03
 * @ Semprathlon
 */
public class Main_0 {
	static int[] f;
	static int maxn=1010;
	//static int maxn=100;
	static void init(){
		f=new int[maxn];
		for(int i=1;i<=6;i++) f[i]=i;
		for(int i=2;i<maxn;i++){
			int tmp=3*i*(i-1)+1;
			if (tmp>maxn) break;
			f[tmp]=1;
			f[tmp+1]=2;
		}
		for(int i=8;i<maxn;i++){
			if (f[i]>0) continue;
			f[i]=i;
			for(int j=1;j<i;j++){
				f[i]=Math.min(f[i],f[j]+f[i-j]);
				f[i]=Math.min(f[i],f[j]+i-j);
			}
		}
	}
	/*static int search(int n){
		int res=n;
		if (n<maxn) return f[n];
			if (f[i]>0) continue;
			f[i]=i;
			for(int j=1;j<i;j++){
				if (f[j]+f[i-j]<f[i])
					f[i]=f[j]+f[i-j];
			}
		}
		return res;
	}*/

}

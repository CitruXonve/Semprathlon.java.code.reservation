# About this reposition
Synchronization and backup

What all these projects are concerning about are the followings:

* Bestcoder weekly contest
* Multiple University training 2015 summer
* Program Practise from HDU

# Code Templates for ACM-ICPC
## InputAn "Inputreader" for common use:

```class InputReader{    public BufferedReader reader;    public StringTokenizer tokenizer;     public InputReader(InputStream stream){           reader = new BufferedReader(                   new InputStreamReader(stream), 32768);           tokenizer = null;    }     public String next(){        while(tokenizer == null || !tokenizer.hasMoreTokens()){            try{                tokenizer = new StringTokenizer(                           reader.readLine());            }catch (IOException e) {                throw new RuntimeException(e);            }        }        return tokenizer.nextToken();    }     public int nextInt() {        return Integer.parseInt(next());    }         public long nextLong() {        return Long.parseLong(next());    } }
```An enhanced edition supporting keeping reading data until the end of input while the number of input cases is unknown:

```class InputReader {	public BufferedReader reader;	public StringTokenizer tokenizer;	public InputReader(InputStream stream) {		reader = new BufferedReader(new InputStreamReader(stream), 32768);		tokenizer = null;	}	public String nextLine() {		String tmp = null;		try {			tmp = reader.readLine();			tokenizer = new StringTokenizer(tmp);		} catch (IOException e) {			throw new RuntimeException(e);		} catch (NullPointerException e) {			return null;		}		return tmp;	}	public String next() {		while (tokenizer == null || !tokenizer.hasMoreTokens()) {			try {				tokenizer = new StringTokenizer(reader.readLine());			} catch (IOException e) {				throw new RuntimeException(e);			}		}		return tokenizer.nextToken();	}	public int nextInt() {		return Integer.parseInt(next());	}	public long nextLong() {		return Long.parseLong(next());	}	public double nextDouble() {		return Double.parseDouble(next());	}}```##Data Structure###PointThe followings are used to describe a point on a plane,while the maximum value of x-coordinate or y-coordinate is `n-1`:

```class Pt{    int x,y,n;    Pt(int _x,int _y,int _n){        x=_x;y=_y;n=_n;    }    Pt(int hash,int _n){        n=_n;        y=hash%n;        x=hash/n;    }    public int hashCode(){        return x*n+y;    }}```###Queue

```Queue<Integer> que=new LinkedList<Integer>();
```###Priority Queue
```
PriorityQueue<Integer> que=new PriorityQueue<Integer>();
```###Heap
This is a minimum heap:

```class Heap {    private int maxn;    int[] data;    int r;     Heap(int size) {    	maxn=size;        data = new int[maxn];        r = 0;    }        void clear(){    	//Arrays.fill(data, 0);    	r=0;    }        public int size() {        return r;    }     void swap(int a, int b) {        int tmp = data[a];        data[a] = data[b];        data[b] = tmp;    }     void up(int p) {        if (!(p > 0))            return;        int q = p >> 1;        if (data[p] < data[q]) {            swap(p, q);            up(q);        }    }     void down(int p) {        int q;        if ((p << 1) >= r)            return;        else if ((p << 1) == r - 1) {            q = p << 1;        } else {q = (data[p << 1] < data[p << 1 | 1] ?     p << 1 : p << 1 | 1);        }        if (data[p] > data[q]) {            swap(p, q);            down(q);        }    }     void push(int n) {        data[r++] = n;        up(r - 1);    }     int pop() {        int res = data[0];        swap(0, r - 1);        r--;        down(0);        return res;    }     int top() {        return data[0];    }    }```###Adjacent Table
```class Edge{	int v,w,nx;	Edge(int _u,int _v,int _w){		v=_u;w=_v;nx=_w;	}}class Graph{	int[] h;	int sz;	Edge[] edge;	Graph(int size){		sz=size;		h=new int[sz+1];		edge=new Edge[sz+1];		Arrays.fill(h, -1);		h[0]=0;	}	void add(int u,int v,int w){		edge[h[0]]=new Edge(v,w,h[u]);		h[u]=h[0]++;	}}```#Search
```final int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};boolean can(int x,int y){		if (x<1||x>R||y<1||y>C) return false;		return true;	}```#Modify,Update & Query##RMQ```void RMQ(){ //pre-proceeding->O(nlogn){    for(int i = 1; i != maxm; ++i)        for(int j = 1; j <= n; ++j)            if(j + (1 << i) - 1 <= n){                maxsum[i][j] = Math.max(maxsum[i - 1][j],                             maxsum[i - 1][j + (1 << i >> 1)]);                minsum[i][j] = Math.min(minsum[i - 1][j],                             minsum[i - 1][j + (1 << i >> 1)]);                }    }     int query(int src,int des){        int k = (int)(Math.log(des - src + 1.0) / Math.log(2.0));        int maxres = Math.max(maxsum[k][src],                           maxsum[k][des - (1 << k) + 1]);        int minres = Math.min(minsum[k][src],                           minsum[k][des - (1 << k) + 1]);        return maxres-minres;    }```#Graph Theory##The Maximum Matching of Bipartite Graph
```boolean dfs(int u){        for(int v:adj[u]){            if (vis[v]) continue;            vis[v]=true;            if (match[v]<0||dfs(match[v])){                match[v]=u;                return true;            }        }        return false;    }int maxmatch(){        int res=0;        Arrays.fill(match, -1);        for(int i=1;i<=cnt;i++){            Arrays.fill(vis, false);            if (dfs(i)) res++;        }        return res;    }```#Number Theory##Quick power and moduloTo calculate n^m%mod:

```long pow(long n,long m,long mod){        long res=1L;        while(m>0L){            if ((m&1)>0L) res=res*n%mod;            n=n*n%mod;            m>>=1;        }        return res;    }
```
##Normal pow with double
Just to calculate n^m:

```static double pow(double n, int m) {		double res = 1;		while (m > 0) {			if ((m & 1) > 0)				res = res * n;			n = n * n;			m >>= 1;		}		return res;	}```##Prime filtering (linear) && Calculating Euler function φ(x)
```	int[] pri,phi,fstp;	void get_prime(){		pri=new int[maxn];		fstp=new int[maxn];		phi=new int[maxn];		phi[1]=1;		for(int i=2;i<maxn;i++){			if (fstp[i]==0){				pri[++pri[0]]=i;				phi[i]=i-1;			}			for(int j=1;j<=pri[0]&&i*pri[j]<maxn;j++){				int k=i*pri[j];				fstp[k]=pri[j];				//if (fstp[i]==pri[j]){				if (i%pri[j]==0){					phi[k]=phi[i]*pri[j];					break;				}				else					phi[k]=phi[i]*(pri[j]-1);			}		}	}	Vector<Integer> get_prime_factor(int n){		Vector<Integer> res=new Vector<Integer>();		while(n>1&&fstp[n]>0){			res.add(fstp[n]);			n/=fstp[n];		}		if (n>1) res.add(n);		return res;	}```##Integer Prime Factorization
for normal purpose:

```Vector<Integer> get_prime_factor(int n){        Vector<Integer> res=new Vector<Integer>();        res.clear();        for(int i=2;i*i<=n;i++)            if (n%i==0){                res.add(i);                while(n%i==0)                    n/=i;            }        if (n>1) res.add(n);        return res;    }
```
##Division and moduloTo calculate a/b%mod correctly (mod is a prime number thus φ(mod)=mod-1)

```long div(long a,long b,long mod){        return a*pow(b,mod-2,mod)%mod;    }
```#Combinatorial Mathematics##Permutation & Combination
```double[][] c;void init(){		c=new double[maxn][maxn];		c[0][0]=1;		for(int i=1;i<maxn;i++){			c[i][0]=c[i][i]=1;			for(int j=1;j<i;j++)				c[i][j]=c[i-1][j]+c[i-1][j-1];		}	}
```#Computational Geometry
##Basic Structure
```class Pt{	int x,y;	//Pt(){}	Pt(int _x,int _y){		this.x=_x;this.y=_y;	}}class Vc{	int a,b;	Vc(Pt c,Pt d){		a=d.x-c.x;		b=d.y-c.y;	}	static int dot(Vc v1,Vc v2){		return v1.a*v2.a+v1.b*v2.b;	}	static int cross(Vc v1,Vc v2){		return v1.a*v2.b-v2.a*v1.b;	}	double len(){		return Math.sqrt(a*a+b*b);	}	static double angle(Vc v1,Vc v2){		return dot(v1,v2)/v1.len()/v2.len();	}}```
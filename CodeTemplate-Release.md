![ACM-ICPC][1]

<h1 align="center">
  Java Code Template By Semprathlon
</h1>

*   部分理论知识引用自维基百科 *

<!--more-->

# Input 输入

An enhanced `InputReader` supporting keeping reading data until the end of input while the number of input cases is unknown:  
一个加强版的`输入器` ，支持读到输入文件末尾的方式，用法类似`java.util.Scanner`但效率显著提高:

<pre class="toolbar:2 wrap:true lang:java decode:true">class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public boolean hasNext() {
        return tokenizer != null && tokenizer.hasMoreTokens() || nextLine() != null;
    }

    public String nextLine() {
        String tmp = null;
        try {
            tmp = reader.readLine();
            tokenizer = new StringTokenizer(tmp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            return null;
        }
        return tmp;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}
</pre>

Keeping reading data until the end of input：  
读取数据直至文件末尾：

<pre class="toolbar:2 wrap:true lang:java decode:true " >while(in.hasNext()){
//TODO ...
}
</pre>

# Data Structure 数据结构篇

## Queue 队列

`Queue<Integer> que=new LinkedList<Integer>();`

## Priority Queue 优先队列

Please initialize it properly before using!  
使用时注意初始化！  
`PriorityQueue<Integer> que=new PriorityQueue<Integer>();`

## Heap 堆

This is a minimum heap:  
这是一个小根堆：

<pre class="toolbar:2 wrap:true lang:java decode:true " >class Heap {
    private int maxn;
    int[] data;
    int r;
 
    Heap(int size) {
        maxn=size;
        data = new int[maxn];
        r = 0;
    }
    
    void clear(){
        //Arrays.fill(data, 0);
        r=0;
    }
    
    public int size() {
        return r;
    }
 
    void swap(int a, int b) {
        int tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }
 
    void up(int p) {
        if (!(p &gt; 0))
            return;
        int q = p &gt;&gt; 1;
        if (data[p] &lt; data[q]) {
            swap(p, q);
            up(q);
        }
    }
 
    void down(int p) {
        int q;
        if ((p &lt;&lt; 1) &gt;= r)
            return;
        else if ((p &lt;&lt; 1) == r - 1) {
            q = p &lt;&lt; 1;
        } else {
q = (data[p &lt;&lt; 1] &lt; data[p &lt;&lt; 1 | 1] ? 
    p &lt;&lt; 1 : p &lt;&lt; 1 | 1);
        }
        if (data[p] &gt; data[q]) {
            swap(p, q);
            down(q);
        }
    }
 
    void push(int n) {
        data[r++] = n;
        up(r - 1);
    }
 
    int pop() {
        int res = data[0];
        swap(0, r - 1);
        r--;
        down(0);
        return res;
    }
 
    int top() {
        return data[0];
    }
    
}</pre>

## Adjacent Table 链式前向星（邻接表）

<pre class="toolbar:2 wrap:true lang:java decode:true " >class Edge{
    int v,w,nx;
    Edge(int _u,int _v,int _w){
        v=_u;w=_v;nx=_w;
    }
}
class Graph{
    int[] h;
    int sz;
    Edge[] edge;
    Graph(int size){
        sz=size;
        h=new int[sz+1];
        edge=new Edge[sz+1];
        Arrays.fill(h, -1);
        h[0]=0;
    }
    void add(int u,int v,int w){
        edge[h[0]]=new Edge(v,w,h[u]);
        h[u]=h[0]++;
    }
}
</pre>

# Modify,Update & Query 区间修改、更新与查询篇

## RMQ 区间最值查询（静态，离线）

<pre class="toolbar:2 wrap:true lang:java decode:true " >void RMQ(){ //initializing-&gt;O(nlogn){
    for(int i = 1; i != maxm; ++i)
        for(int j = 1; j &lt;= n; ++j)
            if(j + (1 &lt;&lt; i) - 1 &lt;= n){
                maxsum[i][j] = Math.max(maxsum[i - 1][j], 
                            maxsum[i - 1][j + (1 &lt;&lt; i &gt;&gt; 1)]);
                minsum[i][j] = Math.min(minsum[i - 1][j], 
                            minsum[i - 1][j + (1 &lt;&lt; i &gt;&gt; 1)]);
                }
    }
     
int query(int src,int des){
        int k = (int)(Math.log(des - src + 1.0) / Math.log(2.0));
        int maxres = Math.max(maxsum[k][src], 
                          maxsum[k][des - (1 &lt;&lt; k) + 1]);
        int minres = Math.min(minsum[k][src], 
                          minsum[k][des - (1 &lt;&lt; k) + 1]);
        return maxres-minres;
    }
</pre>

## Segment Tree 线段树

Suppose there are $latex n$ distinct integers ranging from $latex 1$ to $latex n$,and the $latex k$th largest one is required.  
假设有范围在$latex [1,n]$内的$latex n$个不同整数，求第$latex k$大的数。

<pre class="toolbar:2 wrap:true lang:java decode:true " >class ST {
    int[] l, r, m, v;
    int sz;

    ST() {
    }

    ST(int _sz) {
        sz = _sz &lt;&lt; 2;
        l = new int[sz];
        r = new int[sz];
        m = new int[sz];
        v = new int[sz];
    }

    void build(int k, int x, int y) {
        l[k] = x;
        r[k] = y;
        m[k] = (x + y) &gt;&gt; 1;
        if (x &lt; y) {
            build(k &lt;&lt; 1, x, m[k]);
            build(k &lt;&lt; 1 | 1, m[k] + 1, y);
        }
        v[k] = y - x + 1;
    }

    int query(int k, int x, int y, int q) {
        if (l[k] == r[k]) {
            v[k] = 0;
            return l[k];
        }
        int res = 0;
        if (v[k &lt;&lt; 1 | 1] &gt;= q)
            res = query(k &lt;&lt; 1 | 1, m[k] + 1, y, q);
        else
            res = query(k &lt;&lt; 1, x, m[k], q - v[k &lt;&lt; 1 | 1]);
        v[k] = v[k &lt;&lt; 1] + v[k &lt;&lt; 1 | 1];
        return res;
    }

}</pre>

## Binary Indexed Tree 树状数组

Suppose there are n distinct integers ranging from $latex 1$ to $latex n$,and the $latex k$th largest one is required(use `query(k-1)`).  
假设有范围在$latex [1,n]$内的$latex n$个不同整数，求第$latex k$大的数（调用`query(k-1)`）。

<pre class="toolbar:2 wrap:true lang:java decode:true " >class BIT {
    int[] data;
    int sz;

    BIT() {
    }

    BIT(int _sz) {
        sz = _sz;
        data = new int[sz + 1];
    }

    int lowbit(int x) {
        return x & (-x);
    }

    void add(int p, int v) {
        while (p &lt;= sz) {
            data[p] += v;
            p += lowbit(p);
        }
    }

    int sum(int p) {
        int res = 0;
        while (p &gt; 0) {
            res += data[p];
            p -= lowbit(p);
        }
        return res;
    }

    int find(int p) {
        int l = 1, r = sz;
        while (l &lt; r) {
            int mid = (l + r) &gt;&gt; 1;
            if (sum(mid) &lt;= p)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }
}</pre>

# Graph Theory 图论篇

## Topological Sorting 拓扑排序

<pre class="toolbar:2 wrap:true lang:java decode:true " >int[] degree;
boolean[][] arc;
Vector&lt;Integer&gt; ans;
PriorityQueue&lt;Integer&gt; que;

void Topo(int n) {
        for (int u = 1; u &lt;= n; u++)
            if (degree[u] == 0) {
                que.add(u);
            }
        while (!que.isEmpty()) {
            int u = que.poll();
            ans.add(u);
            for (int v = 1; v &lt;= n; v++) {
                if (arc[u][v]) {
                    degree[v]--;
                    if (degree[v] == 0) {
                        que.add(v);
                    }
                }
            }
        }
}</pre>

## Hungarian Algorithm 二分图最大匹配 - 匈牙利算法

<pre class="toolbar:2 wrap:true lang:java decode:true " >boolean dfs(int u){
        for(int v:adj[u]){
            if (vis[v]) continue;
            vis[v]=true;
            if (match[v]&lt;0||dfs(match[v])){
                match[v]=u;
                return true;
            }
        }
        return false;
    }
int maxmatch(){
        int res=0;
        Arrays.fill(match, -1);
        for(int i=1;i&lt;=cnt;i++){
            Arrays.fill(vis, false);
            if (dfs(i)) res++;
        }
        return res;
    }</pre>

# Number Theory 数论篇

## Extended Euclid Theorem 扩展欧几里得定理

Suppose $latex ax+by=gcd(a,b) $,and the value of $latex x $ and of $latex y $ are required.  
已知$latex ax+by=gcd(a,b) $，求$latex x$与$latex y$的值。  
注意$latex x$与$latex y$中很可能有一个是负整数。

<pre class="toolbar:2 wrap:true lang:java decode:true " >long x,y;
void extgcd(long a, long b) {
        if (b == 0L) {
            x = 1L;
            y = 0L;
            return;
        }
        extgcd(b, a % b);
        long t = x;
        x = y;
        y = t - a / b * y;
}
</pre>

## Modular multiplicative inverse 模逆元

$latex a^{-1} \equiv b \pmod{n},a \cdot a^{-1} \equiv 1.$

The modular multiplicative inverse of $latex a$ modulo $latex m$ can be found with the extended Euclidean algorithm.  
设`exdgcd(a,n)`为扩展欧几里得算法的函数，则可得到$latex ax+ny=gcd(a,n)$.  
若$latex g=1$，则该模逆元存在，根据结果$latex ax+ny=1$.  
在$latex \mod{n}$之下，$latex ax+ny \equiv ax \equiv 1$，根据模逆元的定义，此时$latex x$即为$latex a$关于模$latex n$的其中一个模逆元。  
事实上，$latex x+kn(k \in \mathbb{Z}) $都是$latex a$关于模$latex n$的模逆元，这里取最小的正整数解$latex x\mod{n}(x<n)$。  
若$latex g\ne 1$，则该模逆元不存在。

<pre class="toolbar:2 wrap:true lang:java decode:true " >long cal_inv(long n, long mod) {
        extgcd(n, mod);
        return x &lt; 0L ? (x + mod) % mod : x % mod;
}</pre>

According to Euler's theorem, if $latex a$ is coprime to $latex m$, that is, $latex gcd(a, m) = 1$, then $latex a^φ(m)≡1\pmod{m}$,where $latex φ(m)$ is Euler's totient function.  
This follows from the fact that a belongs to the multiplicative group $latex (Z/mZ)^×$ iff a is coprime to m. Therefore the modular multiplicative inverse can be found directly:  
$latex a^{φ(m)-1}≡a^{-1}\pmod{m}$.  
In the special case when $latex m$ is a prime, the modular inverse is given by the below equation as: $latex a^{-1}≡a^{m-2}\pmod{m}$.  
欧拉函数求单个逆元：

<pre class="toolbar:2 wrap:true lang:java decode:true " >long cal_inv(long n, long mod) {
        return pow_mod(n, phi[mod] - 1, mod);
}</pre>

通过递推的方式，在线性时间复杂度内求出若干个逆元：

<pre class="toolbar:2 wrap:true lang:java decode:true " >void cal_inv(int maxn, long mod) {
        inv[1] = 1;
        for (int i = 2; i &lt; maxn; i++)
            inv[i] = (mod - mod / i) * inv[(int) (mod % i)] % mod;
}</pre>

## Quick power and modulo 快速幂取模

To calculate $latex n^m\%mod $:  
计算$latex n^m\%mod $：

<pre class="toolbar:2 wrap:true lang:java decode:true " >long pow_mod(long n, long m, long mod) {
        long res = 1L;
        n %= mod;
        while (m &gt; 0L) {
            if ((m & 1L) &gt; 0L)
                res = res * n % mod;
            n = n * n % mod;
            m &gt;&gt;= 1;
        }
        return res;
}</pre>

## Multiply and modulo 乘法取模

To calculate $latex (nm)\% mod $:  
在$latex n$和$latex m$的值都较大，直接相乘会溢出的情况下，计算$latex (nm)\% mod $：

<pre class="toolbar:2 wrap:true lang:java decode:true " >long mul_mod(long n, long m, long mod) {
        long ans = 0L;
        n %= mod;
        while (m &gt; 0L) {
            if ((m & 1L) &gt; 0L)
                ans = (ans + n) % mod;
            m &gt;&gt;= 1;
            n = (n + n) % mod;
        }
        return ans;
}</pre>

## Division and modulo 除法取模

To calculate $latex n/m\%mod $ correctly ($latex mod$ is a prime number thus $latex φ(mod)=mod-1 $).  
利用欧拉函数计算$latex n/m\%mod $（当$latex mod$是一个质数时有$latex φ(mod)=mod-1 $）：

<pre class="toolbar:2 wrap:true lang:java decode:true " >long div_mod(long n, long m, long mod) {
        return n * pow_mod(m, mod - 2, mod) % mod;
        // return n * pow_mod(m, phi(mod) - 1, mod) % mod;
}</pre>

In other words,use modular multiplicative inverse.  
或者直接使用模逆元：

<pre class="toolbar:2 wrap:true lang:java decode:true " >long div_mod(long n, long m, long mod) {
        return n * inv[(int) m] % mod;
}</pre>

## Factorial and modulo 阶乘取模

<pre class="toolbar:2 wrap:true lang:java decode:true " >void Get_Fac(long n, long mod) {
        fac[0] = 1;
        for (int i = 1; i &lt;= n; i++) {
            fac[i] = fac[i - 1] * i;
            fac[i] %= mod;
        }
}</pre>

## Prime filtering (linear) 线性筛质数

<pre class="toolbar:2 wrap:true lang:java decode:true " >int[] pri,fstp;
    void filter_prime(){
        pri=new int[maxn];
        fstp=new int[maxn];
        for(int i=2;i&lt;maxn;i++){
            if (fstp[i]==0){
                pri[++pri[0]]=i;
            }
            for(int j=1;j&lt;=pri[0]&&i*pri[j]&lt;maxn;j++){
                int k=i*pri[j];
                fstp[k]=pri[j];
            }
        }
    }</pre>

## Calculating Euler function $latex φ(n) $ 求欧拉函数值

<pre class="toolbar:2 wrap:true lang:java decode:true " >int[] pri,phi,fstp;
    void cal_euler(){
        pri=new int[maxn];
        fstp=new int[maxn];
        phi=new int[maxn];
        phi[1]=1;
        for(int i=2;i&lt;maxn;i++){
            if (fstp[i]==0){
                pri[++pri[0]]=i;
                phi[i]=i-1;
            }
            for(int j=1;j&lt;=pri[0]&&i*pri[j]&lt;maxn;j++){
                int k=i*pri[j];
                fstp[k]=pri[j];
                if (i%pri[j]==0){
                    phi[k]=phi[i]*pri[j];
                    break;
                }
                else{
                    phi[k]=phi[i]*(pri[j]-1);
                }
            }
        }
    }</pre>

## Calculating Möbius function $latex μ(n) $ 求莫比乌斯函数值

<pre class="toolbar:2 wrap:true lang:java decode:true " >int[] pri,fstp,miu;
    void cal_euler(){
        pri=new int[maxn];
        fstp=new int[maxn];
        miu=new int[maxn];
        miu[1]=1;
        for(int i=2;i&lt;maxn;i++){
            if (fstp[i]==0){
                pri[++pri[0]]=i;
                miu[i]=-1;
            }
            for(int j=1;j&lt;=pri[0]&&i*pri[j]&lt;maxn;j++){
                int k=i*pri[j];
                fstp[k]=pri[j];
                if (i%pri[j]==0){
                    miu[k]=0;
                    break;
                }
                else{
                    miu[k]=-miu[i];
                }
            }
        }
    }</pre>

## Integer Prime Factorization 分解质因数

<pre class="toolbar:2 wrap:true lang:java decode:true " >Vector&lt;Integer&gt; get_prime_factor(int n){
        Vector&lt;Integer&gt; res=new Vector&lt;Integer&gt;();
        res.clear();
        for(int i=2;i*i&lt;=n;i++)
            if (n%i==0){
                res.add(i);
                while(n%i==0)
                    n/=i;
            }
        if (n&gt;1) res.add(n);
        return res;
    }

</pre>

## Quick Greatest Common Divisor 快速求最大公约数

<pre class="toolbar:2 wrap:true lang:java decode:true " >int kgcd(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        if ((a & 1) == 0 && (b & 1) == 0)
            return kgcd(a &gt;&gt; 1, b &gt;&gt; 1) &lt;&lt; 1;
        else if ((b & 1) == 0)
            return kgcd(a, b &gt;&gt; 1);
        else if ((a & 1) == 0)
            return kgcd(a &gt;&gt; 1, b);
        else
            return kgcd(Math.abs(a - b), Math.min(a, b));
}</pre>

## Chinese Remainer Theorem 中国剩余定理

Resolving $latex \begin{cases} x ≡a_1 \pmod{m_1}, \newline x ≡a_2 \pmod{m_2}, \newline …, \newline x ≡a_n \pmod{m_n}. \end{cases} $.  
解同余方程组$latex \begin{cases} x ≡a_1 \pmod{m_1}, \newline x ≡a_2 \pmod{m_2}, \newline …, \newline x ≡a_n \pmod{m_n}. \end{cases} $。

<pre class="toolbar:2 wrap:true lang:java decode:true " >long CRT(long n, long[] a, long[] m) {
        long pro = 1L, res = 0L;
        for (int i = 0; i &lt; n; i++)
            pro *= m[i];
        for (int i = 0; i &lt; n; i++) {
            long w = pro / m[i];
            extgcd(m[i], w);
            res = (res + mul_mod(y, mul_mod(w, a[i], pro), pro)) % pro;
        }
        return (res + pro) % pro;
}</pre>

# Combinatorial Mathematics 组合数学篇

## Combination Calculation #1 组合数计算1

$latex C_n^m=C_{n-1}^m+C_{n-1}^{m-1}$.

<pre class="toolbar:2 wrap:true lang:java decode:true " >double[][] c;
void init(){
        c=new double[maxn][maxn];
        c[0][0]=1;
        for(int i=1;i&lt;maxn;i++){
            c[i][0]=c[i][i]=1;
            for(int j=1;j&lt;i;j++)
                c[i][j]=c[i-1][j]+c[i-1][j-1];
        }
}</pre>

## Combination Calculation #2 组合数计算2

It is guaranteed that $latex n≥m$,but $latex n!$ should be computed in advance.  
在$latex n≥m$且$latex n!$已求出的前提下，单个计算$latex C_n^m$：

<pre class="toolbar:2 wrap:true lang:java decode:true " >long C(long n, long m, long mod) {
        int a = (int) (n % mod), b = (int) (m % mod);
        return div_mod(fac[a], mul_mod(fac[a - b], fac[b], mod), mod);
}</pre>

## Lucas Theorem 卢卡斯定理

`C(n, m, mod)` refers to $latex C_n^m\%mod $.  
`C(n, m, mod)`表示$latex C_n^m\%mod $。

<pre class="toolbar:2 wrap:true lang:java decode:true " >long Lucas(long n, long m, long mod) {
        long ret = 1L;
        while (n &gt; 0L && m &gt; 0L) {
            if (n % mod &lt; m % mod)
                return 0L;
            ret = mul_mod(ret, C(n, m, mod), mod);
            ret %= mod;
            n /= mod;
            m /= mod;
        }
        return ret;
}
</pre>

## Catalan number 卡塔兰数

$latex C_n = {2n\choose n} - {2n\choose n+1} = {1\over n+1}{2n\choose n} \quad\text{ for }n\ge 0$;  
The Catalan numbers satisfy the recurrence relation $latex C_0 = 1 \quad \mbox{and} \quad C_{n+1}=\sum_{i=0}^{n}C_i\,C_{n-i}\quad\text{for }n\ge 0$;  
moreover,$latex C_n= \frac 1{n+1} \sum_{i=0}^n {n \choose i}^2$.  
Asymptotically, the Catalan numbers grow as $latex C_n \sim \frac{4^n}{n^{3/2}\sqrt{\pi}}$.  
组合数学中有非常多的组合结构可以用卡塔兰数来计数。

*   $latex C_n$表示长度2n的dyck word的个数。Dyck word是一个有n个X和n个Y组成的字串，且所有的前缀字串皆满足X的个数大于等于Y的个数。以下为长度为6的dyck words:XXXYYY XYXXYY XYXYXY XXYYXY XXYXYY 
*   将上例的X换成左括号，Y换成右括号，$latex C_n$表示所有包含n组括号的合法运算式的个数：((())) ()(()) ()()() (())() (()()) 
*   $latex C_n$表示有n个节点组成不同构二叉树的方案数。 
*   $latex C_n$表示有2n+1个节点组成不同构满二叉树（full binary tree）的方案数。 

> 证明：  
> 令1表示进栈，0表示出栈，则可转化为求一个2n位、含n个1、n个0的二进制数，满足从左往右扫描到任意一位时，经过的0数不多于1数。显然含n个1、n个0的2n位二进制数共有$latex {2n \choose n}$个，下面考虑不满足要求的数目。  
> 考虑一个含n个1、n个0的2n位二进制数，扫描到第2m+1位上时有m+1个0和m个1（容易证明一定存在这样的情况），则后面的0-1排列中必有n-m个1和n-m-1个0。将2m+2及其以后的部分0变成1、1变成0，则对应一个n+1个0和n-1个1的二进制数。反之亦然（相似的思路证明两者一一对应）。  
> 从而$latex C_n = {2n \choose n} - {2n \choose n + 1} = \frac{1}{n+1}{2n \choose n}$。证毕。

*   $latex C_n$表示所有在n × n格点中不越过对角线的单调路径的个数。一个单调路径从格点左下角出发，在格点右上角结束，每一步均为向上或向右。计算这种路径的个数等价于计算Dyck word的个数：X代表“向右”，Y代表“向上”。
*   $latex C_n$表示通过连结顶点而将n + 2边的凸多边形分成三角形的方法个数。 
*   $latex C_n$表示对{1, ..., n}依序进出栈的置换个数。一个置换w是依序进出栈的当S(w) = (1, ..., n),其中S（w）递归定义如下：令w = unv，其中n为w的最大元素，u和v为更短的数列；再令S(w) = S(u)S(v)n，其中S为所有含一个元素的数列的单位元。
*   $latex C_n$表示集合{1, ..., n}的不交叉划分的个数.那么, Cn永远不大于第n项贝尔数. Cn也表示集合{1, ..., 2n}的不交叉划分的个数，其中每个段落的长度为2。
*   $latex C_n$表示用n个长方形填充一个高度为n的阶梯状图形的方法个数。
*   $latex C_n$表示表为2×n的矩阵的标准杨氏矩阵的数量。 也就是说，它是数字 1, 2, ..., 2n 被放置在一个2×n的矩形中并保证每行每列的数字升序排列的方案数。同样的，该式可由勾长公式的一个特殊情形推导得出。 

本例中使用递推公式$latex C_1=1,C_n=\frac{(4n-2)}{(n+1)} \cdot C_{n-1}$;

<pre class="toolbar:2 wrap:true lang:java decode:true " >long C[];
void get_Catalan(int maxn) {
        C[1] = 1;
        for (int i = 2; i &lt; maxn - 1; i++) {
            C[i] = C[i - 1] * ((i &lt;&lt; 2) - 2) % mod;
            C[i] = div_mod(C[i], i + 1, mod);
        }
}</pre>

# Computational Geometry 计算几何篇

## Double Comparing 实数的比较

<pre class="toolbar:2 wrap:true lang:java decode:true " >final double eps = 1e-3;
int dcmp(double d) {
        if (Math.abs(d) &lt; eps)
            return 0;
        return d &gt; 0 ? 1 : -1;
}</pre>

## Platform 几何类框架

### Point 点

<pre class="toolbar:2 wrap:true lang:java decode:true " >class Point {
    double x, y;

    Point() {
    }

    Point(double _x, double _y) {
        x = _x;
        y = _y;
    }

    Point(Point p) {
        this(p.x, p.y);
    }

    static class Comp implements Comparator&lt;Point&gt; {
        Point prep;

        Comp(Point p) {
            prep = p;
        }

        public int compare(Point a, Point b) {
            double tmp = Point.cross(prep, a, b);
            if (dcmp(tmp) == 0)
                return -dcmp(dist(a, prep) - dist(b, prep));
            return -dcmp(tmp);
        }
    }</pre>

### Vector 向量

<pre class="toolbar:2 wrap:true lang:java decode:true " >class Vector extends Point {
    Vector() {
    }

    Vector(double _x, double _y) {
        x = _x;
        y = _y;
    }

    Vector(Point a, Point b) {
        this(b.x - a.x, b.y - a.y);
    }

    Vector(Point p) {
        this(p.x, p.y);
    }}</pre>

### Line 直线/线段

<pre class="toolbar:2 wrap:true lang:java decode:true " >class Line extends Vector {
    Point s, e;

    Line() {
    }

    Line(Point _s, Point _e) {
        s = _s;
        e = _e;
    }

    Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    Vector vector() {
        return new Vector(s, e);
    }

}</pre>

### Polygon 多边形

<pre class="toolbar:2 wrap:true lang:java decode:true " >class Polygon extends Vector {
    int num;
    Point[] v;

    Polygon() {
    }

    Polygon(int n) {
        num = n;
        v = new Point[num + 2];
   </pre>

### Round 圆

<pre class="toolbar:2 wrap:true lang:java decode:true " >class Round extends Vector {
    double r;
    Point o;

    Round(double _r, double _x, double _y) {
        r = _r;
        x = _x;
        y = _y;
        o = new Point(x, y);
    }

    double Area() {
        return pi * r * r;
    }
}</pre>

## Function 函数计算

### Arithmetic 代数运算

<pre class="toolbar:2 wrap:true lang:java decode:true " >double pi = Math.acos(-1.0);

    double Rad2Deg(double rad) {
        return rad * 180.0 / pi;
    }

    double Deg2Rad(double deg) {
        return deg * pi / 180.0;
    }

    double dist(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    double angle(Vector a, Vector b) {
        return Math.acos(dot(a, b) / a.length() / b.length());
    }

    double angle(Point a, Point b) {
        return angle(new Vector(a), new Vector(b));
    }

    double angle(Point o, Point a, Point b) {
        return angle(new Vector(o, a), new Vector(o, b));
    }

    double dot(Vector r) {
        return x * r.x + y * r.y;
    }

    double cross(Vector r) {
        return x * r.y - y * r.x;
    }

    double length() {
        return Math.sqrt(this.dot(this));
    }

    public boolean equals(Point p) {
        return dcmp(Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y))) == 0;
    }

    double CommonArea(Round A, Round B) {
        double area = 0.0;
        Round M = dcmp(A.r - B.r) &gt; 0 ? A : B;
        Round N = dcmp(A.r - B.r) &gt; 0 ? B : A;
        double D = dist(M.o, N.o);
        if (dcmp(M.r + N.r - D) &gt; 0 && dcmp(M.r - N.r - D) &lt; 0) {
            double cosM = (M.r * M.r + D * D - N.r * N.r) / (2.0 * M.r * D);
            double cosN = (N.r * N.r + D * D - M.r * M.r) / (2.0 * N.r * D);
            double alpha = 2.0 * Math.acos(cosM);
            double beta = 2.0 * Math.acos(cosN);

            double TM = 0.5 * M.r * M.r * Math.sin(alpha);
            double TN = 0.5 * N.r * N.r * Math.sin(beta);
            double FM = 0.5 * alpha / pi * M.Area();
            double FN = 0.5 * beta / pi * N.Area();
            area = FM + FN - TM - TN;
        } else if (dcmp(M.r - N.r - D) &gt;= 0) {
            area = N.Area();
        }
        return area;
    }

    double TriAngleCircleInsection(Round C, Point A, Point B) {
        Vector OA = new Vector(A.sub(C.o)), OB = new Vector(B.sub(C.o));
        Vector BA = new Vector(A.sub(B)), BC = new Vector(C.o.sub(B));
        Vector AB = new Vector(B.sub(A)), AC = new Vector(C.o.sub(A));
        double DOA = OA.length(), DOB = OB.length(), DAB = AB.length(), r = C.r;
        if (dcmp(cross(OA, OB)) == 0)
            return 0;
        if (dcmp(DOA - C.r) &lt; 0 && dcmp(DOB - C.r) &lt; 0)
            return cross(OA, OB) * 0.5;
        else if (dcmp(DOB - r) &lt; 0 && dcmp(DOA - r) &gt;= 0) {
            double x = (dot(BA, BC) + Math.sqrt(r * r * DAB * DAB - cross(BA, BC) * cross(BA, BC))) / DAB;
            double TS = cross(OA, OB) * 0.5;
            return Math.asin(TS * (1 - x / DAB) * 2 / r / DOA) * r * r * 0.5 + TS * x / DAB;
        } else if (dcmp(DOB - r) &gt;= 0 && dcmp(DOA - r) &lt; 0) {
            double y = (dot(AB, AC) + Math.sqrt(r * r * DAB * DAB - cross(AB, AC) * cross(AB, AC))) / DAB;
            double TS = cross(OA, OB) * 0.5;
            return Math.asin(TS * (1 - y / DAB) * 2 / r / DOB) * r * r * 0.5 + TS * y / DAB;
        } else if (dcmp(Math.abs(cross(OA, OB)) - r * DAB) &gt;= 0 || dcmp(dot(AB, AC)) &lt;= 0 || dcmp(dot(BA, BC)) &lt;= 0) {
            if (dcmp(dot(OA, OB)) &lt; 0) {
                if (dcmp(cross(OA, OB)) &lt; 0)
                    return (-Math.acos(-1.0) - Math.asin(cross(OA, OB) / DOA / DOB)) * r * r * 0.5;
                else
                    return (Math.acos(-1.0) - Math.asin(cross(OA, OB) / DOA / DOB)) * r * r * 0.5;
            } else
                return Math.asin(cross(OA, OB) / DOA / DOB) * r * r * 0.5;
        } else {
            double x = (dot(BA, BC) + Math.sqrt(r * r * DAB * DAB - cross(BA, BC) * cross(BA, BC))) / DAB;
            double y = (dot(AB, AC) + Math.sqrt(r * r * DAB * DAB - cross(AB, AC) * cross(AB, AC))) / DAB;
            double TS = cross(OA, OB) * 0.5;
            return (Math.asin(TS * (1 - x / DAB) * 2 / r / DOA) + Math.asin(TS * (1 - y / DAB) * 2 / r / DOB)) * r * r
                    * 0.5 + TS * ((x + y) / DAB - 1);
        }
    }</pre>

### Geometry 几何运算

<pre class="toolbar:2 wrap:true lang:java decode:true " >Point add(Point r) {
        return new Point(x + r.x, y + r.y);
    }

    Point sub(Point r) {
        return new Point(x - r.x, y - r.y);
    }

    Point mul(double r) {
        return new Point(x * r, y * r);
    }

    Point move(double dx, double dy) {
        return new Point(x + dx, y + dy);
    }

    Point rotate(double a) {
        return new Point(x * Math.cos(a) - y * Math.sin(a), x * Math.sin(a) + y * Math.cos(a));
    }

    Point rotate(double dx, double dy, double a) {
        return this.move(-dx, -dy).rotate(a).move(dx, dy);
    }

    Point mid(Point a, Point b) {
        return new Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);
    }

    Vector normal() {
        double len = this.length();
        return new Vector(-y / len, x / len);
    }

    Point GetLineIntersection(Point p, Vector v, Point q, Vector w) {
        Vector u = new Vector(p.sub(q));
        double t = cross(w, u) / cross(v, w);
        return p.add(v.mul(t));
    }

    Point GetLineIntersection(Point p, Point v, Point q, Point w) {
        return GetLineIntersection(p, new Vector(v), q, new Vector(w));
    }</pre>

### Judge 关系判断

<pre class="toolbar:2 wrap:true lang:java decode:true " >boolean isLineInter(Line l1, Line l2) {
        if (l1.s.equals(l1.e) || l2.s.equals(l2.e))
            return false;
        return dcmp(cross(l2.s, l1.s, l1.e) * cross(l2.e, l1.s, l1.e)) &lt;= 0;
    }

    boolean isSegInter(Point s1, Point e1, Point s2, Point e2) {
        if (dcmp(Math.min(s1.x, e1.x) - Math.max(s2.x, e2.x)) &lt;= 0
                && dcmp(Math.min(s1.y, e1.y) - Math.max(s2.y, e2.y)) &lt;= 0
                && dcmp(Math.min(s2.x, e2.x) - Math.max(s1.x, e1.x)) &lt;= 0
                && dcmp(Math.min(s2.y, e2.y) - Math.max(s1.y, e1.y)) &lt;= 0
                && dcmp(Vector.cross(s2, e2, s1) * Vector.cross(s2, e2, e1)) &lt;= 0
                && dcmp(Vector.cross(s1, e1, s2) * Vector.cross(s1, e1, e2)) &lt;= 0)
            return true;
        return false;
    }

    boolean isSegInter2(Point p1, Point p2, Point p3, Point p4) // vigorous intersection
    {
        return dcmp(cross(p3, p4, p1)) * dcmp(cross(p3, p4, p2)) == -1;
    }

    boolean isSegInter(Line l1, Line l2) {
        return isSegInter(l1.s, l1.e, l2.s, l2.e);
    }

    boolean isSegInter2(Line l1, Line l2) {
        return isSegInter2(l1.s, l1.e, l2.s, l2.e);
    }

    boolean IsConvexBag() {
        int direction = 0;// 1:counter-clockwise -1:clockwise
        for (int i = 0; i &lt; num; i++) {
            int tmp = dcmp(cross(v[i], v[i + 1], v[i + 1], v[i + 2]));

            if (direction == 0) // prevent co-line
                direction = tmp;

            if (direction * tmp &lt; 0) // as Vec is a ConvexBag,direction*temp&gt;=0 no matter how direction rotates
                return false;
        }
        return true;
    }

    boolean IsInPoly(Polygon pl) {
        double CircleAngle = 0.0; // rotation angle
        for (int i = 1; i &lt;= pl.num; i++) // ignore the repetitive edges
            if (dcmp(cross(o, pl.v[i], o, pl.v[i + 1])) &gt;= 0)
                CircleAngle += angle(o, pl.v[i], pl.v[i + 1]);
            else
                CircleAngle -= angle(o, pl.v[i], pl.v[i + 1]);

        if (dcmp(CircleAngle) == 0) // CircleAngle=0, Peg outside
            return false;
        else if (dcmp(CircleAngle - pi) == 0 || dcmp(CircleAngle + pi) == 0) // CircleAngle=180,
                                                                                // Peg inside(excluding vertices)
        {
            if (dcmp(r) == 0)
                return true;
        } else if (dcmp(CircleAngle - 2 * pi) == 0 || dcmp(CircleAngle + 2 * pi) == 0) // CircleAngle=360, Peg inside
            return true;
        else // CircleAngle in range (0,360)， Peg on the vertex
        {
            if (dcmp(r) == 0)
                return true;
        }
        return false;
    }

    boolean IsFitPoly(Polygon pl) {
        for (int i = 0; i &lt;= pl.num; i++) {
            int k = dcmp(Math.abs(cross(o, pl.v[i], o, pl.v[i + 1]) / dist(pl.v[i], pl.v[i + 1])) - r);
            if (k &lt; 0)
                return false;
        }
        return true;
    }</pre>

# String 字符串篇

## The Knuth-Morris-Pratt Algorithm KMP算法

<pre class="toolbar:2 wrap:true lang:java decode:true " >class KMP {
    static int next[];

    static void getNext(String T) {
        next = new int[T.length() + 1];
        int j = 0, k = -1;
        next[0] = -1;
        while (j &lt; T.length())
            if (k == -1 || T.charAt(j) == T.charAt(k))
                next[++j] = ++k;
            else
                k = next[k];
    }

    static int Index(String S, String T) {
        int i = 0, j = 0;
        getNext(T);

        for (i = 0; i &lt; S.length() && j &lt; T.length(); i++) {
            while (j &gt; 0 && S.charAt(i) != T.charAt(j))
                j = next[j];
            if (S.charAt(i) == T.charAt(j))
                j++;
        }
        if (j == T.length())
            return i - T.length();
        else
            return -1;
    }

    static int Count(String S, String T) {
        int res = 0, j = 0;
        if (S.length() == 1 && T.length() == 1) {
            if (S.charAt(0) == T.charAt(0))
                return 1;
            else
                return 0;
        }
        getNext(T);
        for (int i = 0; i &lt; S.length(); i++) {
            while (j &gt; 0 && S.charAt(i) != T.charAt(j))
                j = next[j];
            if (S.charAt(i) == T.charAt(j))
                j++;
            if (j == T.length()) {
                res++;
                j = next[j];
            }
        }
        return res;
    }
}</pre>

## Aho-Corasick automaton AC自动机

<pre class="toolbar:2 wrap:true lang:java decode:true " >class AC {
    final int maxl = 500010, maxc = 26;
    final char fstc = 'a';
    int root, L;
    int[][] next;
    int[] fail, end;
    Queue&lt;Integer&gt; que = new LinkedList&lt;Integer&gt;();

    AC() {
        next = new int[maxl][maxc];
        fail = new int[maxl];
        end = new int[maxl];
        L = 0;
        root = newnode();
    }

    void clear() {
        Arrays.fill(fail, 0);
        Arrays.fill(end, 0);
        L = 0;
        root = newnode();
    }

    int newnode() {
        Arrays.fill(next[L], -1);
        end[L++] = 0;
        return L - 1;
    }

    void insert(String str) {
        int now = root;
        for (int i = 0; i &lt; str.length(); i++) {
            char ch = str.charAt(i);
            if (next[now][ch - fstc] == -1)
                next[now][ch - fstc] = newnode();
            now = next[now][ch - fstc];
        }
        end[now]++;
    }

    void build() {
        que.clear();
        fail[root] = root;
        for (int i = 0; i &lt; maxc; i++)
            if (next[root][i] == -1)
                next[root][i] = root;
            else {
                fail[next[root][i]] = root;
                que.add(next[root][i]);
            }
        while (!que.isEmpty()) {
            int now = que.poll();
            for (int i = 0; i &lt; maxc; i++)
                if (next[now][i] == -1)
                    next[now][i] = next[fail[now]][i];
                else {
                    fail[next[now][i]] = next[fail[now]][i];
                    que.add(next[now][i]);
                }
        }
    }

    int query(String str) {
        int now = root, res = 0;
        for (int i = 0; i &lt; str.length(); i++) {
            char ch = str.charAt(i);
            now = next[now][ch - fstc];
            int tmp = now;
            while (tmp != root) {
                res += end[tmp];
                // end[tmp] = 0;
                tmp = fail[tmp];
            }
        }
        return res;
    }
}</pre>

# Network Flowing 网络流篇

## Dinic 最大流/最小割

<pre class="toolbar:2 wrap:true lang:java decode:true " >class Dinic{
    public Dinic(int sourse , int meet){
                 this.sourse = sourse ; 
                 this.meet = meet ;
                 Arrays.fill(g,  0) ;
                 id = 1 ;
    }

    static final  int  maxn = 2008 , maxm = 500000 ;
    static class Edge{
           int v , f ,next ;
           Edge(){}
           Edge(int _v , int _f , int _next){
                 this.v = _v ;
                 this.f = _f ;
                 this.next = _next ;
           }
    };
    int  sourse , meet ;
    int  id ;
    static Edge[] e = new Edge[maxm*2 + 10] ;
    static int[] g = new int[maxn + 10] ;

    public void  add(int u , int v , int f){
              e[++id] = new Edge(v , f ,g[u]) ;
              g[u] = id ;
              e[++id] = new Edge(u , 0 , g[v]) ;
              g[v] = id ;
    }

    Queue&lt;Integer&gt; que  = new Queue&lt;Integer&gt;(maxm);
    static boolean[] vis = new boolean[maxn + 10] ;
    static int[]  dist = new int[maxn + 10] ;

    void bfs(){
             Arrays.fill(dist, 0) ;
             while(! que.empty()) que.pop() ;
             que.push(sourse) ;
             vis[sourse] = true ;
             while(! que.empty()){
                  int u = que.pop() ;
                  for(int i = g[u] ; i &gt; 0 ; i = e[i].next){
                       int v = e[i].v ;
                       if(e[i].f  &gt; 0 && !vis[v]){
                             que.push(v) ;
                             dist[v] = dist[u] + 1 ;
                             vis[v] = true ;
                       }
                  }
             }
    }

    int  dfs(int u , int delta){
             if(u == meet) return delta ;
             int ans = 0 ;
             for(int i = g[u] ; i &gt; 0  && delta &gt; 0  ; i = e[i].next){
                   int  v = e[i].v ;
                   if(e[i].f  &gt; 0 && dist[v] == dist[u] + 1){
                        int d = dfs(v , Math.min(delta , e[i].f)) ;
                        e[i].f -= d ;
                        e[i^1].f += d ;
                        delta -= d ;
                        ans += d ;
                   }
             }
             return ans ;
    }

    public int  maxflow(){
                 int ans = 0 ;
                 while(true){
                     Arrays.fill(vis, false) ;
                     bfs() ;
                     if(! vis[meet]) return ans  ;
                     ans += dfs(sourse , Integer.MAX_VALUE) ;
                 }
    }

}</pre>

 [1]: http://cs.ecust.edu.cn/acm/img/icpc_logo.png
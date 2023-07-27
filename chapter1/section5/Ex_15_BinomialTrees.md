### 1.5.15 Binomial trees

Show that the number of nodes at each level in the worst-case trees for weighted quick-union are binomial coefﬁcients. Compute the average depth of a node in a worst-case tree with $N = 2^n$ nodes.

**Answer:**

Observing the worst-case trees for weighted quick-union:

   1    +    1    =   1-1
  1-1   +   1-1   =  1-2-1
 1-2-1  +  1-2-1  =  1-3-3-1
1-3-3-1 + 1-3-3-1 = 1-4-6-4-1



Binomial coefficients.

$$(x + 1)(x + 1) = x^2 + 2x + 1$$

​							$$\vdots$$

$$(x + 1)^4 = x^4 + 4x^3 + 6x^2 + 4x^2 + 1$$	

$$(x + 1)^4 = \binom {4}{4}x^4 + \binom {4}{3}x^3 + \binom {4}{2}x^2 + \binom {4}{1} x^1 + \binom {4}{0} x^0$$

$\dbinom {n}{k} = \dfrac{n!}{k!(n-k)!}$, "$n$ choose $k$", There are $\binom {n} {k}$ ways to choose an (unordered) subset of $k$ elements from a fixed set of $n$ elements. Alternative notations include $C(n, k), C_n^k, C_{n,k}$ in all of which the $C$ stands for *[combinations](https://en.wikipedia.org/wiki/Combination)*  or *choices*.



$$(x + y)(x + y) = x^2 + 2xy + y^2$$

$$(x + y)^n = \sum _{k=0}^n {\binom {n}{k} x^{n-k}y^{k}}$$

Setting $x=1$ and $y=1$, $\sum_{k=0}^n \binom {n}{k} = 2^n$ which is the number of nodes we are given.

The total depth of nodes in a worst-case tree can be represented as $\sum_{k=0}^n k\binom{n}{k} = (2^n)' = n2^{n-1}$

The average depth of nodes in a worst-case tree is $n2^{n-1} / 2^{n} = n/2$.

### 1.4.26

Suppose that you have an algorithm that takes as input N distinct points in the plane and can return the number of triples that fall on the same line. Show that you can use this algorithm to solve the 3-sum problem. 

Strong hint : Use algebra to show that (a, a^3 ), (b, b^3 ), and (c, c^3 ) are collinear if and only if a + b + c = 0.



A.  

A problem is called **3SUM-hard** if solving it in [subquadratic time](https://en.wikipedia.org/wiki/Subquadratic_time) implies a subquadratic-time [algorithm](https://en.wikipedia.org/wiki/Algorithm) for 3SUM.  [link](https://en.wikipedia.org/wiki/3SUM)

Three points (a, a^3), (b, b^3), (c, c^3) are in the same line.

(b^3 -a^3) / (b - a) = (c^3 - b^3) / (c - b)

(b - a)(b ^2 + ab + a^2) / (b - a) = (c - b)(c^2 + cb + b^2) / (c - b)

b^2 + ab + a^2 = c^2 + cb + b^2

b(a - c) + (a - c)(a + c) = 0

(a - c)(a + b + c) = 0

input N distinct points, a ≠c

(a + b + c) = 0.

```java
// a + b + c = 0
int threeSum(int[] a) {
    Points[] N = new Points[a.length];
    for (int i = 0; i < a.length; i += 1) {
        N[i] = new Points(a[i], Math.pow(a[i], 3));
    }
    return sameLineTriples(N);
}

int sameLineTriples(Points[] N) {// return number of triples  that fail on the same line}
```

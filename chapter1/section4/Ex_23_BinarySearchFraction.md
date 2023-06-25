### 1.4.23

Binary search for a fraction. Devise a method that uses a logarithmic number of queries of the form Is the number less than x? to ﬁnd a rational number p/q such that 0 < p < q < N. Hint : Two fractions with denominators less than N cannot differ by more than 1/N2 .

A.

Same with binary search for integer but change the condition from`key == a[mid]` to`(a[mid] - key) <= (1.0 / (a.length * a.length))` , I guess.

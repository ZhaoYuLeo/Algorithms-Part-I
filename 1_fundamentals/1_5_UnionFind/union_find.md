# Union-Find

### Dynamic connectivity

We assume that "is connected to" is an equivalence relation:

- symmetric: If p is connected to q, then q is connected to p.
- transitive: If p is connected to q and p is connected to r, then p is connected to r.
- reflexive: p is connected to p.

<!--similar with other equivalence relations, compare, for example.-->

**Network**
computers in a large network; contact sites in an electrical circuit; people in a social network.

**Variable-name equivalence**
Declare two variable names as being equivalent (references to the same object). After a sequence of such declarationis, the system needs to be able to determine whether two given names are equivalent. This application is an early one (for the FORTRAN programming language) that motivated the development of the algorithms that we are about to consider.
<!--Can't we just compare the contents of the two variables? same content, same address, same object.-->
<!--Maybe we have a, an variable references to object b and we have c, an variable references to a which means c store the address of a. and we have d references to c. d and a reference to the same object but store different address.-->

**Mathematical sets**
On a more abstract level, you can think of the integers as belonging to mathematical sets.

<!--constrain, problem solving, and creativity-->
<!--sites, 触点-->
For simplicity, we assume that we have N sites with integer names, from 0 to N-1. We do so without loss of generality because we shall be considering a host of algorithms in Chapter 3 that can associate arbitrary names with such integer identiﬁers in an efﬁcient manner.
<!--I know they can but I didn't realize the relation. We can replace.-->

For a program, the task is even more difficult, because it has to work just with site names and connections and has no access to the geometric placement of sites in the diagram.

### Solve Problem

**Specify the problem** in a precise manner. 

Problem specification, Algorithm design,  Time and Space. It is impossible to quantify this relationship a *priori*. We often modify a problem specification on finding that it is difficult or expensive to solve or on finding that an algorithm can provide information more useful than what was called for.

Whether or not any given pair p q is connected, and not that it be able to demonstrate a set of connections that connect that pair. Leads us to a different family of algorithms in section 4.1.

**Union-find API**

The development of an algorithmic solution for dynamic connectivity thus reduces to the task of developing an implementation of this API.

Data structure and algorithm design go hand in hand.

It makes sense to use a *site-indexed array* id[] to represent the components since both sites and components will be identified by int values between 0 and N-1.

Represent each component by one of its sites. We initialize id[i] to i for all i from 0 to N-1. 

Test the utility of the API and provide a basis for development. handle input such as largeUF.txt in a reasonable amount of time.

To analyze the algorithms, we focus on the number of times each algorithm accesses an array entry. Constant factor of Union-find cost model. When this quantity.

**Quick-find.** Invariant: p and q are connected if and only if id[p] is e (all method must maintain this invariant). This method is called ***quick-find*** because find(p) just returns id[p]. To maintain the invariant for the call union(p, q), change all the entries equal to id[q] to the value id[p] —the choice between these two alternatives is arbitrary.

***Quick-find analysis***. One array access for each call to find() and between N + 3 and 2N + 1 array accesses for each call to union().

**Proof:** Each call to union() that combines two components does so by making two calls to find(), testing each of the N entries in the id[] array, and changing between 1 and N - 1 of them.

Wind up with a single component. N-1 calls to union(), at least (N+3)(N-1) ~ N^2 array accessed. *Quadratic*-time process. This analysis generalizes to say that quick-find is quadratic for typical applications where we end up with a small number of components. Validate this with a doubling test (see Exe 1.5.23 for an instructive example)

***Quick-union.***  Complementary. The id[] entry for each site is the name of another site in the same component (possibly itself) — we refer to this connection as a *link*. To implement find(), start at the given site, reach a *root* (site has a link to itself). Two sites are in the same component if and only if same root.  <u>To validate this process</u>, we need <u>union(p, q) to maintain this invariant</u>, which is easily arranged: link one of these roots to the other; hence the name *quick-union.* Again, we have an arbitrary choice.

<!--Through which kind of process, can we get a circle.-->

***Forest-of-trees representation.*** The code for quick-union is compact, but a bit opaque. *nodes*, arrows, graphical representation, *trees* —in technical terms, our id[] array is a parent-link representation of forest(set) of trees. Omit both the arrowheads in the link(all point upwards) and the self-links in the roots. <!--谨慎周密-->When we start at the node......, we eventually end up at the root. We can prove this property to be true by **<u>induction</u>**<!--归纳法. widely used, solved many problem-->: It is true after the array is initialized to have every node link to itself, and if it is true before a given `union()` operation, it is certainly true afterward. This representation is useful for this problem because nodes corresponding to two sites are in same tree iff sites in same component. Moreover, the trees are not difficult to build. 

***Quick-union analysis,*** more difficult. More dependent on the nature of the input. find(): 1 - 2N+1<!--while(p != id[p]) p = id[p]. I thought it would be 2N-1, if we had N sites for we could only search the target within this array. Maybe N refers to depth--> (this count is conservative since compiled code will typically *not* do an array access for the second reference to id[p] in the `while` loop). <!--int temp = id[p]; maybe compiled code would store the result.--> We will not dwell on comparative performance of quick-find and quick-union.

**Definition.** *Size*: its number of nodes. *Depth*: the number of links on the path from it to the root. *Height*: maximum depth among its nodes.

**Proposition G.** `find()` 1 + the twice the depth of the node. `union()` and `connected()` 2*cost(find())

***Weighted quick-union.*** Always connect the smaller tree to the larger. Another array to hold the node counts.

 ***Weighted quick-union analysis.*** The worst cast <!--produce the tallest tree--> for weighted quick union, when the sizes of the trees to be merged by union() are always equal (and a power of 2 <!--all trees always have the equal sizes. Why this is the worst case? keep as few branches as possible.让tree尽量向下生长。也许可以用induction来证明。-->). The height of a tree of 2^n nodes is  n<!--exactly-->.  When we merge two trees of 2^n nodes, we get a tree of 2^(n+1) nodes, and we increase the height of the tree to n+1. *Logarithmic* performance.

**Proposition H.** The depth of any node in a forest built by weighted quick-union for N sites is at most lgN.

**Proof:** We prove a stronger fact by (strong) induction: The height of every tree of size k in the forest is at most lgk <!--这里的tree应该有更窄的定义，size k的linked list高是k-1。这里tree对node的branches要求一定更高。-->. base case:  The tree height is 0 when k is 1. Assume the tree <u>height</u> of a tree of size i is <u>at most lgi</u> for all i < k <!--lgi < lgk; lgj < lgk-->.  When we combine a tree of size i with a tree of size j with i <= j and i + j =k, we increase the depth of each node in the smaller set by 1, but they are now in a tree of size i + j = k, so the property is preserved because 1 + lgi = lg(i + i)<!--底是2--> <= lg(i + j) = lgk.<!--这个forest只能通过combine tree的方式来改变-->

**Corollary.** For weighted quick-union with N sites, the worst-case order of growth of the cost of find(), connected(), and union() is logN.

**Proof.** Each operation does at most a constant number of array access for each node on the path from a node to a root in the forest.

The weighted quick-union algorithm uses at most $cMlogN$ array accesses to process M connections among N sites for a small constant c.  in start contrast to *at least* $MN$ array accesses. 

Relatively nodes fall far from... Empirical studies....typically solve....in *constant* time per operation.<!--大多数情况下都是只有一个节点的树归到更大的树中。--> 

| algorithm                                  | constructor | union                  | find                    |
| ------------------------------------------ | ----------- | ---------------------- | ----------------------- |
| quick-find                                 | N           | N                      | 1                       |
| quick-union                                | N           | tree height            | tree height             |
| weighted quick-union                       | N           | lg N                   | lg N                    |
| weighted quick-union with path compression | N           | very, very nearly, but | not quite 1 (amortized) |
| impossible                                 | N           | 1                      | 1                       |

**Optimal algorithms.** plagued researchers for many years. In pursuit of an answer,... *path compression*, We can approach the ideal simply by making all the nodes that we do examine directly link to the root. This step seems drastic at first blush, but it is easy to implement, and there is nothing sacrosanct about the structure of these trees: if we can modify them to make the algorithm more efficient, we should do so.

Add another loop to find() that sets the id[] entry corresponding to each node encountered along the way to link directly to the root. The net result is to flatten the trees almost completely, approximating the ideal achieved by the quick-find algorithm.

Not likely to be able to discern any improvement over weighted quick-union in a pratical situation (see Exe 1.5.24). Theoretical results about the situation are extremely complicated and quite remarkable. *Weighted quick union with path compression is optimal but not quite constant-time per operation.* under "cell probe" model.

Weighted quick-union with path compression is very close to the best that we can do for this problem.

***Amortized cost plots.***  Such diagrams are easy to produce (see Exe 1.5.16)

### Perspective

1. problem statement, complete, specific. Identify fundamental abstract operations that are intrinsic to the problem. API.
2. Succinct implementation, straightforward algorithm, well-thought-out development client, realistic input data. 
3. Know when an implement could not. improve or abandoned.
4. Develop improved implement. stepwise refinement. Empirical analysis, mathematical analysis.
5. Find high-level abstract representations of data structures or algorithms in operation that enable effective high-level design of improved versions.
6. Worst-case performance guarantees. Good performance on typical data.
7. Know when to leave further improvements for detailed in-depth study to skilled researchers and move on to the next problem.

### Q&A

Q. `delete()` method?

A. No one has devised an algorithm as simple and efficient as the ones in this section that can handle deletions. Several .... deleting...more difficult than adding....

Q. What is the cell-probe model?

A. A model of computation where we only count accesses to a random-access memory large enough to hold the input and consider all other operations to be free.

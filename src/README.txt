Grace Heard and Margaret Curtis
CSC 242
Project 2: Automated Reasoning
Members:
Grace Heard
Maggie Curtis

Description:
Our project evaluates whether a sentence can be proven given the Knowledge Base. We have an algorithm that enumerates the truth table in order to determine the entailment of a given sentence by the knowledge base. 
Secondly we converted the KB and the new clause to CNF using CNFConverter. For satisfiability, we used WalkSAT algorithm. The algorithm determines the satisfiability of the new clause and returns a satisfiable model. WalkSAT works by randomly flipping the truth values of symbols in clauses until a satisfiable model is found; otherwise, the algorithm returns a failure if no satisfiable model is found.
 
Who Did What:
We actually started of as two individuals working alone. We were helping each other out with dumb java syntax errors and making sure we each understood the algorithms. Eventually we decided to combine into one group. This meant that we each had an almost full working code base before that. The final touches were both of us working together.

How to Run:
cd src
find pl -name "*.java" -exec javac '{}' +
java pl.examples.ModusPonensKB
java pl.examples.UnicornKB
java pl.examples.WumpusWorldKB

How to Run to Test:
Just run it, since we hard-coded in the queries.

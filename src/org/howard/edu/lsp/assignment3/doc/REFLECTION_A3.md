# Reflection: Assignment 2 vs Assignment 3

In Assignment 2, the ETL pipeline was implemented with mostly procedural code, where all logic was in a single class or a few methods. There was minimal use of object-oriented principles, and responsibilities were not clearly separated.

In Assignment 3, I redesigned the solution to be more object-oriented:

- **Classes and Objects:** I created separate classes for `Product`, `CSVReader`, `CSVWriter`, `Transformer`, and `SimpleTransformer`.
- **Encapsulation:** Each class encapsulates its data and provides methods to access or modify it.
- **Polymorphism:** The `Transformer` interface allows different transformation strategies to be plugged in.
- **Separation of Concerns:** Reading, transforming, and writing are handled by different classes, making the code easier to maintain and extend.

**Testing:**  
I verified that Assignment 3 produces the same output as Assignment 2 by running the pipeline on the original `products.csv` and comparing `transformed_products.csv`. I also tested empty and missing input cases to ensure error handling works correctly.


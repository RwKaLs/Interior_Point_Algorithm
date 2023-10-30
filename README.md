# Interior Point Algorithm for Linear Programming

This repository contains a Java implementation of the Interior Point Algorithm for solving linear programming problems. The Interior Point Algorithm is a numerical optimization method used to find the maximum or minimum of a linear objective function subject to linear equality and inequality constraints.

## Prerequisites

Before running this code, make sure you have the following:

- Java Development Kit (JDK) installed on your system.
- Apache Commons Math library for linear algebra operations. You can download it from [here](https://commons.apache.org/proper/commons-math/download_math.cgi).

## How to Use

1. Clone or download this repository to your local machine.

2. Run the program

3. Follow the on-screen instructions to input the linear programming problem details:

    - Number of variables (n)
    - Number of constraints (m)
    - Objective function coefficients (vector c)
    - Constraint coefficients (matrix A)
    - Right-hand side values (vector b)
    - Approximation accuracy (epsilon)
    - Whether to input a trial solution (1 for yes, 2 for no)

4. If you choose to input a trial solution, provide the trial solution vector.

5. The program will then use the Interior Point Algorithm to maximize and minimize the objective function subject to the constraints, and display the results.

## Code Structure

- `Main.java`: The main class that handles user input and initiates the Interior Point Algorithm.

- `InteriorPointAlgorithm.java`: The class that implements the Interior Point Algorithm, including functions for maximizing and minimizing the objective function.

## Contributors

- Aliya Bogapova
- Mikita Drazdou
- Egor Meganov
- Egor Solodovnikov

## License

This project is licensed under the MIT License.

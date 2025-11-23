<a name="readme-top"></a>

<!--
*** Sudoku Solver - OOP Java Implementation
*** Multiple solving algorithms and advanced techniques
-->

<!-- PROJECT SHIELDS -->
<!--
*** Reference style links for better readability
-->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![Java][java.com]][java-url]
[![Maven][maven.com]][maven-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <h1 align="center">🧩 Sudoku Solver</h1>
  <p align="center">
    A comprehensive Object-Oriented Java implementation of multiple Sudoku solving algorithms
    <br />
    <a href="#-features"><strong>Explore Features »</strong></a>
    <br />
    <a href="#getting-started">Getting Started</a>
    ·
    <a href="#-solvers">Solvers</a>
    ·
    <a href="#usage">Usage</a>
    ·
    <a href="https://github.com/GiangHoGoVap/sudoku/issues">Report Bug</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>📋 Table of Contents</summary>
  <ol>
    <li>
      <a href="#-about-the-project">About The Project</a>
      <ul>
        <li><a href="#-features">Features</a></li>
        <li><a href="#-solvers">Solvers</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#project-structure">Project Structure</a></li>
    <li><a href="#algorithm-details">Algorithm Details</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## 📖 About The Project

This project provides a professional Object-Oriented Java implementation of multiple Sudoku solving algorithms. It's designed to be modular, extensible, and educational, showcasing various problem-solving techniques from simple backtracking to advanced constraint satisfaction methods.

### ✨ Features

-   **Multiple Solving Algorithms**: Choose from 3+ different solvers
-   **OOP Architecture**: Clean, modular design with proper abstraction
-   **Batch Processing**: Solve multiple puzzles from CSV files
-   **Performance Metrics**: Timing and iteration tracking
-   **Flexible Input**: Read from various puzzle formats (Kaggle, Serg, Magictour datasets)
-   **DLX Optimization**: Dancing Links with intelligent column selection
-   **Genetic Algorithm**: Meta-heuristic approach with local search
-   **Heuristic Column Selection**: Proven MRV + Degree strategy

### 🔧 Solver

This project uses **Dancing Links (Algorithm X)** for efficient Sudoku solving:

| Component            | Details                                  |
| -------------------- | ---------------------------------------- |
| **Algorithm**        | Dancing Links (Algorithm X)              |
| **Column Selection** | Heuristic-based (MRV + Degree)           |
| **Complexity**       | O(n²) average case                       |
| **Strength**         | Very fast, handles all difficulty levels |

### Built With

-   [![Java][java.com]][java-url] - Java 11+
-   [![Maven][maven.com]][maven-url] - Build automation
-   [![JUnit][junit.com]][junit-url] - Testing framework

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

### Prerequisites

-   **Java**: JDK 11 or higher
-   **Maven**: 3.6.0 or higher
-   **Git**: For cloning the repository (optional)

Check your versions:

```sh
java -version
mvn -version
```

### Installation

1. Clone the repository

```sh
git clone https://github.com/GiangHoGoVap/sudoku.git
cd sudoku
```

2. Build the project

```sh
mvn clean compile
```

3. Package the application

```sh
mvn package
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->

## Usage

### Running the Application

**Solve puzzles from a CSV file:**

```sh
mvn exec:java -Dexec.mainClass="com.sudoku.SudokuSolverApp" \
  -Dexec.args="data/puzzles0_kaggle output/results.txt"
```

**Programmatic Usage (Java code):**

```java
import com.sudoku.model.Board;
import com.sudoku.io.PuzzleReader;
import com.sudoku.solver.DLXSudokuSolver;

// Read puzzle from string
String puzzleString = "4.3..6.1..1...4.8.8.4......5.4......7.1.7.5.......9.4.7.8...7..4.5.3.2.....7.6.";
Board board = PuzzleReader.parsePuzzleString(puzzleString);

// Solve using Dancing Links
DLXSudokuSolver solver = new DLXSudokuSolver();
boolean solved = solver.solve(board);

if (solved) {
    System.out.println("Solution:\n" + board);
} else {
    System.out.println("No solution found!");
}
```

### Input Format

CSV files should have the format:

```
source,puzzles
1,4.3..6.1..1...4.8.8.4......5.4......7.1.7.5.......9.4.7.8...7..4.5.3.2.....7.6.
2,..3..4.6..5.28....7893.6.5..4...9..6............6..59..1.....2.8..4.......492..1.
```

Each puzzle is an 81-character string where:

-   `.` = empty cell
-   `1-9` = given digit

### Output

Solutions are printed in a readable format:

```
5 3 | 4 | 6 7 | 8 9 2
6 7 | 2 | 1 9 | 5 3 4
2 9 | 8 | 5 3 | 4 6 1
------+-------+------
8 5 | 9 | 6 2 | 7 1 3
4 2 | 6 | 8 1 | 3 9 5
7 1 | 3 | 9 4 | 2 8 6
------+-------+------
9 6 | 7 | 3 5 | 1 2 8
1 4 | 5 | 2 8 | 6 7 9
3 8 | 1 | 7 6 | 9 4 5
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Project Structure

```
sudoku/
├── src/main/java/com/sudoku/
│   ├── model/
│   │   ├── Board.java           # Sudoku board (flexible dimensions)
│   │   └── Cell.java            # Individual cell model
│   │
│   ├── solver/
│   │   └── DLXSudokuSolver.java # Dancing Links solver
│   │
│   ├── dlx/                      # Dancing Links implementation
│   │   ├── DLX.java             # Core algorithm
│   │   ├── Node.java            # Matrix node
│   │   ├── Column.java          # Column header
│   │   ├── selector/
│   │   │   ├── ColumnSelector.java          # Interface
│   │   │   ├── SmallestColumnSelector.java  # MRV heuristic
│   │   │   └── MLColumnSelector.java        # ML-based selection
│   │   ├── features/
│   │   │   └── ColumnFeatureExtractor.java  # Feature extraction
│   │   └── model/
│   │       └── HeuristicModel.java            # Heuristic scoring
│   │
│   ├── io/
│   │   ├── PuzzleReader.java    # Efficient CSV streaming
│   │   └── PuzzleWriter.java    # Solution output
│   │
│   └── SudokuSolverApp.java     # Main CLI application
│
├── data/                         # Puzzle datasets
│   ├── puzzles0_kaggle/
│   ├── puzzles1_unbiased/
│   ├── puzzles2_17_clue/
│   ├── puzzles3_magictour_top1465/
│   ├── puzzles4_forum_hardest_1905/
│   └── ... (more datasets)
│
├── output/                       # Solutions directory
├── pom.xml                       # Maven configuration
└── README.md                     # This file
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Algorithm Details

### Dancing Links (Algorithm X)

The solver uses **Dancing Links**, an efficient implementation of Algorithm X by Donald Knuth for solving exact cover problems. Sudoku can be formulated as an exact cover problem, making DLX ideal for this task.

**How it works:**

1. **Problem Formulation**: Each cell, row, column, and 3×3 box becomes a constraint
2. **Matrix Construction**: Creates a sparse matrix linking cells to constraints
3. **Backtracking Search**: Uses efficient node removal/restoration to explore possibilities
4. **Column Selection**: Chooses columns strategically using heuristics:
    - **MRV (Minimum Remaining Values)**: Select most constrained column first
    - **Degree Heuristic**: Consider which constraints are affected
    - **Result**: Dramatically reduces search space

**Performance:**

-   **Complexity**: O(n²) average case
-   **Typical Speed**: Solves most puzzles in milliseconds
-   **Scalability**: Works efficiently for all Sudoku difficulty levels from easy to extreme

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->

## Contact

**Tam Nguyen**

-   📧 Email: [minhtam27022001@gmail.com](mailto:minhtam27022001@gmail.com)
-   🐙 GitHub: [@GiangHoGoVap](https://github.com/GiangHoGoVap)

Project Repository: [https://github.com/GiangHoGoVap/sudoku](https://github.com/GiangHoGoVap/sudoku)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->

[contributors-shield]: https://img.shields.io/github/contributors/GiangHoGoVap/sudoku.svg?style=for-the-badge
[contributors-url]: https://github.com/GiangHoGoVap/sudoku/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/GiangHoGoVap/sudoku.svg?style=for-the-badge
[forks-url]: https://github.com/GiangHoGoVap/sudoku/network/members
[stars-shield]: https://img.shields.io/github/stars/GiangHoGoVap/sudoku.svg?style=for-the-badge
[stars-url]: https://github.com/GiangHoGoVap/sudoku/stargazers
[issues-shield]: https://img.shields.io/github/issues/GiangHoGoVap/sudoku.svg?style=for-the-badge
[issues-url]: https://github.com/GiangHoGoVap/sudoku/issues
[java.com]: https://img.shields.io/badge/java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[java-url]: https://www.java.com/
[maven.com]: https://img.shields.io/badge/maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white
[maven-url]: https://maven.apache.org/
[junit.com]: https://img.shields.io/badge/junit-25A162?style=for-the-badge&logo=junit5&logoColor=white
[junit-url]: https://junit.org/junit5/

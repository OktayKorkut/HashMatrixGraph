# HashMatrixGraph

A Java library and command-line tool that combines a custom hash table implementation with an adjacency matrix-based graph structure.

---

##  Features

- **Generic Hash Table:** Implements a linear probing hash table for efficient key-based storage (`LinearProbingHash.java`).
- **Adjacency Matrix Graph:** Provides graph creation and manipulation using an adjacency matrix (`GraphMatrix.java`).
- **CLI Interface:** Interactive command-line interface to create graphs, add/remove vertices and edges, and display the adjacency matrix (`Main.java`).
- **Core Operations:**
  - Insert, delete, and lookup vertices via custom hash table.
  - Add and remove edges between vertices.
  - Print the adjacency matrix for visualizing graph connectivity.

---

##  Requirements

- **Java SE 8** or above.
- A terminal or command-line environment.

---

##  Installation & Usage

1. **Clone the repository:**
   ```sh
   git clone https://github.com/oktaykorkut/HashMatrixGraph.git
   cd HashMatrixGraph
   ```

2. **Compile the source files:**
   ```sh
   javac *.java
   ```

3. **Run the application:**
   ```sh
   java Main
   ```
   Or initialize with a fixed number of vertices:
   ```sh
   java Main <initialVertexCount>
   ```

4. **Follow on-screen prompts** to:
   - Load or create vertices.
   - Add or remove edges.
   - Display the adjacency matrix.
   - Search for vertices by key.

---

##  Project Structure

```
HashMatrixGraph/
├── Main.java              # Entry point with CLI menu
├── LinearProbingHash.java # Generic linear probing hash table
└── GraphMatrix.java       # Adjacency matrix graph implementation
```

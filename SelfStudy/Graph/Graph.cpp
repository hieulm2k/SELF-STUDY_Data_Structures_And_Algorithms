#include "Graph.h"

Graph CreateGraphFromFile(string file_name)
{
	int n;
	Graph g;
	ifstream FileIn(file_name, ios::in);
	
	// Notify if failed to open file.
	if (FileIn.fail()) {
		cout << "Failed to open this file!" << endl;
		FileIn.close();
	}

	// Get the number of vertices from file.
	FileIn >> n;
	g.num_vertices = n;
	
	// Get the adjacency matrix from file.
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			FileIn >> g.adjacency_matrix[i][j];
		}
	}

	FileIn.close();
	return g;
}


void DisplayGraph(Graph g) {
	// Display the number of vertices.
	int n = g.num_vertices;
	cout << n << endl;

	// Display the adjacency matrix.
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (g.adjacency_matrix[i][j] == MAX_INT) cout << "INF" << "    ";
			else cout << g.adjacency_matrix[i][j] << "    ";
		}
		cout << endl;
	}
}

bool IsValidGraph(Graph g) {
	// Get the number of vertices.
	int n = g.num_vertices;
	
	// One vertex cannot connect itself, means that each value in the diagonal cannot equal 1.
	for (int i = 0; i < n; i++)
	{
		if (g.adjacency_matrix[i][i] != 0 && g.adjacency_matrix[i][i] != MAX_INT) return false;
	}

	return true;
}

bool IsUndirectedGraph(Graph g) {
	// Get the number of vertices.
	int n = g.num_vertices;

	// In undirected graph, if vertex i connect to vertex j, then j connect to i too. 
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (g.adjacency_matrix[i][j] != g.adjacency_matrix[j][i]) return false;
		}
	}

	return true;
}

int CountEdge(Graph g)
{
	// Get the number of vertices.
	int n = g.num_vertices;

	int count = 0;
	// if g is an undirected graph, we just need to count edge in the upper half.
	if (IsUndirectedGraph(g)) {
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (g.adjacency_matrix[i][j] != 0 && g.adjacency_matrix[i][j] != MAX_INT) count++;
			}
		}
	}
	// if g is a directed graph, we need to count edge in whole graph
	else {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (g.adjacency_matrix[i][j] != 0 && g.adjacency_matrix[i][j] != MAX_INT) count++;
			}
		}
	}
	return count;
}

int CountVertices(Graph g, int flag)
{
	// Get the number of vertices.
	int n = g.num_vertices;

	int count = 0;
	for (int i = 0; i < n; i++) {
		int count_degree = 0;
		for (int j = 0; j < n; j++) {
			// If vertex have a successor, increase variable count degree by 1;
			if (g.adjacency_matrix[i][j] != 0 && g.adjacency_matrix[i][j] != MAX_INT) count_degree++;
		}
		// if degree of vertex equal to flag, increase variable count vertex by 1;
		if (count_degree % 2 == flag) count++;
	}
	return count;
}

void BFS(Graph g, int start_vertex) {
	// Get the number of vertices.
	int n = g.num_vertices;

	// Mark all the vertices as not visited 
	bool* visited = new bool[n];
	for (int i = 0; i < n; i++)
		visited[i] = false;

	// Create a new empty queue
	queue<int> q;

	// Add start_vertex to queue, mark it and print on the screen 
	q.push(start_vertex);
	visited[start_vertex] = true;
	cout << start_vertex << " ";

	// Loop invariant: there is a path from vertex w to every vertex in the queue q 
	LOOP:while (!q.empty())
	{
		int w = q.front();
		q.pop();
		for (int u = 0; u < n; u++) {
			if (g.adjacency_matrix[w][u] != 0 && g.adjacency_matrix[w][u] != MAX_INT && visited[u] == false)
			{
				q.push(u);
				visited[u] = true;
				cout << u << " ";
			}
		}
	}
	cout << endl;
	// If there exist vertices that haven't yet passed, recall the LOOP in BFS
	for (int u = 0; u < n; u++) {
		if (visited[u] == false) {
			visited[u] = true;
			q.push(u);
			cout << u << " ";
			goto LOOP;
			break;
		}
	}
	delete[]visited;
}

void DFS(Graph g, int start_vertex) {
	// Get the number of vertices.
	int n = g.num_vertices;

	// Mark all the vertices as not visited 
	bool* visited = new bool[n];
	for (int i = 0; i < n; i++)
		visited[i] = false;

	// Create a new empty stack
	stack<int> s;

	// Push start_vertex onto the stack, mark it and print on the screen 
	s.push(start_vertex);
	visited[start_vertex] = true;
	cout << start_vertex << " ";

	// Loop invariant : there is a path from vertex v at the
	// bottom of the stack s to the vertex at the top of s
	LOOP:while (!s.empty())
	{
		// variable to check if vertex has a successor or not
		bool hasSuccessor = false;

		// Get the vertex on the top of the stack
		int v = s.top();
		
		// Select an unvisited vertex u adjacent to the vertex on the top of the stack(v)
		for (int u = 0; u < n; u++) {
			if (g.adjacency_matrix[v][u]!=0 && g.adjacency_matrix[v][u] != MAX_INT && visited[u] == false) {
				s.push(u);
				cout << u << " ";
				visited[u] = true;
				hasSuccessor = true;
				break;
			}
		}
		// If there is no unvisited vertices are adjacent to the vertex on the top of the stack(v)
		if (!hasSuccessor) s.pop(); // Backtrack
	}
	cout << endl;
	// If there exist vertices that haven't yet passed, recall the LOOP in DFS
	for (int u = 0; u < n; u++) {
		if (visited[u] == false) {	
			visited[u] = true;
			s.push(u);
			cout << u << " ";
			goto LOOP;
			break;
		}
	}
	delete[]visited;
}

void DFS(Graph g, int v, bool *visited)
{
	visited[v] = true;

	// Get the number of vertices.
	int n = g.num_vertices;

	for (int u = 0; u < n; u++) {
		// if vertex v connect to u and vertex u have not visited, recursive DFS
		if (g.adjacency_matrix[v][u] != 0 && g.adjacency_matrix[v][u] != MAX_INT && visited[u] == false) {
			DFS(g, u, visited);
		}
	}
}

int CountConnectedComponents(Graph g) {
	int count = 0;
	// Apply for Undirected graph
	if (IsUndirectedGraph(g)) {
		// Get the number of vertices.
		int n = g.num_vertices;

		// Mark all the vertices as not visited 
		bool* visited = new bool[n];
		for (int i = 0; i < n; i++)
			visited[i] = false;

		for (int v = 0; v < n; v++) {
			// if vertex v have not visited, recall DFS and increase variable count by 1
			if (visited[v] == false)
			{
				DFS(g, v, visited);
				count++;
			}
		}
		delete[]visited;
	}
	return count;
}

bool IsConnectedGraph(Graph g) {
	// if connected component equal to 1, the graph is connected.
	if (CountConnectedComponents(g) == 1) return true;
	return false;
}

bool IsInVertexSet(vector<int> vertexSet, int v) {
	for (int i = 0; i < vertexSet.size(); i++)
	{
		// if vertex v in vertexSet, return true
		if (vertexSet[i] == v) return true;
	}
	return false;
}

int FindMinWeightIndex(int *weight, vector<int> vertexSet, int n)
{
	int min = MAX_INT;
	int minIndex = 0;

	for (int v = 0; v < n; v++) {
		// if vertex v in vertexSet and weight[v] < min, update min and minIndex
		if (!IsInVertexSet(vertexSet,v) && weight[v] < min) {
			min = weight[v];
			minIndex = v;
		}
	}
	return minIndex;
}

void FindShortestPathDijkstra(Graph g, int start_vertex) {
	// Get the number of vertices.
	int n = g.num_vertices;
	int* weight = new int[n];
	// Step 1: initialization
	//Create a set vertexSet that contains only start vertex
	vector<int> vertexSet;
	vertexSet.push_back(start_vertex);

	// Get weight from row of column start vertex of maxtrix
	for (int v = 0; v < n; v++)
	{
		weight[v] = g.adjacency_matrix[start_vertex][v];										
	}
	
	// Steps 2 through n
	// Invariant: For v not in vertexSet, weight[v] is the
	// smallest weight of all paths from start vertex to v that pass
	// through only vertices in vertexSet before reaching
	// v. For v in vertexSet, weight[v] is the smallest
	// weight of all paths from start vertex to v (including paths
	// outside vertexSet), and the shortest path
	// from 0 to v lies entirely in vertexSet.
	for (int step = 2; step <= n; step++)
	{
		int v = FindMinWeightIndex(weight, vertexSet, n);
		vertexSet.push_back(v);

		for (int u = 0; u < n; u++)
		{
			if (!IsInVertexSet(vertexSet, u) && weight[u] > weight[v] + g.adjacency_matrix[v][u])
				weight[u] = weight[v] + g.adjacency_matrix[v][u];
		}
	}
	cout << "Find the shortest path from one vertex to all the other vertices according to Dijkstra algorithm:" << endl;
	for (int v = 0; v < n; v++)
	{
		if (v != start_vertex) {
			cout << "The shortest path from vertex " << start_vertex << " to vertex " << v << " is: ";
			if (weight[v] == MAX_INT) cout << "INF";
			else cout << weight[v];
		}
		cout << endl;
	}
}

void FindShortestPathFloyd(Graph g, int start_vertex) {
	// Get the number of vertices.
	int n = g.num_vertices;

	/* D[][] will be the output matrix
   that will finally have the shortest
   distances between every pair of vertices */
	int D[MAX][MAX];

	/* Initialize the solution matrix same
   as input graph matrix. Or we can say
   the initial values of shortest distances
   are based on shortest paths considering
   no intermediate vertex. */
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
		{
			if (j == i) D[i][j] = 0;
			else D[i][j] = g.adjacency_matrix[i][j];
		}

	for (int k = 0; k < n; k++)
	{
		// Pick all vertices as source one by one  
		for (int i = 0; i < n; i++)
		{
			// Pick all vertices as destination for the  
			// above picked source  
			for (int j = 0; j < n; j++)
			{
				// If vertex k is on the shortest path from  
				// i to j, then update the value of D[i][j]  
				if (D[i][k] + D[k][j] < D[i][j])
					D[i][j] = D[i][k] + D[k][j];
			}
		}
	}

	cout << "Find the shortest path from one vertex to all the other vertices according to Floyd algorithm:" << endl;
	for (int v = 0; v < n; v++)
	{
		if (v != start_vertex)
		{
			cout << "The shortest path from vertex " << start_vertex << " to vertex " << v << " is: ";
			if (D[start_vertex][v] == MAX_INT)
				cout << "INF";
			else
				cout << D[start_vertex][v];
		}
		cout << endl;
	}
}

void FindShortestPathBellman(Graph g, int start_vertex)
{
	// Get the number of vertices.
	int n = g.num_vertices;
	int* weight = new int[n];
}
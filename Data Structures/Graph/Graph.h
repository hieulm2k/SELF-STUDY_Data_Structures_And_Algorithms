#pragma once
#include <fstream>
#include <iostream>
#include <stack>
#include <queue>
#include <vector>
#define MAX 100
#define MAX_INT 1000000
using namespace std;

struct Graph
{
	int num_vertices; // number of vertices
	int adjacency_matrix[MAX][MAX]; // adjacency matrix
};

// Function: Create a graph from file.
// Return: A graph is read from file.
// Input:
// file_name: the name of input file(graph.txt).
Graph CreateGraphFromFile(string file_name);

// Function: Display a graph.
// Return: void.
// Input:
// g: the graph you want to display.
void DisplayGraph(Graph g);

// Function: Check if the graph is valid or not.
// Return: true if g is a valid graph, false otherwise.
// Input:
// g: the graph you want to check.
bool IsValidGraph(Graph g);

// Function: check if the graph is undirected or not.
// Return: true if g is an undirected graph, false otherwise.
// Input:
// g: the graph you want to check.
bool IsUndirectedGraph(Graph g);

// Function: Count the number of edges in the graph.
// Return: the number of edges counted.
// Input:
// g: the graph you want to count edge.
int CountEdge(Graph g);

// Function: Count the number of vertices on request.
// Return: the number of vertices counted on request.
// Input:
// g: the graph you want to count vertices.
// flag: if flag = 1, function performs counting the number of vertices with odd degrees;
//		 if flag = 0, function performs counting the number of vertices with even degrees.
int CountVertices(Graph g, int flag);

// Function: Traversing the graph by breadth.
// Return: void
// Input:
// g: the graph you want to traversing.
// start vertex: the vertex to start traversing.
void BFS(Graph g, int start_vertex);

// Function: Traversing the graph by depth.
// Return: void.
// Input:
// g: the graph you want to traversing.
// start vertex: the vertex to start traversing.
void DFS(Graph g, int start_vertex);

// Function: check if the graph is connected or not.
// Return: true if g is a connected graph, false otherwise.
// Input:
// g: the graph you want to check.
bool IsConnectedGraph(Graph g);

// Function: Count the number of connected components in the graph.
// Return: the number of connected components counted.
// Input:
// g: the graph you want to count connected component.
int CountConnectedComponents(Graph g);

// Function: Find the shortest path from one vertex to all the other vertices according to Dijkstra algorithm.
// Return: void 
// Input:
// g: the graph you want to find the shortest path.
// start vertex: the vertex to start finding.
void FindShortestPathDijkstra(Graph g, int start_vertex);

// Function: Find the shortest path from one vertex to all the other vertices according to Floyd algorithm.
// Return: void 
// Input:
// g: the graph you want to find the shortest path.
// start vertex: the vertex to start finding.
void FindShortestPathFloyd(Graph g, int start_vertex);

// Function: Find the shortest path from one vertex to all the other vertices according to Bellman algorithm.
// Return: void 
// Input:
// g: the graph you want to find the shortest path.
// start vertex: the vertex to start finding.
void FindShortestPathBellman(Graph g, int start_vertex);
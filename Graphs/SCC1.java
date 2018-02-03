// Strongly connected components using Khosaraju Algorithm in O(V+E) time
// This code will print all the strongly connected components in the graph
// https://www.youtube.com/watch?v=RpgcYiky7uw&t=6s

import java.util.*;
class SCC1{
	int V;
	LinkedList<Integer> Edges[];
	LinkedList<Integer> Reverse[];
	LinkedList<Integer> stack;
	boolean visited[];
	SCC1(int x)
	{
		this.V = x+1;
		Edges = new LinkedList[V];
		Reverse = new LinkedList[V];
		visited = new boolean[V];
		stack = new LinkedList<Integer>();
		for(int i = 0;i<V;i++)
		{
			Edges[i] = new LinkedList<Integer>();
			Reverse[i] = new LinkedList<Integer>();
		}
	}
	void addEdge(int u,int v)
	{
		Edges[u].add(v);
		Reverse[v].add(u);
	}
	void runDFS()
	{
		for(int i = 1;i < V;i++)
			{
				if (!visited[i])
					DFS(i,0);	
			}
	}
	void DFS(int u,int bit)
	{
		visited[u] = true;
		LinkedList<Integer> list;
		if (bit == 0)
			list = Edges[u];
		else
			list = Reverse[u];
		for(int i : list)
		{
			if (!visited[i])
				DFS(i,bit);
		}
		if (bit == 0)
			stack.addFirst(u);
		else
		{
			System.out.print(u+" ");
		}
	}
	void khosaraju()
	{
		Arrays.fill(visited,false);
		while(!stack.isEmpty())
		{
			int val = stack.pop();
			if (visited[val])
				continue;
			DFS(val,1);
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//int n = scan.nextInt();   // no. of vertices
		SCC1 graph = new SCC1(11);
		graph.addEdge(1,2);
		graph.addEdge(2,3);
		graph.addEdge(3,1);
		graph.addEdge(2,4);
		graph.addEdge(4,5);
		graph.addEdge(5,6);
		graph.addEdge(6,4);
		graph.addEdge(7,6);
		graph.addEdge(7,8);
		graph.addEdge(8,9);
		graph.addEdge(9,10);
		graph.addEdge(10,7);
		graph.addEdge(10,11);
		graph.runDFS();	
		graph.khosaraju();
	}
}
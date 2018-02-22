import java.util.*;


// implementation using adjacency matrix
class FordFulkerson{
	static final int INF = 10000000;
	int V;
	int Graph[][];   // this is a directed graph using adjacency matrix
	int Residual[][];  // this is residual graph
	int parent[];
	boolean visited[];
	int maxflow = 0;
	FordFulkerson(int V,int G[][])
	{
		this.V = V;
		Graph = G;//new int[V][V];
		Residual = G;//new int[V][V];
		parent = new int[V];
		visited = new boolean[V];
	}
/*	void addEdge(int u,int v,int w)   // directed edge from u to v
	{
		Graph[u][v] = w;
	}*/
	boolean BFS(int source,int sink)  // return true if there is path from source to sink	
	{
		parent[source] = -1;
		visited[source] = true;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		while(!queue.isEmpty())
		{
			int v = queue.poll();
			for(int i = 0;i<V;i++)
			{
				if (Residual[v][i] > 0  && !visited[i])
				{
					queue.add(i);
					visited[i] = true;
					parent[i] = v;
				}
			}
		}
		return visited[sink];
	}


	void findMaxFlow(int source,int sink)
	{
		System.out.println("entered");
		while(BFS(source,sink))
		{
			Arrays.fill(visited,false);
		//	System.out.println("in the loop");
			int max = INF;  // will contain max flow possible from one path
			int v = sink;
			int u;
			while(v!=source)
			{
			//	System.out.println("inside updation loop");
				u = parent[v];
				if (max > Residual[u][v])
					max = Residual[u][v];
				v = u;
			}
			v = sink;
			while(v!=source)
			{
				u = parent[v];
				Residual[u][v]-=max;
				Residual[v][u]+=max;	
				v = u;
			}
			maxflow+=max;
		}
		System.out.println(maxflow);
	}
	public static void main(String[] args) {
		int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0},
		                             {0, 0, 10, 12, 0, 0},
		                             {0, 4, 0, 0, 14, 0},
		                             {0, 0, 9, 0, 0, 20},
		                             {0, 0, 0, 7, 0, 4},
		                             {0, 0, 0, 0, 0, 0}
		                           };
		FordFulkerson ob = new FordFulkerson(6,graph);
		ob.findMaxFlow(0,5);

	}
}

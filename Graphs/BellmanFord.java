import java.util.*;
class BellmanFord{
	static final int INF = 100000000;
	Edge edges[];
	int v,e;
	int distance[];
	BellmanFord(Edge edges[], int v,int e)
	{
		this.edges = edges;
		this.v = v;
		this.e = e;
		distance = new int[v];
		Arrays.fill(distance,INF);
	}
	 void FindPath(int source)
	{
		distance[source] = 0;
		for(int i = 0;i<v-1;i++)
		{
			for(int j = 0;j<e;j++)
			{
				int src = edges[j].source;
				int dest = edges[j].dest;
				int w = edges[j].weight;
				if ( distance[src] !=INF && distance[dest] > distance[src] + w)
					distance[dest] = distance[src] + w;
			}
		}
		boolean negativeCycle = false;
		for(int j = 0;j < e;j++)
		{
							int src = edges[j].source;
				int dest = edges[j].dest;
				int w = edges[j].weight;
				if (distance[dest] > distance[src] + w)
					{
						negativeCycle = true;
						break;
					}
		}
		if (negativeCycle)
		{
			System.out.println("negativeCycle existt");
		}
		else
		{
			System.out.println(Arrays.toString(distance));
		}
	}
	public static void main(String[] args) {
		int v = 5;
		int e = 8;
		Edge edges[] = new Edge[e];
		for(int i = 0;i<e;i++)
			edges[i] = new Edge();
		edges[0].source = 0;
		edges[0].dest = 1;
		edges[0].weight = -1;

		edges[1].source = 0;
		edges[1].dest = 2;
		edges[1].weight = 4;

		edges[2].source = 1;
		edges[2].dest = 2;
		edges[2].weight = 3;

		edges[3].source = 1;
		edges[3].dest = 3;
		edges[3].weight = 2;

		edges[4].source = 1;
		edges[4].dest = 4;
		edges[4].weight = 2;

		edges[5].source = 3;
		edges[5].dest = 2;
		edges[5].weight = 5;

		edges[6].source = 3;
		edges[6].dest = 1;
		edges[6].weight = 1;

		edges[7].source = 4;
		edges[7].dest = 3;
		edges[7].weight = -3;
		BellmanFord ob = new BellmanFord(edges,v,e);
		ob.FindPath(0);		
	}

}
class Edge{
	int source;
	int dest;
	int weight;
/*	Edge(int s,int d,int w)
	{
		source = s;
		dest = d;
		weight = w;
	}*/
}
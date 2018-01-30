

// problem is of finding articulation point in an undirected graph using idea of back edge and dfs
// complexity of code is O(V+E)
// http://www.eecs.wsu.edu/~holder/courses/CptS223/spr08/slides/graphapps.pdf


import java.util.*;
class articulationPoint{
	int parent[];
	int low[];
	int discovery[];
	int time = 0;
	int v;
	boolean visited[];
	boolean articulation[];
	LinkedList<Integer> edge[];
	articulationPoint(int v)
	{
		this.v = v;
		parent = new int[v];
		low = new int[v];
		discovery = new int[v];
		edge = new LinkedList[v];
		visited = new boolean[v];
		articulation = new boolean[v];
		for(int i = 0;i<v;i++)
			{
				edge[i] = new LinkedList<Integer>();
				parent[i] = -1;
			}
	}
	void addEdge(int u,int v)
	{
		edge[u].add(v);
		edge[v].add(u);
	}
	void findpoint(int k)
	{
		for(int i = 0;i<v;i++)
		{
			if (!visited[i])
			{
				DFS(i);
			}
		}
		int count = 0;
		for(int i = 0;i<v;i++)
		{
			if(articulation[i])
				count++;
		}
		System.out.println(count*k);
	}
	void DFS(int v)
	{
		int childs = 0;
		visited[v] = true;
		low[v] = discovery[v] = time++;
		Iterator<Integer> it = edge[v].iterator();
		while(it.hasNext())
		{
			int w = it.next();
			if (!visited[w])
			{
				childs++;
				parent[w] = v;
				DFS(w);
				low[v] = Math.min(low[v],low[w]);
				if (parent[v]==-1  && childs > 1)
					articulation[v] = true;
				else if (parent[v]!=-1 && low[w] >=discovery[v])
					articulation[v] = true;
			}
			else if (parent[v]!=w)
				low[v] = Math.min(low[v],discovery[w]);
		}

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		while(t-->0)
		{
			int n = scan.nextInt();
			int m = scan.nextInt();
			int k = scan.nextInt();
			articulationPoint ob = new articulationPoint(n);
			for(int i = 0;i<m;i++)
			{
				int u = scan.nextInt();
				int v = scan.nextInt();
				ob.addEdge(u,v);
			}
			ob.findpoint(k);
		}
		
	}
}
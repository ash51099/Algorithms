
// Stable Marriage
// https://www.codechef.com/problems/STABLEMP


import java.util.*;
class StableMarriage{
	int menList[][];
	int womenList[][];
	boolean engagedMen[];
	int engagedWomen[];
	int freemen;
	int ans[];
	int freq[][];  // used to store men for woman list
	int n;
	StableMarriage(int n,int menList[][],int womenList[][],int freq[][])
	{
		this.n = n;
		this.menList = menList;//new int[n+1][n+1];
		this.womenList = womenList;//new int[n+1][n+1];
		engagedWomen = new int[n+1];
		engagedMen = new boolean[n+1];
		freemen = n;
		ans = new int[n+1];
		this.freq = freq;//new int[1000][n+1];
		Arrays.fill(engagedWomen,-1);
		Arrays.fill(ans,-1);
	}
	boolean womanprefer(int woman,int cur,int prev)
	{
		return freq[woman][cur] < freq[woman][prev];
	}
	void findStableMatching()
	{
		while(freemen!=0)
		{
			int i;
			for( i = 1;i<=n;i++)  // will give the man who is not engaged yet
			{
				if (!engagedMen[i])
					break;
			}
			if (i == n && engagedMen[i])
				break;
			int currentman = i;
			for(i = 1;i<=n;i++)
			{
				int woman = menList[currentman][i];   // men's preferred womem 
				if (engagedWomen[woman] == -1)  // woman is free
				{
					engagedWomen[woman] = currentman;
					ans[currentman] = woman;
					engagedMen[currentman] = true;
					freemen--;	
					break;
				}
				else
				{
					int prevman = engagedWomen[woman];
					if (womanprefer(woman,currentman,prevman))
					{
						engagedMen[prevman] = false;
						engagedWomen[woman] = currentman;
						ans[currentman] = woman;
						engagedMen[currentman] = true;
						break;
					}
/*					else
						break;*/
				}

			}
			

		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		while(t-->0)
		{
			int n = scan.nextInt();
			int womenList[][] = new int[n+1][n+1];
			int menList[][] = new int[n+1][n+1];
			int freq[][] = new int[n+1][n+1];
			int rank = 0;
			for(int i = 0;i<=n;i++)
			{
				if (i == 0)
				{
					//scan.nextInt();
					continue;
				}
				rank = 0;
				for(int j = 0;j<= n;j++)
				{
					if (j == 0)
					{
						scan.nextInt();
						continue;
					}
					womenList[i][j] = scan.nextInt();
					freq[i][womenList[i][j]] = rank++;
				}
			}
			for(int i = 0;i<=n;i++)
			{
				if (i == 0)
				{
				//	scan.nextInt();
					continue;
				}
				for(int j = 0;j<= n;j++)
				{
					if (j == 0)
					{
						scan.nextInt();
						continue;
					}
					menList[i][j] = scan.nextInt();
				}
			}
			StableMarriage ob = new StableMarriage(n,menList,womenList,freq);
			ob.findStableMatching();
			for(int i = 1;i<=n;i++)
				System.out.println(i+" "+ob.ans[i]);
/*			for(int i = 1;i<=n;i++)
			{
				for(int j = 1;j<=n;j++)
					System.out.print(freq[i][j]);
				System.out.println();
			}*/

		}
		
	}
}

import java.io.*;

class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n=grid.length;
        int sum=0;
        int[] rMax=new int[n];
        int[] lMax=new int[n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]>rMax[i]) rMax[i]=grid[i][j];
                if (grid[i][j]>lMax[j]) lMax[j]=grid[i][j];
            }
        }
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                sum+=getNewHeight(i,j,rMax,lMax)-grid[i][j];
            }
        }
        return sum;
    }
    private int getNewHeight(int row,int col,int[] a,int[] b){
        if (a[row]<=b[col]) return a[row];
        return b[col];
    }
}

public class MainClass {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            
        	String[] arr=line.split("]");
        	
        	int[][] grid;
        	
			if (arr.length == 0)
				grid = new int[0][0];
			else {
				grid=new int[arr.length][arr.length];
				arr[0] = arr[0].substring(1, arr[0].length());
				for (int i = 1; i < arr.length; i++) {
					arr[i] = arr[i].substring(2, arr[i].length());
				}

				for (int i = 0; i < arr.length; i++) {
					String[] arr1=arr[i].split(",");
					for (int j=0;j<arr1.length;j++) {
						grid[i][j]=Integer.valueOf(arr1[j]);
						//System.out.print(grid[i][j]);
					}
				}

				int ret = new Solution().maxIncreaseKeepingSkyline(grid);

				String out = String.valueOf(ret);

				System.out.print(out);
			}
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public int countSegments(String s) {
		int sum=0;
		String[] sArr=s.split(" +");
		for (String sarr:sArr){
			if (!sarr.equals("")) sum++;
		}

		return sum;
	}
}

public class MainClass2 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int ret = new Solution().countSegments(line);

			String out = String.valueOf(ret);

			System.out.print(out);
		}
	}
}
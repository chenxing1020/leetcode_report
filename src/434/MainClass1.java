import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	public int countSegments(String s) {

		int len = s.length();
		int i = 0, sum = 0;

		while (i < len) {
			char c = s.charAt(i);
			if (c!=' ')  {
				sum++;
				i++;
				if (i >= len)
					break;
				c = s.charAt(i);
				while (c!=' ') {
					i++;					
					if (i >= len)
						break;
					c = s.charAt(i);
				}
			} else
				i++;
		}
		return sum;
	}
}

public class MainClass1 {

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
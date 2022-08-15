package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16401 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		int m = Integer.parseInt(input[0]);
		int n = Integer.parseInt(input[1]);

		input = br.readLine().split(" ");
		int[] data = new int[n];

		int start = 0;
		int end = 0;
		int total = 0;

		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(input[i]);
			end = Math.max(end, temp);
			total += temp;
			data[i] = temp;
		}

		if (total < m)
			System.out.println(0);
		else {
			int res = 0;

			while (start <= end) {
				int temp = 0;
				int mid = (start + end) / 2;

				for (int x : data) {
					if (x >= mid)
						temp += (x / mid);
				}

				if (temp >= m) {
					start = mid + 1;
					res = mid;
				} else
					end = mid - 1;
			}
			System.out.println(res);

		}

	}

}

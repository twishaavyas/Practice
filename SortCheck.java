import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Arrays;

public class SortCheck {
	public static void main(String[] args) {
		if ((args.length != 2) || (!args[0].equals("-i"))) {
			System.out.println("Usage: java SortCheck -i <input-file>");
			System.exit(1);
		}

		String filePath = args[1];
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			System.out.println(
					"File " + filePath + " not found or cannot be opened");
			System.exit(1);
		}

		int size = sc.nextInt();
		float[] array = new float[size];
		for (int i = 0; i < size; i++) {
			array[i] = sc.nextFloat();
		}

		sort(array);
		boolean result = verifySorted(array);
		if (result) {
			System.out.println("Final array is sorted correctly");
		} else {
			System.out.println("Final array is not sorted correctly");
		}

	}

	private static boolean verifySorted(float[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				return false;
			}
		}
		return true;
	}

	private static void sort(float[] array) {
		bottomUpMerge(array, array.length);
	}

	private static float[] bottomUpMerge(float[] a, int n) {

		int i = 1;
		int j = 0;
		int r = 0;
		// We create a temporary array of size n
		float[] temp = new float[n];

		// If n = 2, this loop runs 1 times OR n = 2^1
		// If n = 4, this loop runs 2 times OR n = 2^2
		// If n = 8, this loop runs 3 times OR n = 2^3
		// Hence, if n = 2^i , this loop runs i times.
		// therefore, logn = i log 2
		// OR i = logn
		// Hence, this loop runs logn times.

		while (i < n) {
			j = 0;
			// j starts from 0 and runs till n
			// However, the step size here is
			// j = j + (2 * i)
			// We know that this algorithm first runs on all n elements
			// Then it runs for n/2 elements, then n/4 and so on until
			// we get a single array
			while (j < n) {
				// r describes the end of our subarrays
				// if the value of r is greater than the length of the array,
				// (this happens when the length is not a power of 2),
				// then r = n - 1.
				if (j + (2 * i) - 1 >= n)
					r = n - 1;
				else
					r = j + (2 * i) - 1;

				// m describes the end of our subarray. It is basically the
				// partititoning point of the subarrays
				// if m becomes greater than or equal to r, it means that our
				// entire array is sorted and we terminate the execution.
				int m = j + i - 1;
				if (m >= r)
					break;
				merge(a, j, m, r, temp);

				// j is start index of the next sub array. Hence it is the right
				// element of the previous subarray + 1
				j = r + 1;
			}
			// i is the step size which needs to be powers of two.
			i = i * 2;

		}
		return a;

	}

	// this function takes in two sorted subarrays(first from l to m and second
	// from m + 1 to r) and merges them into a single sorted array.
	private static void merge(float[] a, int l, int m, int r, float[] temp) {

		int i = l;
		int j = m + 1;
		int k = l;

		// this loop is executed until we have accessed all the elements of the
		// first or the second subarray.
		while (i <= m && j <= r) {
			// if the element ith element of the first subarry is greater than
			// the jth element of the second array, then we copy it into the
			// temporary array.
			if (a[i] < a[j]) {
				temp[k] = a[i];
				i++;
			} else {
				temp[k] = a[j];
				j++;
			}
			k++;
		}

		// copying the remaining elements of first subarray into the temporary
		// array
		for (; i <= m; i++) {
			temp[k] = a[i];
			k++;
		}

		// copying the remaining elements of second subarray into the temporary
		// array
		for (; j <= r; j++) {
			temp[k] = a[j];
			k++;
		}

		// copying the sorted elements back into the original array
		for (int x = l; x <= r; x++) {
			a[x] = temp[x];
		}

	}

}
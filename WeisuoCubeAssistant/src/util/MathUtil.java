package util;

public class MathUtil {

	// Cnk = n!/(k!*(n-k)!)
	// =n*(n-1)*(n-2)*...*(n-k+1) /(k*(k-1)*(k-2)*...*1)
	// number:k number:k
	public static int Cnk(int n, int k) {
		int s = n;
		int m = 1;
		int result = 1;
		if (n < k)
			return 0;
		if (k > n / 2)
			k = n - k;// reduction
		while (s > k) {
			result *= s--;
			result /= m++;
		}
		return result;
	}
	
	public static void main(String args[]){
		System.out.println(Cnk(6, 3));
	}
}

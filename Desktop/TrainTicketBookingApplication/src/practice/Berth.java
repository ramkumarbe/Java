package practice;

public class Berth {
	public static void main(String[] args) {
		int[] arr = {1,1,2,2};
		boolean flag = false;
		for(int i=0; i<arr.length; i++) {
			int left=0,right=0;
			for(int j=0; j<i; j++) 
				left += arr[j];
			for(int j=i+1; j<arr.length; j++) 
				right += arr[j];
			if(left == right) {
				System.out.println("Yes");
				flag = true;
				break;
			}
		}
		if(!flag)
			System.out.println("No");
	}
}

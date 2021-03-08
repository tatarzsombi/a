

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static class Interval {

		private int min;
		private int max;

		public  Interval(int min, int max) {
			this.min = min;
			this.max = max;
		}

		public int getMin() {
			return min;
		}

		public void setMin(int min) {
			this.min = min;
		}

		public int getMax() {
			return max;
		}

		public void setMax(int max) {
			this.max = max;
		}
	}
	
	public static void main(String[] args) {
		List<Interval> planet1 = new ArrayList<>();
		List<Interval> planet2 = new ArrayList<>();

        Scanner my_scan = new Scanner(System.in);
        int elso = my_scan.nextInt();
        int masodik = my_scan.nextInt();
        int max = 0;
       
        for(int i = 0; i < elso; i++){
            int szam1 = my_scan.nextInt();
            int szam2 = my_scan.nextInt();
            planet1.add(new Interval(szam1, szam2));
            max = setMax(max, szam2);
        }
        for(int i = 0; i < masodik; i++){
        	  int szam1 = my_scan.nextInt();
              int szam2 = my_scan.nextInt();
              planet2.add(new Interval(szam1, szam2));
              max = setMax(max, szam2);
        }

		int[] planetA = new int[max];
		int[] planetB = new int[max];
		
		for (Interval intervl : planet1) {
			setvalami(planetA, intervl);
		}
		
		for (Interval intervl : planet2) {
			setvalami(planetB, intervl);
		}
		
		List<Interval> intervalList = new ArrayList<>();
		
		Interval interval = new Interval(0, 0);
		boolean b = false;
		for (int i = 0; i < max; i++) {
			//System.out.println("i: " + i +", A: " + planetA[i] + ", B: " + planetB[i]);
			if (planetA[i] != planetB[i]) {
				if (!b) {
					interval.setMin(i+1);
					b = true;
				}
			} else {
				if (b) {
					interval.setMax(i);
					b = false;
					intervalList.add(interval);
					interval = new Interval(0, 0);
				}
			}
		}
		
		if (interval.getMax() == 0) {
				interval.setMax(max);
				intervalList.add(interval);
		}
		
		
		System.out.println(intervalList.size());
		
		for (Interval intervl : intervalList) {
			System.out.println(intervl.getMin() + " " + intervl.getMax());
		}
	}

	
	private static void setvalami(int[] planet, Interval interval) {
		for (int i = interval.getMin()-1; i < interval.getMax(); i++) {
			planet[i] = 1;
		}
	}
	
	private static int setMax(int max, int newInt) {
		return max > newInt ? max : newInt;
			
	}

}

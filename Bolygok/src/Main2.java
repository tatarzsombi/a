import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
	static class Product {

		private int productType;
		private int productAmount;
		private int soldProduct;

		public Product(int productType, int productAmount, int soldProduct) {
			this.productType = productType;
			this.productAmount = productAmount;
			this.soldProduct = soldProduct;
		}

		public int getProductType() {
			return productType;
		}

		public void setProductType(int productType) {
			this.productType = productType;
		}

		public int getProductAmount() {
			return productAmount;
		}

		public void setProductAmount(int productAmount) {
			this.productAmount = productAmount;
		}

		public int getSoldProduct() {
			return soldProduct;
		}

		public void setSoldProduct(int soldProduct) {
			this.soldProduct = soldProduct;
		}
	}

	public static void main(String[] args) {

		Map<Integer, List<Product>> kereskedoestermekMap = new HashMap<Integer, List<Product>>();
		List<Product> productList = new ArrayList<Product>();
		Scanner my_scan = new Scanner(System.in);
		int kereskedosz = my_scan.nextInt();
		int termekeksz = my_scan.nextInt();
		int kertek = my_scan.nextInt();

		for (int x = 0; x < kereskedosz; x++) {
			int kereskedoindex = my_scan.nextInt();
			for (int y = 0; y < termekeksz; y++) {
				if (my_scan.hasNext()) {
					productList.add(new Product(my_scan.nextInt(), my_scan.nextInt(), my_scan.nextInt()));
				}
			}
			kereskedoestermekMap.put(kereskedoindex, productList);
		}

		List<Integer> osszegek = new ArrayList<Integer>();
		Map<Integer, List<Integer>> kereskedoesbevetelMap = new HashMap<Integer, List<Integer>>();
		for (Map.Entry<Integer, List<Product>> me : kereskedoestermekMap.entrySet()) {

			int osszeg = kiszamolas(me.getValue());

			if (kereskedoesbevetelMap.containsKey(osszeg)) {
				kereskedoesbevetelMap.get(osszeg).add(me.getKey());
			} else {
				osszegek.add(osszeg);
				List<Integer> kereskedolista = new ArrayList<Integer>();
				kereskedolista.add(me.getKey());
				kereskedoesbevetelMap.put(osszeg, kereskedolista);
			}
		}

		Collections.sort(osszegek);
		for (Integer i : osszegek) {
			for (Integer ok : kereskedoesbevetelMap.get(i)) {
				System.out.println(ok);
			}
		}
	}

	static int kiszamolas(List<Product> list) {
		int osszeg = 0;
		for (Product p : list) {
			osszeg = osszeg + p.productAmount * p.soldProduct;
		}
		return osszeg;
	}

}

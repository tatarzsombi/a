package valami.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
    static class Product {

        private int productType;
        private int productAmount;
        private int soldProduct;

        public  Product(int productType, int productAmount, int soldProduct) {
            this.productType = productType;
            this.productAmount = productAmount;
            this.soldProduct = soldProduct ;
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

    public static void main(String[] args) throws IOException {

        Map<Integer, List<Product>> kereskedoestermekMap= new HashMap<Integer, List<Product>>();
        List<Product> productList = new ArrayList<Product>();
        InputStreamReader r2=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(r2);
        String[] kereskedsz = br.readLine().split(" ");
        int kereskedosz = Integer.parseInt(kereskedsz[0]);
        int termekeksz = Integer.parseInt(kereskedsz[1]);
        int kertek = Integer.parseInt(kereskedsz[2]);
        String line;
        for(int x = 0; x < kereskedosz; x++){
            String[] kereskedindex = br.readLine().split(" ");
            int kereskedoindex = Integer.parseInt(kereskedindex[0]);
            for(int y = 1; y < kereskedindex.length;){
                    productList.add(new Product(Integer.parseInt(kereskedindex[y]), Integer.parseInt(kereskedindex[y+1]), Integer.parseInt(kereskedindex[y + 2])));
                    y += 3;
            }
           kereskedoestermekMap.put(kereskedoindex, productList);
           productList = new ArrayList<Product>();
        }
        
       

        List<Integer> osszegek = new ArrayList<Integer>();
        Map<Integer, List<Integer>> kereskedoesbevetelMap= new HashMap<Integer, List<Integer>>();
        for (Map.Entry<Integer, List<Product>> me : kereskedoestermekMap.entrySet()) {
            int osszeg = kiszamolas(me.getValue());

            if(kereskedoesbevetelMap.containsKey(osszeg)) {
                kereskedoesbevetelMap.get(osszeg).add(me.getKey());
            } else {
                osszegek.add(osszeg);
                List<Integer> kereskedolista = new ArrayList<Integer>();
                kereskedolista.add(me.getKey());
                kereskedoesbevetelMap.put(osszeg, kereskedolista);
            }
        }

        Collections.sort(osszegek);
        for (Integer i : osszegek ){
            for(Integer ok : kereskedoesbevetelMap.get(i)){
                System.out.println(ok);
            }
        }
        
        legnagyobb( new ArrayList<List<Product>>(kereskedoestermekMap.values()));
    }
    static int kiszamolas(List<Product> list){
        int osszeg = 0;
        for (Product p : list){
            osszeg = osszeg + p.productAmount * p.soldProduct;
        }
        return osszeg;
    }
    
    static void legnagyobb(List<List<Product>> list){
        Map<Integer, Integer> mennyisegMap = new HashMap<Integer, Integer>();
        for (List<Product> x : list) {
            for (Product y : x) {
                if(mennyisegMap.containsKey(y.getProductType())){
                    mennyisegMap.put(y.getProductType(), mennyisegMap.get(y.getProductType()) + y.getSoldProduct() * y.getProductAmount());
                }else{
                    mennyisegMap.put(y.getProductType(), y.getSoldProduct() * y.getProductAmount());
                }
            }
        }
        for(Entry<Integer, Integer> e : mennyisegMap.entrySet()) {
        	System.out.println("key: " +e.getKey() + ", value: " +e.getValue() );
        }
        
    }

}


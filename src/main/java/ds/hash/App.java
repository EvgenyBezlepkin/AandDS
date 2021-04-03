package ds.hash;

public class App {

    public static void main(String[] args) {

        HashTable ht = new HashTable();
        ht.insert(1);
        ht.insert(2);
        ht.insert(11);
        //ht.printHasTable();
        System.out.println(ht.find(11));
    }
}

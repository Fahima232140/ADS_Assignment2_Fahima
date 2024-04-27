
public class Main {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Initial List: " + list.toArray());

        list.add(1, 5);
        System.out.println("List after adding at index 1: " + list.toArray());

        list.remove(2);
        System.out.println("List after removing element at index 2: " + list.toArray());

        System.out.println("Size of the list: " + list.size());
        System.out.println("Element at index 1: " + list.get(1));
        System.out.println("Index of element 3: " + list.indexOf(3));
    }

}
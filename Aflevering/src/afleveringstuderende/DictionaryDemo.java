package afleveringstuderende;

public class DictionaryDemo {
    public static void main(String[] args) {
        DictionaryDoubleHashing dic = new DictionaryDoubleHashing<>(10);

        DictionaryDoubleHashing.Entry<Integer,String> entry1 = new DictionaryDoubleHashing.Entry<>(1,"Bo");
        DictionaryDoubleHashing.Entry<Integer,String> entry2 = new DictionaryDoubleHashing.Entry<>(2,"Bob");
        DictionaryDoubleHashing.Entry<Integer,String> entry3 = new DictionaryDoubleHashing.Entry<>(3,"Ole");
        DictionaryDoubleHashing.Entry<Integer,String> entry4 = new DictionaryDoubleHashing.Entry<>(1,"Kennnn");
        DictionaryDoubleHashing.Entry<Integer,String> entry5 = new DictionaryDoubleHashing.Entry<>(1,"Per");

        System.out.println(dic.size());

        dic.put(1, "Bo");
        dic.put(2, "Bob");
        dic.put(3, "Ole");
        dic.put(4, "Kennnnn");
        dic.put(5, "Per");

        dic.writeOut();
        System.out.println(dic.size());
        System.out.println();
        System.out.println(dic.get(1));
        System.out.println();
        dic.writeOut();
        System.out.println(dic.size());
        System.out.println();
        System.out.println(dic.put(5458, "Hejdi"));
        System.out.println();
        System.out.println(dic.get(11));
        System.out.println();
        System.out.println(dic.put(2, "Karen"));
        dic.writeOut();
        System.out.println(dic.size());
        System.out.println();
        System.out.println(dic.remove(15));
        System.out.println();
        dic.writeOut();
        System.out.println(dic.size());
    }
}

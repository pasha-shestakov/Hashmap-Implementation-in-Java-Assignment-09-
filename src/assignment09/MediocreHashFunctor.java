package assignment09;

public class MediocreHashFunctor implements HashFunctor{

    @Override
    public int hash(String item) {
        int hash = 0;
        for (int i = 0; i < item.length(); i++) {
            hash = 3 * hash + item.charAt(i);
        }
        return hash;
    }
}

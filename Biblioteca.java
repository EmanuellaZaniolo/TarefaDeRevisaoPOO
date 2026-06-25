import java.util.ArrayList;

public class Biblioteca<T> {
    private ArrayList<T> itens = new ArrayList<>();

    public void adicionar(T item) {
        itens.add(item);
    }

    public void listar() {
        for (T item : itens) {
            System.out.println("  -> " + item);
        }
    }
}
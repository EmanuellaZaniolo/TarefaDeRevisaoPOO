import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// Enum é uma lista fixa de constantes. Útil quando os valores
// possíveis são conhecidos e limitados.
enum StatusLivro {
    DISPONIVEL,
    EMPRESTADO,
    MANUTENCAO
}

// Record é uma classe simplificada para guardar dados.
// O Java gera automaticamente: construtor, getters, equals, hashCode e toString.
record Livro(String titulo, String autor, int ano) {}

// @Override avisa o compilador que estamos sobrescrevendo um método da superclasse.
// Se errarmos o nome do método, o compilador nos avisa — sem @Override, o erro passaria batido.
class Animal {
    public String som() {
        return "...";
    }
}

class Cachorro extends Animal {
    @Override
    public String som() {
        return "Au au!";
    }
}

// <T> significa que a classe aceita qualquer tipo.
// Assim, Biblioteca<Livro>, Biblioteca<String>, etc. funcionam.
class Biblioteca<T> {
    private ArrayList<T> itens = new ArrayList<>();

    public void adicionar(T item) {
        itens.add(item);
    }

    public void listar() {
        for (T item : itens) {
            System.out.println("  → " + item);
        }
    }
}


public class Blibloteca {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        StatusLivro status = StatusLivro.DISPONIVEL; // declarando variável do tipo enum
        System.out.println("Status do livro: " + status);
        System.out.println();

        System.out.println(" RECORD");

        Livro livro1 = new Livro("O Senhor dos Anéis", "Tolkien", 1954);
        Livro livro2 = new Livro("Clean Code", "Robert Martin", 2008);

        System.out.println(livro1); // o record já tem toString() automático
        System.out.println(livro2);
        System.out.println();

        System.out.println("WRAPPER E AUTOBOXING");

        // AUTOBOXING: o Java converte automaticamente o int 320
        // para um objeto Integer. Você não precisa escrever new Integer(320).
        Integer paginas = 320;
        System.out.println("Integer paginas = 320  →  autoboxing (int virou objeto Integer)");

        // UNBOXING: o Java converte automaticamente o Integer de volta para int primitivo.
        int x = paginas;
        System.out.println("int x = paginas        →  unboxing  (Integer virou int)");
        System.out.println("Valor de x: " + x);

        // Demonstração extra:
        Integer a = 10;       // autoboxing
        Integer b = 20;       // autoboxing
        int soma = a + b;     // unboxing automático para fazer a soma
        System.out.println("Soma (a + b): " + soma);
        System.out.println();


        // COLLECTIONS — ArrayList de Livros
        System.out.println("COLLECTIONS (ArrayList)");

        // Por que ArrayList em vez de vetor (array)?
        // → Array tem tamanho fixo. ArrayList cresce automaticamente.
        // → ArrayList tem métodos prontos: add(), remove(), contains()...
        ArrayList<Livro> acervo = new ArrayList<>();

        acervo.add(new Livro("Java: Como Programar", "Deitel", 2017));
        acervo.add(new Livro("O Hobbit", "Tolkien", 2012));
        acervo.add(new Livro("Algoritmos", "Cormen", 2009));
        acervo.add(new Livro("Effective Java", "Joshua Bloch", 2018));
        acervo.add(new Livro("Harry Potter", "J.K. Rowling", 1997));

        System.out.println("Acervo completo:");
        for (Livro l : acervo) {
            System.out.println("  " + l);
        }
        System.out.println();


        // 5. ITERATOR — removendo livros anteriores a 2015
        System.out.println("ITERATOR");

        // Iterator é a forma segura de remover elementos durante a iteração.
        // Se usarmos o for-each e chamarmos remove(), dá erro!
        Iterator<Livro> it = acervo.iterator();
        while (it.hasNext()) {
            Livro l = it.next();
            if (l.ano() < 2015) {
                it.remove(); // remove com segurança
            }
        }

        System.out.println("Acervo após remover livros anteriores a 2015:");
        for (Livro l : acervo) {
            System.out.println("  " + l);
        }
        System.out.println();
        // 6. GENERICS — por que ArrayList<int> não compila?
        System.out.println("GENERIC");

        // ArrayList<int> NÃO compila porque Generics só aceita tipos OBJETO (classes).
        // 'int' é tipo primitivo. A solução é usar o Wrapper: Integer.
        ArrayList<Integer> numeros = new ArrayList<>(); // CORRETO
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        System.out.println("ArrayList<Integer> (correto): " + numeros);
        System.out.println();


    
        // 7. TRATAMENTO DE EXCEÇÕES
        System.out.println("TRATAMENTO DE EXCEÇÕES");

        int anoDigitado = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("Digite o ano de publicação de um livro: ");
            try {
                // nextInt() lança InputMismatchException se o usuário digitar texto
                anoDigitado = scanner.nextInt();
                entradaValida = true; // só chega aqui se não deu exceção
            } catch (java.util.InputMismatchException e) {
                System.out.println("  ✗ Erro: digite apenas números! Tente novamente.");
                scanner.nextLine(); // limpa o buffer do scanner
            }
        }
        System.out.println("Ano informado: " + anoDigitado);
        System.out.println();


        // 8. DATAS — LocalDate

        System.out.println("  DATAS (LocalDate)");

        LocalDate hoje = LocalDate.now();
        System.out.println("Data de hoje: " + hoje);

        LocalDate publicacao = LocalDate.of(anoDigitado, 1, 1);
        int anosPassados = hoje.getYear() - publicacao.getYear();
        System.out.println("O livro foi publicado há aproximadamente " + anosPassados + " ano(s).");
        System.out.println();

        Animal animal = new Cachorro();
        System.out.println("Som do animal: " + animal.som());
        System.out.println();


        // 10. CLASSE GENÉRICA Biblioteca<T>
        System.out.println(" CLASSE GENÉRIA ");

        // Funcionando com Livro
        Biblioteca<Livro> bibLivros = new Biblioteca<>();
        bibLivros.adicionar(new Livro("Clean Code", "Robert Martin", 2008));
        bibLivros.adicionar(new Livro("Effective Java", "Joshua Bloch", 2018));
        System.out.println("Biblioteca de Livros:");
        bibLivros.listar();

        // O mesmo código funciona com String!
        Biblioteca<String> bibStrings = new Biblioteca<>();
        bibStrings.adicionar("Categoria: Tecnologia");
        bibStrings.adicionar("Categoria: Literatura");
        System.out.println("Biblioteca de Strings:");
        bibStrings.listar();

    }
}




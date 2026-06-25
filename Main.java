import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- ENUM ---
        System.out.println("--- ENUM ---");
        StatusLivro status = StatusLivro.DISPONIVEL;
        System.out.println("Status do livro: " + status + "\n");


        // --- RECORD ---
        System.out.println("--- RECORD ---");
        Livro livro1 = new Livro("O Senhor dos Anéis", "Tolkien", 1954);
        Livro livro2 = new Livro("Clean Code", "Robert Martin", 2008);
        System.out.println(livro1);
        System.out.println(livro2 + "\n");


        // --- WRAPPER E AUTOBOXING ---
        System.out.println("--- WRAPPER E AUTOBOXING ---");
        // a) Explicação da atribuição: 
        // Ocorreu o Autoboxing. O Java converte automaticamente o tipo primitivo int (320) 
        // para um objeto da classe Wrapper Integer.
        Integer paginas = 320; 
        
        // b) Explicação do int x = paginas: 
        // Ocorreu o Unboxing. O Java converte automaticamente o objeto Integer 
        // de volta para o tipo primitivo int.
        int x = paginas; 
        
        System.out.println("Autoboxing: Integer paginas = " + paginas);
        System.out.println("Unboxing: int x = " + x + "\n");


        // --- COLLECTIONS ---
        System.out.println("--- COLLECTIONS ---");
        // Por que utilizar ArrayList em vez de um vetor?
        // Resposta: Diferente de um vetor tradicional (array) que tem tamanho fixo, 
        // o ArrayList tem tamanho dinâmico (cresce automaticamente) e possui diversos 
        // métodos embutidos para manipulação de dados (add, remove, etc).
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


        // --- ITERATOR ---
        System.out.println("--- ITERATOR ---");
        Iterator<Livro> it = acervo.iterator();
        while (it.hasNext()) {
            Livro l = it.next();
            if (l.ano() < 2015) {
                it.remove();
            }
        }

        System.out.println("Acervo após remover livros anteriores a 2015:");
        for (Livro l : acervo) {
            System.out.println("  " + l);
        }
        System.out.println();


        // --- GENERICS ---
        System.out.println("--- GENERICS ---");
        // Por que ArrayList<int> não compila?
        // Resposta: Generics no Java só aceitam tipos de referência (Objetos). 
        // Como 'int' é um tipo primitivo, ocorre erro de compilação. É necessário usar a classe Wrapper (Integer).
        ArrayList<Integer> numeros = new ArrayList<>(); 
        numeros.add(1);
        System.out.println("ArrayList<Integer> (código corrigido): " + numeros + "\n");


        // --- TRATAMENTO DE EXCEÇÕES ---
        System.out.println("--- TRATAMENTO DE EXCEÇÕES ---");
        int anoDigitado = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print("Digite o ano de publicação de um livro: ");
            try {
                anoDigitado = scanner.nextInt();
                entradaValida = true; 
            } catch (InputMismatchException e) {
                System.out.println("Erro: digite apenas números! Tente novamente.");
                scanner.nextLine(); // Limpa o buffer
            }
        }
        System.out.println();


        // --- DATAS ---
        System.out.println("--- DATAS ---");
        LocalDate hoje = LocalDate.now();
        System.out.println("Data atual: " + hoje);

        LocalDate publicacao = LocalDate.of(anoDigitado, 1, 1);
        int anosPassados = hoje.getYear() - publicacao.getYear();
        System.out.println("O livro digitado foi publicado há aproximadamente " + anosPassados + " ano(s).\n");


        // --- ANNOTATIONS ---
        System.out.println("--- ANNOTATIONS ---");
        Animal animal = new Cachorro();
        System.out.println("Som da classe com @Override: " + animal.som() + "\n");


        // --- CLASSE GENÉRICA ---
        System.out.println("--- CLASSE GENÉRICA ---");
        Biblioteca<Livro> bibLivros = new Biblioteca<>();
        bibLivros.adicionar(new Livro("Design Patterns", "GoF", 1994));
        bibLivros.adicionar(new Livro("Clean Architecture", "Robert Martin", 2017));
        System.out.println("Biblioteca instanciada com tipo genérico:");
        bibLivros.listar();

        scanner.close();
    }
}
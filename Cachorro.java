public class Cachorro extends Animal {
    // A anotação @Override é útil pois avisa o compilador que estamos sobrescrevendo 
    // um método da superclasse. Se errarmos o nome do método ou a assinatura, o compilador gera um erro
    @Override
    public String som() {
        return "Au au!";
    }
}
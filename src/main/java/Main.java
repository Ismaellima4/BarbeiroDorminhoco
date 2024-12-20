public class Main {
    public static void main(String[] args) {
        // Cria a instância da barbearia
        Barbearia barbearia = new Barbearia();

        // Cria e inicia o barbeiro
        Barbeiro barbeiro = new Barbeiro(barbearia);
        barbearia.setBarbeiro(barbeiro);
        barbeiro.start();

        // Cria a instância de clientes
        for (int i = 1; i <= 5; i++) {
            Cliente cliente = new Cliente(i, barbearia);
            cliente.start();
        }
    }
}
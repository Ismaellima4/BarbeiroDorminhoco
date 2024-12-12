public class Main {
    public static void main(String[] args) {

        Barbearia barbearia = new Barbearia();
        barbearia.setBarbeiro(new Barbeiro(barbearia));

        // Cria e inicia a thread do barbeiro
        Barbeiro barbeiro =  barbearia.getBarbeiro();

        barbeiro.start();

        // Cria e adiciona os clientes à fila
        for (int i = 1; i <= 5; i++) {
            Cliente cliente = new Cliente(i);
            if (i == 1) {
                // Cliente 1 entra diretamente e corta o cabelo
                System.out.println("O barbeiro acordou!");
                barbeiro.cortarCabelo(cliente);
            } else {
                barbearia.adicionarCliente(cliente);
            }
        }
    }
}

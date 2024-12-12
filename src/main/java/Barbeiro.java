public class Barbeiro extends Thread {

    private final Barbearia barbearia;

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;
    }

    public void run() {
        do {
            try {
                // Aguarda até um cliente estar disponível
                barbearia.clientes.acquire();
                // Pega o próximo cliente da fila
                Cliente cliente = barbearia.filaClientes.take();
                // Libera o barbeiro para outro cliente
                barbearia.barbeiros.release();
                // Realiza o corte de cabelo
                cortarCabelo(cliente);

                if (barbearia.filaClientes.isEmpty()) { // Sem clientes o Barbeiro volta a dormir
                    System.out.println("O barbeiro voltou a dormir.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public void cortarCabelo(Cliente cliente) {
        try {
            System.out.println("Cliente " + cliente.getId() + " está cortando o cabelo.");
            Thread.sleep(5000); // Simula o tempo de corte de cabelo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

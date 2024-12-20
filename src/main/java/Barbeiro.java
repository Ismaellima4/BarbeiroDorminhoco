public class Barbeiro extends Thread {

    private final Barbearia barbearia;
    private boolean isSleep;

    public Barbeiro(Barbearia barbearia) {
        this.barbearia = barbearia;
        this.isSleep = true;
    }

    public void run() {
        do {
            try {
                // Pega o próximo cliente da fila
                Cliente cliente = this.barbearia.filaClientes.take();
                // Realiza o corte de cabelo
                cortarCabelo(cliente);

                if (this.barbearia.filaClientes.isEmpty()) { // Sem clientes o Barbeiro volta a dormir
                    this.setSleep(true);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!this.isSleep);
    }

    public void cortarCabelo(Cliente cliente) {
        try {
            System.out.println("Cliente " + cliente.getId() + " está cortando o cabelo.");
            Thread.sleep(2000); // Simula o tempo de corte de cabelo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isSleep() {
        return this.isSleep;
    }

    public void setSleep(boolean sleep) {
        this.isSleep = sleep;
        if(this.isSleep) System.out.println("O barbeiro voltou a dormir!");
        else System.out.println("O barbeiro acordou!");
    }
}

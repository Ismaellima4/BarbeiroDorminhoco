import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Barbearia {
    // Capacidade da barbearia
    public final int CAPACIDADE_BARBEARIA = 2;
    // Fila de clientes
    protected BlockingQueue<Cliente> filaClientes = new ArrayBlockingQueue<>(CAPACIDADE_BARBEARIA);
    // Semáforo para garantir exclusão mútua
    protected Semaphore mutex = new Semaphore(1);
    private Barbeiro barbeiro;

    public void entrarBarbearia(Cliente cliente) throws InterruptedException {
        // entra zona crítica
        this.mutex.acquire();
        //se o barbeiro estiver dormindo o primeiro cliente entra sem esperar na fila e acorda o barbeiro;
        if (this.barbeiro.isSleep()) {
            this.barbeiro.setSleep(false);
            this.barbeiro.cortarCabelo(cliente);
        } else {
            adicionarCliente(cliente);
        }
        //sai da zona crítica
        this.mutex.release();
    }

    private void adicionarCliente(Cliente cliente) throws InterruptedException {
        // Verifica se há espaço na fila
        if (this.filaClientes.size() < this.CAPACIDADE_BARBEARIA) {
            System.out.println("Cliente " + cliente.getId() + " esperando.");
            // Adiciona o cliente à fila
            this.filaClientes.put(cliente);
        } else {
            // Se a fila estiver cheia, o cliente sai
            System.out.println("Cliente " + cliente.getId() + " saiu porque não havia cadeiras disponíveis.");
        }

    }

    public Barbeiro getBarbeiro() {
        return this.barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }
}

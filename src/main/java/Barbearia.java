import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Barbearia {
    // Capacidade da barbearia: 1 cliente cortando e 2 esperando
    public final int CAPACIDADE_BARBEARIA = 3;
    // Fila de clientes com capacidade para 3
    protected BlockingQueue<Cliente> filaClientes = new ArrayBlockingQueue<>(CAPACIDADE_BARBEARIA);
    // Semáforo para contar os clientes esperando (inicia em 0)
    protected Semaphore clientes = new Semaphore(0, true);
    // Semáforo para contar os barbeiros disponíveis (apenas um barbeiro)
    protected Semaphore barbeiros = new Semaphore(1, true);
    // Semáforo para garantir exclusão mútua
    protected Semaphore mutex = new Semaphore(1, true);
    // Instância do barbeiro
    private Barbeiro barbeiro;

    public  void adicionarCliente(Cliente cliente) {
        try {
            // Entra na região crítica
            mutex.acquire();
            if (filaClientes.size() < CAPACIDADE_BARBEARIA - 1) { // Verifica se há espaço na fila
                // Adiciona o cliente à fila
                filaClientes.put(cliente);
                System.out.println("Cliente " + cliente.getId() + " esperando.");
                // Incrementa o contador de clientes (Semáforo)
                clientes.release();
            } else {
                // Se a fila estiver cheia, o cliente sai
                System.out.println("Cliente " + cliente.getId() + " saiu porque não havia cadeiras disponíveis.");
            }
            // Sai da região crítica
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }
}

public class Cliente extends Thread {
    private int id;
    private Barbearia barbearia;

    public Cliente(int id, Barbearia barbearia) {
        this.id = id;
        this.barbearia = barbearia;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public void run() {
        try {
            this.barbearia.entrarBarbearia(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

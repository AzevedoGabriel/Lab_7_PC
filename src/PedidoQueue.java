import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PedidoQueue {
    private BlockingQueue<Pedido> filaPedidos;

    public PedidoQueue(int capacidade) {
        this.filaPedidos = new LinkedBlockingQueue<>(capacidade);
    }

    public void adicionarPedido(Pedido pedido) throws InterruptedException {
        filaPedidos.put(pedido);
    }

    public Pedido pegarPedido() throws InterruptedException {
        return filaPedidos.take();
    }
}

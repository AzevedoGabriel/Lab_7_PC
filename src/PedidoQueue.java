import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PedidoQueue {
    private BlockingQueue<Pedido> filaPedidos;

    public PedidoQueue(int capacidade) {
        this.filaPedidos = new ArrayBlockingQueue<>(capacidade);
    }

    public boolean adicionarPedido(Pedido pedido) {
        return filaPedidos.offer(pedido);
    }

    public Pedido obterPedido() throws InterruptedException {
        return filaPedidos.take();
    }
}

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> produtosIniciais = new HashMap<>();
        produtosIniciais.put("Produto A", 100);
        produtosIniciais.put("Produto B", 150);
        produtosIniciais.put("Produto C", 200);

        Estoque estoque = new Estoque(produtosIniciais);

        PedidoQueue pedidoQueue = new PedidoQueue(10);

        RelatorioVendas relatorioVendas = new RelatorioVendas();

        Reabastecedor reabastecedor = new Reabastecedor(estoque);
        reabastecedor.iniciarReabastecimento(10, TimeUnit.SECONDS);

        int numWorkers = 3;
        for (int i = 0; i < numWorkers; i++) {
            new Thread(new PedidoWorker(pedidoQueue, estoque, relatorioVendas)).start();
        }

        relatorioVendas.iniciarRelatorio(30, TimeUnit.SECONDS);

        ScheduledExecutorService pedidoExecutor = Executors.newScheduledThreadPool(1);
        pedidoExecutor.scheduleAtFixedRate(() -> {
            Pedido pedido = new Pedido("Cliente " + System.currentTimeMillis(), 
                                        new ItemPedido("Produto A", 5), 
                                        new ItemPedido("Produto B", 3));
            boolean sucesso = pedidoQueue.adicionarPedido(pedido);

            if (sucesso) {
                System.out.println("Novo pedido adicionado à fila: " + pedido);
            } else {
                System.out.println("Fila cheia! Pedido não pode ser adicionado.");
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}

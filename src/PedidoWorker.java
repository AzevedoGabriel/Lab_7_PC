public class PedidoWorker implements Runnable {
    private PedidoQueue fila;
    private Estoque estoque;

    public PedidoWorker(PedidoQueue fila, Estoque estoque) {
        this.fila = fila;
        this.estoque = estoque;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Pedido pedido = fila.pegarPedido();
                processarPedido(pedido);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void processarPedido(Pedido pedido) {
        boolean todosDisponiveis = true;

        for (Item item : pedido.getItens()) {
            if (!estoque.verificarDisponibilidade(item.getNome(), item.getQuantidade())) {
                todosDisponiveis = false;
                break;
            }
        }

        if (todosDisponiveis) {
            for (Item item : pedido.getItens()) {
                estoque.atualizarEstoque(item.getNome(), item.getQuantidade());
            }
            System.out.println("Pedido processado: " + pedido);
        } else {
            System.out.println("Pedido rejeitado: " + pedido);
        }
    }
}

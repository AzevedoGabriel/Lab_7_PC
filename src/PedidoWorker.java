public class PedidoWorker implements Runnable {
    private PedidoQueue pedidoQueue;
    private Estoque estoque;
    private RelatorioVendas relatorioVendas;

    public PedidoWorker(PedidoQueue pedidoQueue, Estoque estoque, RelatorioVendas relatorioVendas) {
        this.pedidoQueue = pedidoQueue;
        this.estoque = estoque;
        this.relatorioVendas = relatorioVendas;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Pedido pedido = pedidoQueue.obterPedido();
                System.out.println("Processando pedido: " + pedido);

                boolean pedidoValido = true;
                for (ItemPedido item : pedido.getItens()) {
                    if (!estoque.verificarDisponibilidade(item.getProduto(), item.getQuantidade())) {
                        pedidoValido = false;
                        break;
                    }
                }

                if (pedidoValido) {
                    for (ItemPedido item : pedido.getItens()) {
                        estoque.atualizarEstoque(item.getProduto(), item.getQuantidade());
                    }
                    relatorioVendas.registrarPedidoProcessado(pedido.calcularValorTotal());
                    System.out.println("Pedido processado com sucesso: " + pedido);
                } else {
                    relatorioVendas.registrarPedidoRejeitado();
                    System.out.println("Pedido rejeitado: " + pedido);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

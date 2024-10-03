public class GeradorRelatorio implements Runnable {
    private PedidoQueue fila;
    private Estoque estoque;

    public GeradorRelatorio(PedidoQueue fila, Estoque estoque) {
        this.fila = fila;
        this.estoque = estoque;
    }

    @Override
    public void run() {
        // Implemente a lógica para gerar o relatório
        System.out.println("Gerando relatório...");
    }
}

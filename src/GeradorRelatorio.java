public class GeradorRelatorio implements Runnable {
    private PedidoQueue fila;
    private Estoque estoque;

    public GeradorRelatorio(PedidoQueue fila, Estoque estoque) {
        this.fila = fila;
        this.estoque = estoque;
    }

    @Override
    public void run() {
        System.out.println("Gerando relatório...");
    }
}

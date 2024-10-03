import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RelatorioVendas {
    private int totalPedidosProcessados;
    private double valorTotalVendas;
    private int totalPedidosRejeitados;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public RelatorioVendas() {
        this.totalPedidosProcessados = 0;
        this.valorTotalVendas = 0.0;
        this.totalPedidosRejeitados = 0;
    }

    public synchronized void registrarPedidoProcessado(double valorVenda) {
        totalPedidosProcessados++;
        valorTotalVendas += valorVenda;
    }

    public synchronized void registrarPedidoRejeitado() {
        totalPedidosRejeitados++;
    }

    public void iniciarRelatorio(long intervalo, TimeUnit unidade) {
        scheduler.scheduleAtFixedRate(this::gerarRelatorio, 0, intervalo, unidade);
    }

    private void gerarRelatorio() {
        System.out.println("------ Relatório de Vendas ------");
        System.out.println("Total de pedidos processados: " + totalPedidosProcessados);
        System.out.println("Valor total das vendas: R$ " + valorTotalVendas);
        System.out.println("Total de pedidos rejeitados: " + totalPedidosRejeitados);
        System.out.println("---------------------------------");
    }
}

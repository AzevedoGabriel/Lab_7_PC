import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Reabastecedor {
    private Estoque estoque;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Reabastecedor(Estoque estoque) {
        this.estoque = estoque;
    }

    public void iniciarReabastecimento(long intervalo, TimeUnit unidade) {
        scheduler.scheduleAtFixedRate(this::reabastecer, 0, intervalo, unidade);
    }

    private void reabastecer() {
        System.out.println("Reabastecendo o estoque...");
        
        int quantidadeReabastecimento = 50;
        estoque.reabastecerEstoque(quantidadeReabastecimento);
        
        System.out.println("Reabastecimento concluído!");
    }
}

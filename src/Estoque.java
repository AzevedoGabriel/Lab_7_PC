import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Estoque {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<String, Integer> produtos;

    public Estoque(Map<String, Integer> produtosIniciais) {
        this.produtos = produtosIniciais;
    }

    public void reabastecerEstoque(int quantidade) {
        lock.writeLock().lock();
        try {
            for (Map.Entry<String, Integer> entry : produtos.entrySet()) {
                String produto = entry.getKey();
                int quantidadeAtual = entry.getValue();
                produtos.put(produto, quantidadeAtual + quantidade); // Reabastece
            }
            System.out.println("Estoque reabastecido com " + quantidade + " unidades de cada produto.");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean verificarDisponibilidade(String produto, int quantidade) {
        lock.readLock().lock();
        try {
            return produtos.getOrDefault(produto, 0) >= quantidade;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void atualizarEstoque(String produto, int quantidade) {
        lock.writeLock().lock();
        try {
            produtos.put(produto, produtos.get(produto) - quantidade);
        } finally {
            lock.writeLock().unlock();
        }
    }
}

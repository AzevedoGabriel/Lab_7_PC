import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Estoque {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<String, Integer> produtos;

    public Estoque(Map<String, Integer> produtosIniciais) {
        this.produtos = produtosIniciais;
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

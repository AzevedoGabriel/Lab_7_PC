public class ItemPedido {
    private String produto;
    private int quantidade;
    private double preco;

    public ItemPedido(String produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = 10.0;
    }

    public String getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return produto + " - " + quantidade + " unidades";
    }
}

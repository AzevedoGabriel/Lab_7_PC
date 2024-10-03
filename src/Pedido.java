public class Pedido {
    private String cliente;
    private ItemPedido[] itens;

    public Pedido(String cliente, ItemPedido... itens) {
        this.cliente = cliente;
        this.itens = itens;
    }

    public ItemPedido[] getItens() {
        return itens;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getQuantidade() * item.getPreco();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Pedido do cliente: " + cliente;
    }
}

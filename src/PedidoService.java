import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class PedidoService {

    private static final Logger logger = Logger.getLogger(PedidoService.class.getName());
    private static final double DESCONTO_ESPECIAL = 0.1;

    public double calcularTotalPedido(double precoProduto, int quantidade, double desconto) {
        double total = precoProduto * quantidade;
        total = aplicarDesconto(total, desconto);
        if (total > 1000) {
            total = aplicarDesconto(total, DESCONTO_ESPECIAL);
        }
        return total;
    }

    private double aplicarDesconto(double valor, double percentual) {
        return valor - (valor * percentual);
    }

    public void salvarPedido(String cliente, String produto, int quantidade, double preco) {
        double total = calcularTotalPedido(preco, quantidade, 0.05);
        logPedido(cliente, produto, quantidade, total);
        persistirPedido(cliente, produto, quantidade, total);
    }

    private void logPedido(String cliente, String produto, int quantidade, double total) {
        logger.info(String.format("Pedido gerado: Cliente=%s, Produto=%s, Quantidade=%d, Total=%.2f",
                cliente, produto, quantidade, total));
    }

    private void persistirPedido(String cliente, String produto, int quantidade, double total) {
        try (FileWriter writer = new FileWriter("pedidos.txt", true)) {
            writer.write(cliente + ";" + produto + ";" + quantidade + ";" + total + "\n");
        } catch (IOException e) {
            logger.severe("Erro ao salvar pedido: " + e.getMessage());
        }
    }
}

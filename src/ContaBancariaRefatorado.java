import java.util.logging.Logger;

// Exemplo de código refatorado
public class ContaBancariaRefatorado {

    public class ContaBancaria {
        private static final Logger logger = Logger.getLogger(ContaBancaria.class.getName());
        private final String titular;
        private double saldo;

        public ContaBancaria(String titular, double saldoInicial) {
            if (saldoInicial < 0) throw new IllegalArgumentException("Saldo inicial não pode ser negativo");
            this.titular = titular;
            this.saldo = saldoInicial;
        }

        public void depositar(double valor) {
            validarValorPositivo(valor, "Depósito inválido");
            saldo += valor;
            registrarOperacao("Depósito", valor);
        }

        public void sacar(double valor) {
            validarValorPositivo(valor, "Saque inválido");
            if (valor > saldo) throw new IllegalArgumentException("Saldo insuficiente");
            saldo -= valor;
            registrarOperacao("Saque", valor);
        }

        public void transferir(ContaBancaria destino, double valor) {
            validarValorPositivo(valor, "Transferência inválida");
            if (valor > saldo) throw new IllegalArgumentException("Saldo insuficiente para transferência");
            this.sacar(valor);
            destino.depositar(valor);
            logger.info(String.format("Transferência concluída de %s para %s | Valor: %.2f",
                    this.titular, destino.titular, valor));
        }

        public double getSaldo() {
            return saldo;
        }

        private void registrarOperacao(String tipo, double valor) {
            logger.info(String.format("%s realizado | Titular: %s | Valor: %.2f | Saldo atual: %.2f",
                    tipo, titular, valor, saldo));
        }

        private void validarValorPositivo(double valor, String mensagemErro) {
            if (valor <= 0) throw new IllegalArgumentException(mensagemErro);
        }
    }
}

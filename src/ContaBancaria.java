// Outro exemplo de código legado gerado por IA
public class ContaBancaria {

    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso.");
            System.out.println("Novo saldo: " + saldo);
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso.");
            System.out.println("Novo saldo: " + saldo);
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    public void transferir(ContaBancaria destino, double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            destino.saldo += valor;
            System.out.println("Transferência realizada com sucesso.");
            System.out.println("Saldo atual: " + saldo);
        } else {
            System.out.println("Não foi possível realizar a transferência.");
        }
    }

    public void exibirSaldo() {
        System.out.println("Titular: " + titular + " | Saldo: " + saldo);
    }
}
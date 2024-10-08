import entities.Carro;
import entities.Moto;
import entities.Veiculo;
import service.VeiculoService;

import java.util.Scanner;

import Dudu.Dudu;

public class CadVeiculo {
    public static void main(String[] args){
        VeiculoService vs = new VeiculoService();
        while(true){
            Dudu.ShowMenu();
            String input = Dudu.getUserInput();
            while (!Dudu.isNumber(input)) {
                System.out.println("Digite um número dentro das opções acima.");
                input = Dudu.getUserInput();
            }
            Integer option = Integer.parseInt(input);
            if(option == 0){
                System.out.println("Saindo do sistema.");
                break;
            } else if(option >= 1 && option <= 4) {
                handleMenuOption(option, vs);
            } else {
                System.out.println("Digite um número dentro das opções acima.");
            }
        }
    }

    private static void handleMenuOption(int option, VeiculoService vs){
        switch (option) {
            case 1:
                Dudu.ShowMenuCadastro();
                String input = Dudu.getUserInput();
                while (!Dudu.isNumber(input) || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 2) {
                    System.out.println("Entrada inválida. Por favor, digite 1 para Carro ou 2 para Moto.");
                    input = Dudu.getUserInput();
                }
                int tipoVeiculo = Integer.parseInt(input);
                if (tipoVeiculo == 1) {
                    cadastrarCarro(vs);
                } else if (tipoVeiculo == 2) {
                    cadastrarMoto(vs);
                }
                waitForEnter();
                break;

            case 2:
                vs.listarVeiculos();
                waitForEnter();
                break;

            case 3:
                System.out.print("PESQUISA DE VEICULOS POR PLACA\nDigite a placa do veículo que deseja pesquisar: ");
                String placa = Dudu.getUserInput();
                Veiculo vc = vs.pesquisarVeiculoPorPlaca(placa);
                if(vc == null)
                    System.out.println("Veículo não encontrado com a placa informada!");
                else
                    System.out.println(vc);
                waitForEnter();
                break;

            case 4:
                System.out.println("REMOÇÃO DE VEICULOS");
                vs.listarVeiculos();
                System.out.print("Digite a placa do veículo que deseja remover: ");
                placa = Dudu.getUserInput();
                vs.removerVeiculo(placa);
                waitForEnter();
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarCarro(VeiculoService vs){
        try {
            System.out.print("Digite a marca do carro: ");
            String marca = Dudu.getUserInput();
            System.out.print("Digite o modelo do carro: ");
            String modelo = Dudu.getUserInput();
            System.out.print("Digite o ano do carro: ");
            String ano = Dudu.getUserInput();
            while (!Dudu.isValidYear(ano)) {
                System.out.print("Ano inválido. Digite um ano válido: ");
                ano = Dudu.getUserInput();
            }
            Integer anoInt = Integer.parseInt(ano);
            System.out.print("Digite a placa do carro: ");
            String placa = Dudu.getUserInput();
            System.out.print("Digite o número de portas: ");
            String nportas = Dudu.getUserInput();
            while (!Dudu.isNumber(nportas) || Integer.parseInt(nportas) <= 0) {
                System.out.print("Número de portas inválido. Digite um número válido de portas: ");
                nportas = Dudu.getUserInput();
            }
            Integer nportasInt = Integer.parseInt(nportas);
            vs.cadastrarVeiculo(new Carro(marca, modelo, anoInt, placa, nportasInt));
        } catch(Exception e){
            System.out.println("Erro ao cadastrar carro: " + e.getMessage());
        }
    }

    private static void cadastrarMoto(VeiculoService vs) {
        try {
            System.out.print("Digite a marca da moto: ");
            String marca = Dudu.getUserInput();
            System.out.print("Digite o modelo da moto: ");
            String modelo = Dudu.getUserInput();
            System.out.print("Digite o ano da moto: ");
            String ano = Dudu.getUserInput();
            while (!Dudu.isValidYear(ano)) {
                System.out.print("Ano inválido. Digite um ano válido: ");
                ano = Dudu.getUserInput();
            }
            Integer anoInt = Integer.parseInt(ano);
            System.out.print("Digite a placa da moto: ");
            String placa = Dudu.getUserInput();
            System.out.print("A moto tem partida elétrica? (true/false): ");
            String partidaEletricaInput = Dudu.getUserInput();
            while (!partidaEletricaInput.equalsIgnoreCase("true") && !partidaEletricaInput.equalsIgnoreCase("false")) {
                System.out.print("Entrada inválida. A moto tem partida elétrica? (true/false): ");
                partidaEletricaInput = Dudu.getUserInput();
            }
            Boolean partidaEletrica = Boolean.parseBoolean(partidaEletricaInput);
            vs.cadastrarVeiculo(new Moto(marca, modelo, anoInt, placa, partidaEletrica));
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar moto: " + e.getMessage());
        }
    }

    private static void waitForEnter() {
        System.out.println("Digite enter para voltar ao menu principal");
        new Scanner(System.in).nextLine();
    }
}

package modelo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculadoraIMC { //calcula o imc para a interpretadora transformar em string
    public static void calcularIMC(Aluno aluno) {
         double imc = aluno.getPeso() / (aluno.getAltura() * aluno.getAltura());
        String interpretacao = interpretarIMC(imc);
        gravarResultado(aluno, imc, interpretacao);
    }
    
    //

   private static String interpretarIMC(double imc) {
    if (imc < 18.5) {
        return "Abaixo do peso";
    } else if (imc >= 18.5 && imc < 25) {
        return "Peso normal";
    } else if (imc >= 25 && imc < 30) {
        return "Sobrepeso";
    } else if (imc >= 30 && imc < 35) {
        return "Obesidade Grau I";
    } else if (imc >= 35 && imc < 40) {
        return "Obesidade Grau II (severa)";
    } else {
        return "Obesidade Grau III (mórbida)";
    }
}

    private static void gravarResultado(Aluno aluno, double imc, String interpretacao) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = formatter.format(new Date());
        String conteudo = dataFormatada + " | CPF: " + aluno.getCpf() + " | Nome: " + aluno.getNome() +
                " | IMC: " +  (int)Math.round(imc) + " | Interpretacao: " + interpretacao + "\n";

        try {
            FileWriter writer = new FileWriter("resultado_imc.txt", true);
            writer.write(conteudo);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void removerResultado(String cpf) { 
        List<String> linhas = new ArrayList<>();
        String linhaAtual;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("resultado_imc.txt"));

            while ((linhaAtual = reader.readLine()) != null) {
                if (!linhaAtual.contains("CPF: " + cpf)) {
                    linhas.add(linhaAtual);
                }
            }
            reader.close();

            FileWriter writer = new FileWriter("resultado_imc.txt");

            for (String linha : linhas) {
                writer.write(linha + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMenu implements ActionListener {
    private JFrame frame;
    private JPanel panel;

    public GUIMenu() {
        frame = new JFrame("Menu do Cadastro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        
        // Cria um JPanel para o título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
         // Adiciona uma borda ao JPanel

        // Cria um JLabel para o texto "MENU"
        JLabel menuLabel = new JLabel("MENU", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(menuLabel); // Adiciona o JLabel ao JPanel

        frame.add(titlePanel, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 0, 10)); // Define o layout do painel como uma grade de 3 linhas e 1 coluna, com espaçamento vertical de 10 pixels
        //primeiro botao

        JButton button1 = new JButton("Cadastrar aluno");
        button1.addActionListener(this);
        panel.add(button1);
        //segundo botao
        JButton button2 = new JButton("Historico Peso");
        button2.addActionListener(this);
        panel.add(button2);
        //terceiro botao
        JButton button3 = new JButton("Sair");
        button3.addActionListener(this);
        panel.add(button3);

        // Adicionando uma borda ao painel com espaçamento
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10 pixels de espaçamento em todas as direções

        frame.add(panel, BorderLayout.CENTER);
        
        // Centraliza a janela na tela
    frame.setLocationRelativeTo(null);

        // Torna a janela visível
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Cadastrar aluno")) {
            AlunoGUI alunoGUI = new AlunoGUI();
            alunoGUI.setVisible(true);
            frame.dispose();
        } else if (command.equals("Historico Peso")) {
            GUIMenuPeso menupeso = new GUIMenuPeso();
            //ConsultarHistoricoPesoGUI consulta=new ConsultarHistoricoPesoGUI();
            frame.dispose();
        } else if (command.equals("Sair")) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIMenu();
            }
        });
    }


}

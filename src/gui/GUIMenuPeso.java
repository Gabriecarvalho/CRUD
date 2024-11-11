package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMenuPeso implements ActionListener {
    private JFrame frame;
    private JPanel panel;

    public GUIMenuPeso() {
        frame = new JFrame("Historico de peso");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        
        // Cria um JPanel para o título
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
         // Adiciona uma borda ao JPanel

        // Cria um JLabel para o texto "MENU"
        JLabel menuLabel = new JLabel("Menu Peso", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(menuLabel); // Adiciona o JLabel ao JPanel

        frame.add(titlePanel, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 0, 10)); // Define o layout do painel como uma grade de 3 linhas e 1 coluna, com espaçamento vertical de 10 pixels
        //primeiro botao

        JButton button1 = new JButton("Cadastrar/Excluir peso");
        button1.addActionListener(this);
        panel.add(button1);
        //segundo botao
        JButton button2 = new JButton("Consultar Historico de peso");
        button2.addActionListener(this);
        panel.add(button2);
        //terceiro botao
        JButton button3 = new JButton("Excluir peso por id");
        button3.addActionListener(this);
        panel.add(button3);
        
        JButton button4 = new JButton("Voltar");
        button4.addActionListener(this);
        panel.add(button4);

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

        if (command.equals("Cadastrar/Excluir peso")) {
            HistoricoPesoGUI cadastro = new HistoricoPesoGUI();
            cadastro.setVisible(true);
            frame.dispose();
        } else if (command.equals("Consultar Historico de peso")) {
            ConsultarHistoricoPesoGUI consulta=new ConsultarHistoricoPesoGUI();
            frame.dispose();
        } else if(command.equals("Excluir peso por id")){
            DeletarID delete = new DeletarID();
            delete.setVisible(true);
            frame.dispose();
            
        } else if (command.equals("Voltar")) {
            GUIMenu menu = new GUIMenu();
            frame.dispose();
        }
    }
}

package gui;



import dao.AlunoDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.HistoricoPesoDAO;
import javax.swing.JOptionPane;

import modelo.HistoricoPeso;

public class ConsultarHistoricoPesoGUI extends JFrame {
    private JTextField cpfAlunoTextField;
    private JButton consultarButton;
    private JButton limparFiltroButton;
    private JTable tabelaHistoricoPeso;
    private JScrollPane scrollPane;
    private JButton voltarButton;

    private HistoricoPesoDAO historicoPesoDAO;

    public ConsultarHistoricoPesoGUI() {
        setTitle("Consultar Histórico de Peso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        historicoPesoDAO = new HistoricoPesoDAO();

        cpfAlunoTextField = new JTextField(20);
        consultarButton = new JButton("Consultar");
        limparFiltroButton = new JButton("Limpar Filtro");
        tabelaHistoricoPeso = new JTable();
        scrollPane = new JScrollPane(tabelaHistoricoPeso);
        voltarButton = new JButton("Voltar");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        //a borda de cima
        JPanel consultaPanel = new JPanel();
        consultaPanel.add(new JLabel("CPF do Aluno:"));
        consultaPanel.add(cpfAlunoTextField);
        consultaPanel.add(consultarButton);
        consultaPanel.add(limparFiltroButton);

        mainPanel.add(consultaPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(voltarButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarHistoricoPeso();
            }
        });

        limparFiltroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpfAlunoTextField.setText("");   
                limparTabela();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaMainGUI();
            }
        });

  

        setVisible(true);
    }
    
    
    private void consultarHistoricoPeso() {// faz a consulta e o historico filtrado é os pesos do aluno em questao
        String cpfAluno = cpfAlunoTextField.getText();
        AlunoDAO AlunoDAO = new AlunoDAO();
        String[] dados = AlunoDAO.consulta(cpfAlunoTextField.getText());
        if (cpfAlunoTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Coloque algum cpf para consulta.");
        }else if (dados[4].equals("")){
            JOptionPane.showMessageDialog(null, "esse cpf não consta no banco de dados.");
        }else{
            List<HistoricoPeso> historicosFiltrados = historicoPesoDAO.consultarPorCpf(cpfAluno);
            popularTabela(historicosFiltrados);
        }
        
    }

    private void popularTabela(List<HistoricoPeso> historicos) {
        DefaultTableModel model = new DefaultTableModel();//completa a tabela com os dados pegos do historico filtrado
        model.addColumn("ID");
        model.addColumn("CPF Aluno");
        model.addColumn("Data");
        model.addColumn("Peso");
        model.addColumn("Nome");

        for (HistoricoPeso historico : historicos) {
            Object[] rowData = new Object[5];
            rowData[0] = historico.getId();
            rowData[1] = historico.getCpfAluno();
            rowData[2] = historico.getData();
            rowData[3] = historico.getPeso();
            rowData[4] = historico.getNomeAluno();
            model.addRow(rowData);
        }

        tabelaHistoricoPeso.setModel(model);
    }
    
    private void limparTabela() {
    DefaultTableModel model = (DefaultTableModel) tabelaHistoricoPeso.getModel();
    model.setRowCount(0); // Remove todas as linhas da tabela
}

    private void voltarParaMainGUI() {
        GUIMenuPeso menupeso = new GUIMenuPeso();
        dispose(); // Fecha a instância atual de ConsultarHistoricoPesoGUI
    }
}
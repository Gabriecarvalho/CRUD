/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import factory.ConnectionFactory;
import java.sql.*;

import modelo.Aluno;

/**
 * nesse pacote se mexe/insere e deleta do banco de dados
 * 
 * @author Manhã
 */
public class AlunoDAO {
    private Connection connection;

    public AlunoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Aluno aluno) {
    String sqlAluno = "INSERT INTO Aluno(cpf, nome, data_nasc, peso, altura) VALUES(?, ?, ?, ?, ?)";
    String sqlHistoricoPeso = "INSERT INTO HistoricoPeso(cpfAluno, data, peso, nomeAluno) VALUES(?, ?, ?, ?)";

    try {
        PreparedStatement stmtAluno = connection.prepareStatement(sqlAluno);
        stmtAluno.setString(1, aluno.getCpf());
        stmtAluno.setString(2, aluno.getNome());
        stmtAluno.setString(3, aluno.getDataNascimento());
        stmtAluno.setDouble(4, aluno.getPeso());
        stmtAluno.setDouble(5, aluno.getAltura());
        stmtAluno.execute();
        stmtAluno.close();

        // Obtendo a data atual
        java.util.Date dataAtual = new java.util.Date();
        java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());

        PreparedStatement stmtHistoricoPeso = connection.prepareStatement(sqlHistoricoPeso);
        stmtHistoricoPeso.setString(1, aluno.getCpf());
        stmtHistoricoPeso.setDate(2, dataSQL);
        stmtHistoricoPeso.setDouble(3, aluno.getPeso());
        stmtHistoricoPeso.setString(4, aluno.getNome());
        stmtHistoricoPeso.execute();
        stmtHistoricoPeso.close();

    } catch (SQLException u) {
        throw new RuntimeException(u);
    }
}

    public void remove(String cpf) {
        String sql = "DELETE FROM aluno WHERE cpf=? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cpf);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void atualizar(Aluno aluno) {
    String sqlUpdateAluno = "UPDATE Aluno SET nome=?, data_nasc=?, peso=?, altura=? WHERE cpf=?";
    String sqlInsertHistoricoPeso = "INSERT INTO HistoricoPeso(cpfAluno, data, peso, nomeAluno) VALUES(?, ?, ?, ?)";

    try {
        // Atualizando o aluno na tabela "Aluno"
        PreparedStatement stmtUpdateAluno = connection.prepareStatement(sqlUpdateAluno);
        stmtUpdateAluno.setString(1, aluno.getNome());
        stmtUpdateAluno.setString(2, aluno.getDataNascimento());
        stmtUpdateAluno.setDouble(3, aluno.getPeso());
        stmtUpdateAluno.setDouble(4, aluno.getAltura());
        stmtUpdateAluno.setString(5, aluno.getCpf());
        stmtUpdateAluno.execute();
        stmtUpdateAluno.close();

        // Adicionando um registro no histórico de peso na tabela "HistoricoPeso"
        // Obtendo a data atual
        java.util.Date dataAtual = new java.util.Date();
        java.sql.Date dataSQL = new java.sql.Date(dataAtual.getTime());

        PreparedStatement stmtInsertHistoricoPeso = connection.prepareStatement(sqlInsertHistoricoPeso);
        stmtInsertHistoricoPeso.setString(1, aluno.getCpf());
        stmtInsertHistoricoPeso.setDate(2, dataSQL);
        stmtInsertHistoricoPeso.setDouble(3, aluno.getPeso());
        stmtInsertHistoricoPeso.setString(4, aluno.getNome());
        stmtInsertHistoricoPeso.execute();
        stmtInsertHistoricoPeso.close();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}


    public String[] consulta(String cpf) {
        String sql = "SELECT nome, data_nasc, altura, peso, cpf FROM aluno WHERE cpf = ?";
        String[] dados = new String[5];
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cpf);

            ResultSet resultSet = statement.executeQuery();
            // Verifica se há resultados
            if (resultSet.next()) {
                dados[0] = resultSet.getString("nome");
                dados[1] = resultSet.getString("data_nasc");
                dados[2] = resultSet.getString("altura");
                dados[3] = resultSet.getString("peso");
                dados[4] = resultSet.getString("cpf");

            } else {
                dados[0] = "";
                dados[1] = "";
                dados[2] = "";
                dados[3] = "";
                dados[4] = "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dados;
    }
}

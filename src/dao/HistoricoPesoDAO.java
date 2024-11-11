
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import modelo.HistoricoPeso;

public class HistoricoPesoDAO {
    private Connection connection;

    public HistoricoPesoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void inserir(HistoricoPeso historicoPeso) {
        String sql = "INSERT INTO historicopeso (cpfaluno, data, peso,nomeAluno) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, historicoPeso.getCpfAluno());
            stmt.setDate(2, new java.sql.Date(historicoPeso.getData().getTime()));
            stmt.setDouble(3, historicoPeso.getPeso());
            stmt.setString(4, historicoPeso.getNomeAluno());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(String cpf) {
        String sql = "DELETE FROM historicopeso WHERE cpfAluno=? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cpf);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
    public void removeID(String id) {
        String sql = "DELETE FROM historicopeso WHERE id=? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public List<HistoricoPeso> consultarPorCpf(String cpfAluno) {
        List<HistoricoPeso> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historicopeso WHERE cpfAluno = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpfAluno);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HistoricoPeso historicoPeso = new HistoricoPeso(
                        rs.getInt("id"),
                        rs.getString("cpfAluno"),
                        rs.getDate("data"),
                        rs.getDouble("peso"),
                        rs.getString("nomeAluno"));
                historicos.add(historicoPeso);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicos;
    }

}
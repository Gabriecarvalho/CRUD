/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;
import java.sql.Connection;//conexao sql para java
import java.sql.DriverManager;// driver de conexao sql para java
import java.sql.SQLException;// classe para tratamento de exceçoes

/**
 *
 * @author Manhã
 */
public class ConnectionFactory {
    public Connection getConnection(){
          try{
              return DriverManager.getConnection("jdbc:mysql://localhost/alunos","root","fatec");// usuario root, a senha é fatec, e o primeiro é a rota
          }
          catch(SQLException excecao){
              throw new RuntimeException(excecao);
          }
    }
}
        
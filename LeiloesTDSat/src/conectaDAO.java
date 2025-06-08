
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO implements AutoCloseable {

    // Objeto de conexão com o banco
    private Connection conexao;

    /**
     * Retorna a conexão atual com o banco de dados.
     * @return 
     */
    public Connection getConexao() {
        return conexao;
    }

    /**
     * Realiza a conexão com o banco de dados MySQL.
     * Lança SQLException para que o erro possa ser tratado por quem chamou.
     * @throws java.sql.SQLException
     */
    public void conectar() throws SQLException {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco: endereço, usuário e senha
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/uc11?useSSL=false", "root", "");

        } catch (ClassNotFoundException cn) {
            // Caso o driver JDBC não seja encontrado
            System.err.println("Driver JDBC não encontrado: " + cn.getMessage());
        } catch (SQLException sql) {
            // Problemas ao conectar (usuário, senha, nome do banco, etc)
            throw sql; // repassa o erro para quem chamou a conexão
        }
    }

    /**
     * Fecha a conexão com o banco de dados, se estiver aberta.
     * @throws java.sql.SQLException
     */
    public void desconectar() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }

    /**
     * Implementação do método da interface AutoCloseable.
     * Permite que a conexão seja fechada automaticamente ao sair de um bloco try-with-resources.
     * @throws java.sql.SQLException
     */
    @Override
    public void close() throws SQLException {
        desconectar();
    }
    
}

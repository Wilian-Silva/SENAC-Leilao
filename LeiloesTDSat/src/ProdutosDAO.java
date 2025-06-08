/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    
      public static boolean cadastrarProduto (ProdutosDTO p) {
        // Consulta SQL para inserir um novo filme
        String sql = "INSERT INTO produtos (nome, valor, status ) VALUES (?, ?, ?)";

        // try-with-resources garante o fechamento automático da conexão
        try (conectaDAO conn = new conectaDAO()) {
            // Abre a conexão com o banco
            conn.conectar();

            // Prepara a instrução SQL
            PreparedStatement ps = conn.getConexao().prepareStatement(sql);

            // Define os valores dos parâmetros com os dados do objeto 'cliente'
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getValor());
            ps.setString(3, p.getStatus());
           

            // Executa a inserção e retorna o número de linhas afetadas
            int linhasAfetadas = ps.executeUpdate();

            // Retorna true se ao menos uma linha foi inserida
            return linhasAfetadas > 0;

        } catch (SQLException se) {
            System.err.println("Driver JDBC não encontrado: " + se);
            return false;
        }

    }
   
 
    
    
    //public void cadastrarProduto (ProdutosDTO produto){
        
        
        //conn = new conectaDAO().connectDB();
        
        
        
        
    //}
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}


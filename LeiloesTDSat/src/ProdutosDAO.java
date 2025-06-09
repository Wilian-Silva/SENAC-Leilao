
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
   
 
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            //Armazenamos a query SQL em uma string
            //String sql = "SELECT id, nome, cpf, endereco, telefone, especialidade, convenioId FROM Medico";
            String sql = "SELECT * FROM produtos";

            //Preparamos o comando para ser executado no banco
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

           while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
 

                listagem.add(p);
            }

            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar Produtos: " + se.getMessage());
        }

        
        
        
        return listagem;
    }

     public static boolean venderProduto(int p) {
        
         String sql = "UPDATE produtos SET status = 'Vendido' WHERE id=?";

        // try-with-resources garante o fechamento automático da conexão
        try (conectaDAO conn = new conectaDAO()) {
            // Abre a conexão com o banco
            conn.conectar();

            // Prepara a instrução SQL
            PreparedStatement ps = conn.getConexao().prepareStatement(sql);

            // Define os valores dos parâmetros com os dados do objeto 'cliente'
            ps.setInt(1, p);                 

            // Executa a inserção e retorna o número de linhas afetadas
            int linhasAfetadas = ps.executeUpdate();

            // Retorna true se ao menos uma linha foi inserida
            return linhasAfetadas > 0;

        } catch (SQLException se) {
            System.err.println("Erro: " + se);
            return false;
        }
        
    }
    
    
    
        
}


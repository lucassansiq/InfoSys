/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

/**
 *
 * @author Lucas Hype
 */
public class ProdutoDAO implements Persistencia<Produto>{
    
   private static ProdutoDAO dao = null ;
   
   public ProdutoDAO(){
       
   }
   
   public static ProdutoDAO getInstance(){
       if(dao  == null) dao = new ProdutoDAO();
       
       return dao;
   }

    @Override
    public int create(Produto c) {
        int id = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "INSERT INTO Produtos(Descricao,Ativo,Estoque,Custo,Valor) VALUES"
                + "(?,?,?,?,?)";
        try{
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, c.getDescricao());
            pst.setBoolean(2, c.isAtivo());
            pst.setInt(3, c.getEstoque());
            pst.setDouble(4, c.getCusto());
            pst.setDouble(5, c.getVenda());
            pst.execute();
            rs = pst.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
        }catch(SQLException ex){
            id = 0;
        }finally{
            ConnectionFactory.closeConnection(con,pst,rs);
        }
        
        return id;
    }

    @Override
    public Produto findByCodigo(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Produto c = null;
        String sql = "SELECT * FROM Produtos where Codigo = ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                int codigo = rs.getInt("Codigo");
                String descricao = rs.getString("Descricao");
                boolean ativo = rs.getBoolean("Ativo");
                int estoque = rs.getInt("Estoque");
                double custo = rs.getDouble("Custo");
                double valor = rs.getDouble("Valor");
                c = new Produto(codigo,descricao,ativo,estoque,custo,valor);
            }
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
        }finally{
            ConnectionFactory.closeConnection(con,pst,rs);
        }         
        return c;
    }

    @Override
    public void delete(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "DELETE from Produtos where Codigo = ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();    
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Delete");
        }finally{
            ConnectionFactory.closeConnection(con,pst,rs);
        }
    }

    @Override
        public void update(Produto c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "UPDATE Produtos set Descricao = ?,Ativo = ?,Estoque = ?,Custo=?,Valor=? where Codigo = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, c.getDescricao());
            pst.setBoolean(2, c.isAtivo());
            pst.setInt(3, c.getEstoque());
            pst.setDouble(4, c.getCusto());
            pst.setDouble(5, c.getVenda());
            pst.setInt(6, c.getCodigo());
            pst.execute();
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Delete");
        }finally{
            ConnectionFactory.closeConnection(con,pst,rs);
        }
    }

    @Override
    public List<Produto> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Produto c = null;
        String sql = "SELECT * FROM Produtos ORDER BY Descricao";
        List<Produto> lista = new ArrayList<Produto>();
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                String descricao = rs.getString("Descricao");
                boolean ativo = rs.getBoolean("Ativo");
                int estoque = rs.getInt("Estoque");
                double custo = rs.getDouble("Custo");
                double valor = rs.getDouble("Valor");
                lista.add(new Produto(descricao,ativo,estoque,custo,valor));
            }
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return lista;
    }
}



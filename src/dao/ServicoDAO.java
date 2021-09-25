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
import model.Servico;

/**
 *
 * @author Lucas Hype
 */
public class ServicoDAO implements dao.Persistencia<Servico> {

    private static ServicoDAO dao = null ;
    
    public ServicoDAO(){
        
    }
    
    public static ServicoDAO getInstance(){
        if(dao == null) dao = new ServicoDAO();
        
        return dao;
    }
    
    
    
    @Override
    public int create(Servico c) {
        int id = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "INSERT INTO Servicos(Descricao, Tecnico ,Valor) VALUES"
                + "(?,?,?)";
        try{
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, c.getDescricao());
            pst.setString(2, c.getTecnico());
            pst.setDouble(3, c.getValor());
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
    public Servico findByCodigo(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Servico c = null;
        String sql = "SELECT * FROM Servicos where Codigo = ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                int codigo = rs.getInt("Codigo");
                String descricao = rs.getString("Descricao");
                String Tecnico = rs.getString("Tecnico");
                double valor = rs.getDouble("Valor");
                c = new Servico(codigo,descricao,Tecnico,valor);
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
        String sql = "DELETE from Servicos where Codigo = ?";
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
    public void update(Servico c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "UPDATE Servicos set Descricao = ?,Tecnico = ?,Valor = ? where Codigo = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, c.getDescricao());
            pst.setString(2, c.getTecnico());
            pst.setDouble(3, c.getValor());
            pst.setInt(4, c.getCodigo());
            pst.execute();
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Delete");
        }finally{
            ConnectionFactory.closeConnection(con,pst,rs);
        }
    }

    @Override
    public List<Servico> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Servico c = null;
        String sql = "SELECT * FROM Servicos ORDER BY Descricao";
        List<Servico> lista = new ArrayList<Servico>();
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                String descricao = rs.getString("Descricao");
                String Tecnico = rs.getString("Tecnico");
                double valor = rs.getDouble("Valor");
                lista.add(new Servico(descricao,Tecnico,valor));
            }
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return lista;
    }

}

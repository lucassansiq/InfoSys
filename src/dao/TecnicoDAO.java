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
import model.Tecnico;

/**
 *
 * @author Lucas Hype
 */
public class TecnicoDAO implements dao.Persistencia<Tecnico> {

    private static TecnicoDAO dao = null ;
    
    public TecnicoDAO(){
        
    }
    
    public static TecnicoDAO getInstance(){
        if(dao == null) dao = new TecnicoDAO();
        
        return dao;
    }
    
    
    
    @Override
    public int create(Tecnico c) {
        int id = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "INSERT INTO Tecnicos(Nome, Salario, Vl_Hora) VALUES"
                + "(?,?,?)";
        try{
            pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, c.getNome());
            pst.setDouble(2, c.getSalario());
            pst.setDouble(3, c.getValor_hora());
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
    public Tecnico findByCodigo(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Tecnico c = null;
        String sql = "SELECT * FROM Tecnicos where Codigo = ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                int codigo = rs.getInt("Codigo");
                String nome = rs.getString("Nome");
                double Salario = rs.getDouble("Salario");
                double valor = rs.getDouble("Vl_Hora");
                c = new Tecnico(codigo,nome,Salario,valor);
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
        String sql = "DELETE from Tecnicos where Codigo = ?";
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
    public void update(Tecnico c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "UPDATE Tecnicos set Nome = ?,Salario = ?,Vl_Hora = ? where Codigo = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, c.getNome());
            pst.setDouble(2, c.getSalario());
            pst.setDouble(3, c.getValor_hora());
            pst.setInt(4, c.getCodigo());
            pst.execute();
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Delete");
        }finally{
            ConnectionFactory.closeConnection(con,pst,rs);
        }
    }

    @Override
    public List<Tecnico> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Tecnico c = null;
        String sql = "SELECT * FROM Tecnicos ORDER BY Nome";
        List<Tecnico> lista = new ArrayList<Tecnico>();
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                String nome = rs.getString("Nome");
                double Salario = rs.getDouble("Salario");
                double valor = rs.getDouble("Vl_Hora");
                lista.add(new Tecnico(nome,Salario,valor));
            }
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro no Select");
        }finally{
            ConnectionFactory.closeConnection(con, pst, rs);
        }
        return lista;
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.ClienteDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas Hype
 */
public class Cliente {
    private int codigo = 0;
    private String Nome,CPF,Telefone,Celular,Email = null;

    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    public Cliente(String Nome, String CPF, String Telefone, String Celular, String Email) {
        setNome(Nome);
        setCPF(CPF);
        setTelefone(Telefone);
        setCelular(Celular);
        setEmail(Email);
        gravar();
    }
    
    
    public Cliente(int codigo,String Nome, String CPF, String Telefone, String Celular, String Email) {
        setCodigo(codigo);
        setNome(Nome);
        setCPF(CPF);
        setTelefone(Telefone);
        setCelular(Celular);
        setEmail(Email); 
    }
    public Cliente(){
        
    }
    
    @Override
    public String toString(){
        String ret = null;
        ret = "Cliente.:[" + getNome() + "]\n" +
              "CPF.....:[" + getCPF() + "]\n" +
              "Telefone:[" + getTelefone() + "]\n" +
              "Celular.:[" + getCelular() + "]\n" +
              "Email...:[" + getEmail() + "]\n";
        return ret;
    }
    
    private void gravar(){
        ClienteDAO dao = new ClienteDAO();
        int codigo = dao.create(this);
        if(codigo > 0) setCodigo(codigo);
    }
    
    public static DefaultTableModel getTableModel(){
        List<Cliente> lista = ClienteDAO.getInstance().read();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Telefone");
        modelo.addColumn("Celular");
        modelo.addColumn("Email");
        for (Cliente c: lista){
            String[] reg = {c.getNome(),c.getTelefone(),c.getCelular(),c.getEmail()};
            modelo.addRow(reg);
        }
        return modelo;
    }

    
}

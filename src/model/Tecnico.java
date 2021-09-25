/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.TecnicoDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas Hype
 */
public class Tecnico {
    
    private int codigo = 0;
    private String nome;
    private double salario,valor_hora;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getValor_hora() {
        return valor_hora;
    }

    public void setValor_hora(double valor_hora) {
        this.valor_hora = valor_hora;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Tecnico(String nome, double salario, double valor_hora) {
        setNome(nome);
        setSalario(salario);
        setValor_hora(valor_hora);
        gravar();
    }
    public Tecnico(int codigo,String nome, double salario, double valor_hora) {
        setCodigo(codigo);
        setNome(nome);
        setSalario(salario);
        setValor_hora(valor_hora);
    }
    
    public Tecnico(){}
    
    @Override
    public String toString(){
        String ret = null;
        
        ret = "Nome..........:" + getNome() + "\n" +
              "Salario.......:" + getSalario() + "\n" +
              "Valor da Hora.:" + getValor_hora(); 
        
        return ret;
    }
    
    private void gravar(){
        TecnicoDAO dao = new TecnicoDAO();
        int codigo = dao.create(this);
        if(codigo > 0) setCodigo(codigo);
    }
    
    public static DefaultTableModel getTableModel(){
        List<Tecnico> lista = TecnicoDAO.getInstance().read();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Salario");
        modelo.addColumn("Valor_Hora");
        for (Tecnico c: lista){
            String[] reg = {c.getNome(),String.valueOf(c.getSalario()),String.valueOf(c.getValor_hora())};
            modelo.addRow(reg);
        }
        return modelo;
    }
    
}

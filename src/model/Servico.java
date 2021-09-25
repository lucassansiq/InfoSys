/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.ClienteDAO;
import dao.ServicoDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas Hype
 */
public class Servico {
    
    private int codigo = 0;
    private String descricao, tecnico;
    private double valor;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Servico(String descricao, String tecnico, double valor) {
        setDescricao(descricao);
        setTecnico(tecnico);
        setValor(valor);
        gravar();
    }
    
    
    public Servico(int codigo,String descricao, String tecnico, double valor) {
        setCodigo(codigo);
        setDescricao(descricao);
        setTecnico(tecnico);
        setValor(valor);
    }
    
    public Servico(){}
    
    @Override
    public String toString(){
        String ret;
        ret = "Descricao..:" + getDescricao() + "\n" +
               "Tecnico...:" + getTecnico() + "\n" +
               "Valor.....:" + getValor();
        
        return ret;
    }
    
    private void gravar(){
        ServicoDAO dao = new ServicoDAO();
        int codigo = dao.create(this);
        if(codigo > 0) setCodigo(codigo);
    }
    
    
    public static DefaultTableModel getTableModel(){
        List<Servico> lista = ServicoDAO.getInstance().read();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Descricao");
        modelo.addColumn("Tecnico");
        modelo.addColumn("Valor");
        for (Servico c: lista){
            String[] reg = {c.getDescricao(),c.getTecnico(),String.valueOf(c.getValor())};
            modelo.addRow(reg);
        }
        return modelo;
    }
    

    
}

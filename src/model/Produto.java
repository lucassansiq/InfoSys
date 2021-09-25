/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas Hype
 */
public class Produto {
    private int codigo = 0 ;
    private String descricao = null;
    private int estoque;
    private boolean ativo = false;
    private double custo,venda = 0;

    public Produto(String descricao, int estoque,double custo, double venda) {
        setDescricao(descricao);
        setEstoque(estoque);
        setCusto(custo);
        setVenda(venda);
        setAtivo(true);
        gravar();
    }
    
    public Produto(int Codigo,String descricao,boolean ativo, int estoque,double custo, double venda) {
        setCodigo(Codigo);
        setDescricao(descricao);
        setEstoque(estoque);
        setCusto(custo);
        setVenda(venda);
        setAtivo(ativo);    
    }
    
    public Produto(String descricao,boolean ativo, int estoque,double custo, double venda) {
        setDescricao(descricao);
        setEstoque(estoque);
        setCusto(custo);
        setVenda(venda);
        setAtivo(ativo);    
    }
    
    public Produto(int Codigo,String descricao, int estoque,double custo, double venda) {
        setCodigo(Codigo);
        setDescricao(descricao);
        setEstoque(estoque);
        setCusto(custo);
        setVenda(venda);
           
    }
    public Produto(){
        
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getVenda() {
        return venda;
    }

    public void setVenda(double venda) {
        this.venda = venda;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    @Override
    public String toString(){
        String ret = null;
        
        if (isAtivo()) ret = "[Ativo]";
        else ret = "[Fora de linha]";
        
        ret = ret + "\n" +
              "Item......:" + getDescricao() + "\n" + 
              "Estoque...:" + getEstoque() + "\n" +
              "Custo.....:" + getCusto() + "\n" +
              "Venda.....:" + getVenda();
        
        return ret;
    }

    private void gravar(){
        ProdutoDAO dao = new ProdutoDAO();
        int codigo = dao.create(this);
        if(codigo > 0) setCodigo(codigo);
    }
    public static DefaultTableModel getTableModel(){
        List<Produto> lista = ProdutoDAO.getInstance().read();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Descricao");
        modelo.addColumn("Estoque");
        modelo.addColumn("Custo");
        modelo.addColumn("Venda");
        for (Produto c: lista){
            String[] reg = {c.getDescricao(),String.valueOf(c.getEstoque()),String.valueOf(c.getCusto()),String.valueOf(c.getVenda())};
            modelo.addRow(reg);
        }
        return modelo;
    }
    

}

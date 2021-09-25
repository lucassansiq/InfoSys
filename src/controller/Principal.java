/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import util.Configura;
import view.telaCadastro;

/**
 *
 * @author Lucas Hype
 */
public class Principal {
    
    public static void main(String[] args) {
        Configura.LookAndFeel("Windows Classic");
        new telaCadastro().setVisible(true);
        dao.ConnectionFactory.getConnection();
    }

}

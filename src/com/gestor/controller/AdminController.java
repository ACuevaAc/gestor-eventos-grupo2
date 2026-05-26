package com.gestor.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import com.gestor.model.entity.Table;
import com.gestor.service.TableService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.FormCrearProducto;
import com.gestor.view.admin.GestorMesasView;
import com.gestor.view.admin.StatsAdminProducts;
import com.gestor.view.admin.signupadminView;

public class AdminController {

    private AdminMainView view;
    private TableService ms;

    public AdminController(AdminMainView v) {

        this.view = v;
        this.ms = new TableService();

        actualizarColoresMesas();

        view.getBtnCreateTable().addActionListener(e -> crearMesa());
        view.getBtnNewAdmin().addActionListener(e -> createNewAdmin());
        view.getBtnEmptyAllTables().addActionListener(e -> deleteTable());
        view.getBtnNewProduct().addActionListener(e-> crearProducto());
        view.getBtnStats().addActionListener(e-> checkStats());
    }
    
    public void checkStats() {
    	view.dispose();
    	StatsAdminProducts v=new StatsAdminProducts();
    	new StatsController(v);
    	v.setVisible(true);      
    }
    
    public void crearProducto() {
    	view.dispose();
    	FormCrearProducto v=new FormCrearProducto();
    	v.setVisible(true);
    	new CreateProductController(this,v);
    }

    public void deleteTable() {

        ms.deleteTable();

        reiniciarMesas();
    }
    public void reiniciarMesas() {
    	
        List<JButton> botones = view.getTablesList();
    	
        for (int i = 0; i < botones.size(); i++) {

            JButton boton = botones.get(i);
           boton.setBackground(null);
        }
    }

    public void actualizarColoresMesas() {

        List<JButton> botones = view.getTablesList();

        List<Table> mesasBD = ms.getCreatedTables();

        for (int i = 0; i < botones.size() && i < mesasBD.size(); i++) {

            JButton boton = botones.get(i);

            Table mesa = mesasBD.get(i);

            if (mesa.isBooked()) {

                boton.setBackground(Color.RED);

            } else {

                boton.setBackground(Color.GREEN);
            }
        }

        view.repaint();
    }

    public AdminMainView getAdminView() {
        return view;
    }

    public void crearMesa() {

        view.setVisible(false);

        GestorMesasView v = new GestorMesasView();
        v.setVisible(true);

        new TableMenuController(v, this);
    }

    public void createNewAdmin() {

        view.setVisible(false);

        signupadminView v = new signupadminView();
        v.setVisible(true);

        new SignupAdminController(v, this);
    }
}
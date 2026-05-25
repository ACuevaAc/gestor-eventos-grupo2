package com.gestor.controller;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.gestor.model.entity.Product;
import com.gestor.service.OrderService;
import com.gestor.service.ProductService;
import com.gestor.view.user.ListaDeProductos;

public class UserTableController {
    private ListaDeProductos view;
    private int idMesa;
    private ProductService productService;
    private OrderService orderService;
    
    private List<Product> listaProductos;
    private int indiceActual = 0;
    private double totalAcumuladoMesa = 0.0;

    public UserTableController(ListaDeProductos v, int id) {
        this.view = v;
        this.idMesa = id;
        this.productService = new ProductService();
        this.orderService = new OrderService();
        
        this.totalAcumuladoMesa = orderService.calculateTableTotal(idMesa);
        actualizarTextoTotal();

        this.listaProductos = productService.getAllProducts();
        
        if (listaProductos != null && !listaProductos.isEmpty()) {
            mostrarProductoActual();
        } else {
            view.getLblTituloProducto().setText("No hay productos");
            view.getPrecioNum().setText("0 €");
        }
        view.getBtnNext().addActionListener(e -> avanzarProducto());
        view.getBtnBack().addActionListener(e -> retrocederProducto());
        view.getBtnAñadir().addActionListener(e -> añadirAlPedido());
        view.getBtnTerminar().addActionListener(e -> finalizarVentana());
    }

    private void mostrarProductoActual() {
        if (listaProductos == null || listaProductos.isEmpty()) return;
        
        Product prod = listaProductos.get(indiceActual);
        view.getLblTituloProducto().setText(prod.getName());
        view.getPrecioNum().setText(prod.getPrice() + " €");
        view.getCantCB().setSelectedIndex(0); 

        if (prod.getImage() != null && prod.getImage().length > 0) {
            ImageIcon original = new ImageIcon(prod.getImage());
            Image escalada = original.getImage().getScaledInstance(260, 131, Image.SCALE_SMOOTH);
            view.getLblFoto().setIcon(new ImageIcon(escalada));
            view.getLblFoto().setText(""); 
        } else {
            view.getLblFoto().setIcon(null);
            view.getLblFoto().setText("Sin imagen");
        }
    }

    private void avanzarProducto() {
        if (listaProductos == null || listaProductos.isEmpty()) return;
        if (indiceActual < listaProductos.size() - 1) {
            indiceActual++;
        } else {
            indiceActual = 0; 
        }
        mostrarProductoActual();
    }

    private void retrocederProducto() {
        if (listaProductos == null || listaProductos.isEmpty()) return;
        if (indiceActual > 0) {
            indiceActual--;
        } else {
            indiceActual = listaProductos.size() - 1;
        }
        mostrarProductoActual();
    }

    private void añadirAlPedido() {
        if (listaProductos == null || listaProductos.isEmpty()) return;

        Product prodActual = listaProductos.get(indiceActual);
        int cantidad = (int) view.getCantCB().getSelectedItem();
        double precioTotalItems = prodActual.getPrice() * cantidad;
        orderService.createOrder(this.idMesa, prodActual.getId(), cantidad, precioTotalItems);
        totalAcumuladoMesa += precioTotalItems;
        actualizarTextoTotal();

        JOptionPane.showMessageDialog(view, 
            "Añadido: " + cantidad + "x " + prodActual.getName() + " a la cuenta.", 
            "Pedido Confirmado", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void actualizarTextoTotal() {
        view.getLblPrecioTotal().setText("Precio Total: " + String.format("%.2f", totalAcumuladoMesa) + " €");
    }

    private void finalizarVentana() {
        JOptionPane.showMessageDialog(view, "Pedido concluido correctamente para la Mesa " + this.idMesa);
        view.dispose(); 
    }
}

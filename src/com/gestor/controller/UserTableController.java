package com.gestor.controller;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.gestor.model.entity.Product;
import com.gestor.model.entity.User;
import com.gestor.service.BookService;
import com.gestor.service.OrderService;
import com.gestor.service.ProductService;
import com.gestor.service.TableService;
import com.gestor.view.user.ListaDeProductos;
import com.gestor.view.user.UserMainView;

public class UserTableController {
    private ListaDeProductos view;
    private int tableId;
    private ProductService productService;
    private BookService bookService;
    private OrderService orderService;
    private List<Product> productList;
    private int currentIndex = 0;
    private double accumulatorTable = 0.0;
    private User user;
    private TableService ts;
    
    public UserTableController (ListaDeProductos v, int id, User user) {
        this.view = v;
        this.tableId = id;
        this.productService = new ProductService();
        this.orderService = new OrderService();
        this.ts = new TableService();
        this.user = user;
        
        this.accumulatorTable = orderService.calculateTableTotal(tableId);
        updateTotalText();

        this.productList = productService.getAllProducts();
        
        if (productList != null && !productList.isEmpty()) {
            showCurrentProduct();
        } else {
            view.getLblTituloProducto().setText("No hay productos");
            view.getPrecioNum().setText("0 €");
        }
        view.getBtnNext().addActionListener(e -> advanceProduct());
        view.getBtnBack().addActionListener(e -> previousProduct());
        view.getBtnAñadir().addActionListener(e -> addToOrder());
        view.getBtnTerminar().addActionListener(e -> finishWindow());
        view.getBtnExit().addActionListener(e -> exitWithoutCancel());
        view.getBtnExitandClose().addActionListener(e -> exitAndCancel());
    }

    private void exitAndCancel() {
		view.dispose();
    	orderService.deleteTableOrder(tableId);
    	bookService.deleteFromBooking(tableId);
    	ts.releaseTable(tableId);
    	UserMainView u = new UserMainView(user);
    	u.setVisible(true);
    	}

	private void exitWithoutCancel() {
    	view.dispose();
    	UserMainView u = new UserMainView(user);
    	u.setVisible(true);
	}

	private void showCurrentProduct() {
        if (productList == null || productList.isEmpty()) return;
        
        Product prod = productList.get(currentIndex);
        view.getLblTituloProducto().setText(prod.getName());
        view.getPrecioNum().setText(prod.getPrice() + " €");
        view.getCantCB().setSelectedIndex(0); 

        if (prod.getImage() != null && prod.getImage().length > 0) {
            ImageIcon original = new ImageIcon(prod.getImage());
            Image scaled = original.getImage().getScaledInstance(260, 131, Image.SCALE_SMOOTH);
            view.getLblFoto().setIcon(new ImageIcon(scaled));
            view.getLblFoto().setText(""); 
        } else {
            view.getLblFoto().setIcon(null);
            view.getLblFoto().setText("Sin imagen");
        }
    }

    private void advanceProduct() {
        if (productList == null || productList.isEmpty()) return;
        if (currentIndex < productList.size() - 1) {
            currentIndex++;
        } else {
            currentIndex = 0; 
        }
        showCurrentProduct();
    }

    private void previousProduct() {
        if (productList == null || productList.isEmpty()) return;
        if (currentIndex > 0) {
            currentIndex--;
        } else {
            currentIndex = productList.size() - 1;
        }
        showCurrentProduct();
    }

    private void addToOrder() {
        if (productList == null || productList.isEmpty()) return;

        Product currentProduct = productList.get(currentIndex);
        int amount = (int) view.getCantCB().getSelectedItem();
        double totalPriceItems = currentProduct.getPrice() * amount;
        orderService.createOrder(this.tableId, currentProduct.getId(), amount, totalPriceItems);
        accumulatorTable += totalPriceItems;
        updateTotalText();

        JOptionPane.showMessageDialog(view, 
            "Añadido: " + amount + "x " + currentProduct.getName() + " a la cuenta.", 
            "Pedido Confirmado", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void updateTotalText () {
        view.getLblPrecioTotal().setText("Precio Total: " + String.format("%.2f", accumulatorTable) + " €");
    }

    private void finishWindow () {
        JOptionPane.showMessageDialog(view, "Pedido concluido correctamente para la Mesa " + this.tableId);
        view.dispose(); 
    }
}

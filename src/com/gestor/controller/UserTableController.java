package com.gestor.controller;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.gestor.model.entity.Product;
import com.gestor.model.entity.SummaryOrders;
import com.gestor.model.entity.User;
import com.gestor.service.BookService;
import com.gestor.service.OrderService;
import com.gestor.service.ProductService;
import com.gestor.service.TableService;
import com.gestor.view.user.ListaDeProductos;
import com.gestor.view.user.UserMainView;
import com.gestor.view.user.UserPaymentSummaryDialog;

/**
 * @class UserTableController
 * @description Controller architectural component managing the customer terminal ordering subsystem,
 * handling catalog sliding carousel interfaces, sequential tracking indices, real-time accumulation pricing matrices,
 * transactional data payloads, binary image stream decoding, and order termination cascades.
 */
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
    
    /**
     * @constructor
     * @description Initializes the client catalog interaction context, queries downstream pricing structures, 
     * caches operational inventory parameters, establishes baseline layouts, and hooks behavioral event listener streams.
     * @param {ListaDeProductos} v - Catalog navigation and item allocation view frame container.
     * @param {int} id - Unique identifier matching the targeting transaction table record.
     * @param {User} user - Current active authentication context profile mapping.
     */
    public UserTableController(ListaDeProductos v, int id, User user) {
        this.view = v;
        this.tableId = id;
        this.productService = new ProductService();
        this.orderService = new OrderService();
        this.bookService = new BookService();
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

    /**
     * @method exitAndCancel
     * @private
     * @description Dismantles visualization frames, executes cascade purge transactions across relational orders, 
     * booking registries, and changes resource table tracking parameters to empty states before redirecting routes.
     */
    private void exitAndCancel() {
        view.dispose();
        orderService.deleteTableOrder(tableId);
        bookService.deleteFromBooking(tableId);
        ts.releaseTable(tableId);
        UserMainView u = new UserMainView(user);
        u.setVisible(true);
    }

    /**
     * @method exitWithoutCancel
     * @private
     * @description Destroys the current context module frame without executing status rollback transactions, 
     * gracefully shifting visibility states back to primary parent workflows.
     */
    private void exitWithoutCancel() {
        view.dispose();
        UserMainView u = new UserMainView(user);
        u.setVisible(true);
    }

    /**
     * @method showCurrentProduct
     * @private
     * @description Extracts item entities matching the active collection carousel index, populates layout textual values, 
     * processes binary database stream arrays into graphical image formats, and triggers downscaling layout interpolation.
     */
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

    /**
     * @method advanceProduct
     * @private
     * @description Updates tracking indexes to forward boundaries, resetting positions back to baseline 
     * indices if thresholds collide with upper limits.
     */
    private void advanceProduct() {
        if (productList == null || productList.isEmpty()) return;
        if (currentIndex < productList.size() - 1) {
            currentIndex++;
        } else {
            currentIndex = 0; 
        }
        showCurrentProduct();
    }

    /**
     * @method previousProduct
     * @private
     * @description Shifts matching navigation tracking pointers backwards, looping boundaries back to terminal array locations 
     * if operations try to dip below starting thresholds.
     */
    private void previousProduct() {
        if (productList == null || productList.isEmpty()) return;
        if (currentIndex > 0) {
            currentIndex--;
        } else {
            currentIndex = productList.size() - 1;
        }
        showCurrentProduct();
    }

    /**
     * @method addToOrder
     * @private
     * @description Parses interface selected quantity states, evaluates product properties, 
     * delegates transactional line-item logging tasks to core order domains, and appends raw totals to the operational accumulator tracking layer.
     */
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

    /**
     * @method updateTotalText
     * @private
     * @description Formats system metrics tracking accumulators according to precise floating point layout schemas 
     * and maps values directly onto view components.
     */
    private void updateTotalText() {
        view.getLblPrecioTotal().setText("Precio Total: " + String.format("%.2f", accumulatorTable) + " €");
    }

    /**
     * @method finishWindow
     * @private
     * @description Queries comprehensive ongoing billing details matching active allocation indices and passes data payloads 
     * as parameters into checkout modal context layers.
     */
    private void finishWindow() {
        List<SummaryOrders> currentOrder = orderService.getOrderDetailsByTable(tableId);
        UserPaymentSummaryDialog checkout = new UserPaymentSummaryDialog(view, tableId, user, currentOrder);
        checkout.setVisible(true);
    }
}
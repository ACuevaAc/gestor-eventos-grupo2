package com.gestor.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.gestor.model.entity.Product;
import com.gestor.service.ProductService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.FormCrearProducto;

public class ControllerCrearProducto {
    private FormCrearProducto view;
    private ProductService ps;
    private AdminController aCont;
    
    public ControllerCrearProducto(AdminController ac, FormCrearProducto v) {
        this.aCont = ac;
        this.view = v;
        this.ps = new ProductService();
        
        view.getBtnCreate().addActionListener(e -> createProduct());
        view.getBtnBack().addActionListener(e -> back());
        view.getBtnBuscarImagen().addActionListener(e -> searchImage());
    }
    
    public void searchImage() {
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "jpeg", "png");
        selector.setFileFilter(filtro);
        
        int resultado = selector.showOpenDialog(view);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoElegido = selector.getSelectedFile();

            view.setImagenSeleccionada(archivoElegido);

            ImageIcon iconoOriginal = new ImageIcon(archivoElegido.getAbsolutePath());
            view.setIconoOriginal(iconoOriginal);
            
            Image escalada = iconoOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            view.setImagenEscalada(escalada);

            view.getLblRutaImagen().setText("");
            view.getLblRutaImagen().setIcon(new ImageIcon(escalada));
        }
    }
    public void createProduct() {
        try {
            String nombre = view.getTxtNombre().getText().trim();
            String precioTexto = view.getTxtPrecio().getText().trim();
            File archivoImagen = view.getImagenSeleccionada();

            if (nombre.isEmpty() || precioTexto.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Por favor, rellena todos los campos obligatorios.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (archivoImagen == null) {
                JOptionPane.showMessageDialog(view, "Debes seleccionar una imagen para el producto.", "Falta imagen", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double precio = Double.parseDouble(precioTexto);
         
            byte[] imagenBytes = Files.readAllBytes(archivoImagen.toPath());

            Product nuevoProducto = new Product(0, nombre, precio, imagenBytes);

            ps.createProduct(nuevoProducto);
            JOptionPane.showMessageDialog(view, "Producto creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            back();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "El precio ingresado no es un número válido (usa formato 10.50).", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, "Ocurrió un error al procesar el archivo de imagen.", "Error de archivo", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    
    public void back() {
        view.dispose();
        AdminMainView v=new AdminMainView();
        v.setVisible(true);
        new AdminController(v);
        
        }
    
}


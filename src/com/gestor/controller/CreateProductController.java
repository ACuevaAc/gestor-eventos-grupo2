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

/**
 * @class CreateProductController
 * @description Controller architectural component managing the product creation inventory subsystem,
 * handling binary image telemetry processing, user validation filters, and local view routing transitions.
 */
public class CreateProductController {
    /**
     * @private
     * @type {FormCrearProducto}
     */
    private FormCrearProducto view;

    /**
     * @private
     * @type {ProductService}
     */
    private ProductService ps;

    /**
     * @private
     * @type {AdminController}
     */
    private AdminController aCont;
    
    /**
     * @constructor
     * @description Initializes the contextual asset ingestion execution flow, linking user event listeners to target triggers.
     * @param {AdminController} ac - Main orchestration tracking parent administrative context.
     * @param {FormCrearProducto} v - Catalog input wizard frame view representation container.
     */
    public CreateProductController(AdminController ac, FormCrearProducto v) {
        this.aCont = ac;
        this.view = v;
        this.ps = new ProductService();
        
        view.getBtnCreate().addActionListener(e -> createProduct());
        view.getBtnBack().addActionListener(e -> back());
        view.getBtnBuscarImagen().addActionListener(e -> searchImage());
    }
    
    /**
     * @method searchImage
     * @description Spawns a system file selector dialog filtered by specific image extensions, 
     * caching target references and generating downscaled layout thumbnails for user feedback.
     */
    public void searchImage() {
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter _filter = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "jpeg", "png");
        selector.setFileFilter(_filter);
        
        int result = selector.showOpenDialog(view);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = selector.getSelectedFile();

            view.setImagenSeleccionada(file);

            ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
            view.setIconoOriginal(originalIcon);
            
            Image scaledIcon = originalIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            view.setImagenEscalada(scaledIcon);

            view.getLblRutaImagen().setText("");
            view.getLblRutaImagen().setIcon(new ImageIcon(scaledIcon));
        }
    }

    /**
     * @method createProduct
     * @description Parses structural text specifications, transforms selected visual assets into binary arrays, 
     * executes target model entity validation pipelines, and transfers persistence tasks to core backend services.
     * @throws {NumberFormatException} Implicitly caught if text criteria mapping for data values violates double parsing schemas.
     * @throws {IOException} Implicitly caught if disk storage asset reading fails during byte array extraction pipelines.
     */
    public void createProduct() {
        try {
            String name = view.getTxtNombre().getText().trim();
            String textPrice = view.getTxtPrecio().getText().trim();
            File image = view.getImagenSeleccionada();

            if (name.isEmpty() || textPrice.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Por favor, rellena todos los campos obligatorios.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (image == null) {
                JOptionPane.showMessageDialog(view, "Debes seleccionar una imagen para el producto.", "Falta imagen", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double price = Double.parseDouble(textPrice);
         
            byte[] imageBytes = Files.readAllBytes(image.toPath());

            Product product = new Product(0, name, price, imageBytes);

            ps.createProduct(product);
            JOptionPane.showMessageDialog(view, "Producto creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            back();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "El precio ingresado no es un número válido (usa formato 10.50).", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, "Ocurrió un error al procesar el archivo de imagen.", "Error de archivo", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    
    /**
     * @method back
     * @description Terminates active creation layout frames to return application navigation focus 
     * to primary management dashboard control threads.
     */
    public void back() {
        view.dispose();
        AdminMainView v=new AdminMainView();
        v.setVisible(true);
        new AdminController(v);
        
    }
}


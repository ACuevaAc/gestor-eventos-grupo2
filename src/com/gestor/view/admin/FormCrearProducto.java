package com.gestor.view.admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @class FormCrearProducto
 * @description Administrative window user interface component that acts as a creation wizard panel.
 * Handles data input entry matrices for catalog management, manages structural image file attachments, 
 * maps scalable visualization previews, and exposes core operation callback hooks.
 */
public class FormCrearProducto extends JFrame {

    /**
     * @private
     * @static
     * @final
     * @type {long}
     */
    private static final long serialVersionUID = 1L;

    /**
     * @private
     * @type {JPanel}
     */
    private JPanel contentPane;

    /**
     * @private
     * @type {JTextField}
     */
    private JTextField txtNombre;

    /**
     * @private
     * @type {JTextField}
     */
    private JTextField txtPrecio;

    /**
     * @private
     * @type {JButton}
     */
    private JButton btnBuscarImagen;

    /**
     * @private
     * @type {File}
     */
    private File imagenSeleccionada;

    /**
     * @private
     * @type {JLabel}
     */
    private JLabel lblRutaImagen;

    /**
     * @private
     * @type {ImageIcon}
     */
    private ImageIcon iconoOriginal;

    /**
     * @private
     * @type {Image}
     */
    private Image imagenEscalada;

    /**
     * @private
     * @type {JButton}
     */
    private JButton btnCreate;

    /**
     * @private
     * @type {JButton}
     */
    private JButton btnBack;
    
    /**
     * @constructor
     * @description Constructs the product creation wizard canvas, sets window constraints, registers 
     * structural layout managers, mounts text tracking controls, and draws image placeholder anchors.
     */
    public FormCrearProducto() {
        super("Crear Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelBotones = new JPanel();
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        btnCreate = new JButton("Create");
        btnCreate.setToolTipText("Crear Producto");
        panelBotones.add(btnCreate);

        btnBack = new JButton("Back");
        btnBack.setToolTipText("Volver");
        panelBotones.add(btnBack);

        JPanel panelForm = new JPanel();
        contentPane.add(panelForm, BorderLayout.CENTER);
        panelForm.setLayout(new GridLayout(4, 2, 5, 5));

        JLabel lblNombre = new JLabel("Nombre");
        panelForm.add(lblNombre);

        txtNombre = new JTextField();
        panelForm.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblPrecio = new JLabel("Precio");
        panelForm.add(lblPrecio);

        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);
        txtPrecio.setColumns(10);

        JLabel lblImagen = new JLabel("Imagen");
        panelForm.add(lblImagen);

        btnBuscarImagen = new JButton("Seleccionar archivo...");
        panelForm.add(btnBuscarImagen);

        JLabel lblEspacioVacio = new JLabel("Vista previa:");
        panelForm.add(lblEspacioVacio);

        lblRutaImagen = new JLabel("Sin foto");
        panelForm.add(lblRutaImagen);
    }

    /**
     * @method getTxtNombre
     * @description Exposes the unique data input entry element monitoring the product descriptive title.
     * @returns {JTextField} Core visual text field manager reference.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * @method setTxtNombre
     * @description Replaces or injects a custom text input module managing catalog naming strings.
     * @param {JTextField} txtNombre - Target text interface tracking component.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * @method getTxtPrecio
     * @description Exposes the validation input element capturing transaction row price value characters.
     * @returns {JTextField} Core numeric entry interface tracker component.
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * @method setTxtPrecio
     * @description Injects an operational interface element handling financial pricing string metrics.
     * @param {JTextField} txtPrecio - Target quantitative input field.
     */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /**
     * @method getBtnBuscarImagen
     * @description Resolves the user interaction trigger deployed to clear or launch local filesystem picker explorers.
     * @returns {JButton} Storage browser engine action anchor.
     */
    public JButton getBtnBuscarImagen() {
        return btnBuscarImagen;
    }

    /**
     * @method setBtnBuscarImagen
     * @description Registers an interface component designated to drive external system directory selection events.
     * @param {JButton} btnBuscarImagen - Target file allocation button reference.
     */
    public void setBtnBuscarImagen(JButton btnBuscarImagen) {
        this.btnBuscarImagen = btnBuscarImagen;
    }

    /**
     * @method getImagenSeleccionada
     * @description Extracts the abstract tracking descriptor mapping chosen binary filesystem objects.
     * @returns {File} Reference pointer to the staged product image file metadata.
     */
    public File getImagenSeleccionada() {
        return imagenSeleccionada;
    }

    /**
     * @method setImagenSeleccionada
     * @description Binds the validated metadata profile of an external system file resource onto current state models.
     * @param {File} imagenSeleccionada - Target storage resource file descriptor.
     */
    public void setImagenSeleccionada(File imagenSeleccionada) {
        this.imagenSeleccionada = imagenSeleccionada;
    }

    /**
     * @method getIconoOriginal
     * @description Pulls uncompressed unscaled source graphic data references bound to this visual context.
     * @returns {ImageIcon} The original immutable vector or bitmap container resource mapping.
     */
    public ImageIcon getIconoOriginal() {
        return iconoOriginal;
    }

    /**
     * @method setIconoOriginal
     * @description Sets the unscaled graphic tracking entity state to structure custom preview components.
     * @param {ImageIcon} iconoOriginal - Source high-resolution graphical resource asset.
     */
    public void setIconoOriginal(ImageIcon iconoOriginal) {
        this.iconoOriginal = iconoOriginal;
    }

    /**
     * @method getImagenEscalada
     * @description Exposes the active display bitmap modified by proportional geometric scaling algorithms.
     * @returns {Image} Processed viewport graphics layout asset reference.
     */
    public Image getImagenEscalada() {
        return imagenEscalada;
    }

    /**
     * @method setImagenEscalada
     * @description Updates presentation layer image parameters to cache down-sampled graphics layers.
     * @param {Image} imagenEscalada - Recalibrated layout matrix canvas object.
     */
    public void setImagenEscalada(Image imagenEscalada) {
        this.imagenEscalada = imagenEscalada;
    }

    /**
     * @method getBtnCreate
     * @description Exposes the system submission anchor dispatched to commit catalog payload parameters to persistence contexts.
     * @returns {JButton} The primary data insertion operation button.
     */
    public JButton getBtnCreate() {
        return btnCreate;
    }

    /**
     * @method setBtnCreate
     * @description Binds the main action module interface tasked with execution handling for object creations.
     * @param {JButton} btnCreate - Target execution action controller button.
     */
    public void setBtnCreate(JButton btnCreate) {
        this.btnCreate = btnCreate;
    }

    /**
     * @method getBtnBack
     * @description Returns the navigation boundary trigger deployed to clear current frame stacks and revert display perspectives.
     * @returns {JButton} Visual navigation context layout asset.
     */
    public JButton getBtnBack() {
        return btnBack;
    }

    /**
     * @method setBtnBack
     * @description Installs the explicit visual interaction node intended to dismiss configuration views.
     * @param {JButton} btnBack - Target navigational frame rollback action component.
     */
    public void setBtnBack(JButton btnBack) {
        this.btnBack = btnBack;
    }

    /**
     * @method getLblRutaImagen
     * @description Fetches the structural placeholder element rendering file target labels or graphical asset previews.
     * @returns {JLabel} Display label view component wrapper.
     */
    public JLabel getLblRutaImagen() {
        return lblRutaImagen;
    }

    /**
     * @method setLblRutaImagen
     * @description Configures text properties or layout coordinates directly onto file attachment visual trackers.
     * @param {JLabel} lblRutaImagen - Target status rendering label view interface.
     */
    public void setLblRutaImagen(JLabel lblRutaImagen) {
        this.lblRutaImagen = lblRutaImagen;
    }
}
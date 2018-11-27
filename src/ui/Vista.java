package ui;

import base.Juego;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.io.File;
import javax.swing.border.TitledBorder;

public class Vista extends JFrame {

    private JPanel contentPane;
    public JLabel lNombre;
    public JLabel lGenero;
    public JLabel lRatingEdad;
    public JLabel lRating;
    public JLabel lPlataforma;
    public JTextField tfNombre;
    public JTextField tfRatingEdad;
    public JTextField tfRating;
    public JComboBox cbGenero1;
    public JComboBox cbGenero2;
    public JComboBox cbPlataforma;
    public JLabel lImagen;
    public JButton btNuevo;
    public JButton btGuardar;
    public JButton btBorrar;
    public JButton btActualizar;
    public JButton btCancelar;
    public JButton btBorrarTodo;
    public JScrollPane scrollPane;
    public JList<Juego> listaJuegos;
    public DefaultListModel<Juego> mListaJuegos;
    public JLabel lblNewLabel;

    public Vista() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lNombre = new JLabel("Nombre");
        lNombre.setBounds(49, 41, 45, 14);
        contentPane.add(lNombre);

        lGenero = new JLabel("Genero");
        lGenero.setBounds(53, 66, 41, 14);
        contentPane.add(lGenero);

        lRatingEdad = new JLabel("Rating de edad");
        lRatingEdad.setBounds(10, 91, 84, 14);
        contentPane.add(lRatingEdad);

        lRating = new JLabel("Rating");
        lRating.setBounds(58, 116, 36, 14);
        contentPane.add(lRating);

        lPlataforma = new JLabel("Plataforma");
        lPlataforma.setBounds(30, 141, 64, 14);
        contentPane.add(lPlataforma);

        tfNombre = new JTextField();
        tfNombre.setHorizontalAlignment(SwingConstants.LEFT);
        tfNombre.setBounds(187, 38, 84, 20);
        contentPane.add(tfNombre);
        tfNombre.setColumns(10);

        tfRatingEdad = new JTextField();
        tfRatingEdad.setBounds(185, 88, 86, 20);
        contentPane.add(tfRatingEdad);
        tfRatingEdad.setColumns(10);

        tfRating = new JTextField();
        tfRating.setBounds(185, 113, 86, 20);
        contentPane.add(tfRating);
        tfRating.setColumns(10);

        cbGenero1 = new JComboBox();
        cbGenero1.setBounds(104, 63, 126, 20);
        contentPane.add(cbGenero1);

        cbGenero2 = new JComboBox();
        cbGenero2.setBounds(240, 63, 126, 20);
        contentPane.add(cbGenero2);

        cbPlataforma = new JComboBox();
        cbPlataforma.setBounds(164, 138, 128, 20);
        contentPane.add(cbPlataforma);

        lImagen = new JLabel("");
        lImagen.setBorder(new TitledBorder(null, "Imagen", TitledBorder.CENTER, TitledBorder.TOP, null, Color.RED));
        lImagen.setBounds(376, 41, 97, 117);
        contentPane.add(lImagen);
        lImagen.setIcon(new ImageIcon(System.getProperty("user.dir")+ File.separator+"imagenes"+File.separator+"fotoPorDefecto.jpg"));//Foto por defecto


        btNuevo = new JButton("Nuevo");
        btNuevo.setBounds(10, 188, 100, 23);
        contentPane.add(btNuevo);
        btNuevo.setFocusPainted(true);

        btGuardar = new JButton("Guardar");
        btGuardar.setBounds(74, 227, 100, 23);
        contentPane.add(btGuardar);

        btBorrar = new JButton("Borrar");
        btBorrar.setBounds(306, 188, 100, 23);
        contentPane.add(btBorrar);

        btActualizar = new JButton("Actualizar");
        btActualizar.setBounds(135, 188, 100, 23);
        contentPane.add(btActualizar);

        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(306, 227, 100, 23);
        contentPane.add(btCancelar);

        btBorrarTodo = new JButton("Borrar todo");
        btBorrarTodo.setBounds(494,239,100,20);
        contentPane.add(btBorrarTodo);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(477, 39, 141, 197);
        contentPane.add(scrollPane);

        listaJuegos = new JList();
        scrollPane.setViewportView(listaJuegos);

        mListaJuegos = new DefaultListModel<>();
        listaJuegos.setModel(mListaJuegos);

        lblNewLabel = new JLabel("Juegos");
        lblNewLabel.setBounds(525, 26, 46, 14);
        contentPane.add(lblNewLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 649, 300);
        setResizable(false);
        setVisible(true);
    }
}

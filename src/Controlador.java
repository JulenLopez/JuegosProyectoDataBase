import base.Juego;
import ui.Login;
import ui.Vista;
import util.Util;

import java.io.File;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

//Este controla las excepciones
public class Controlador implements ActionListener, MouseListener {

    private Vista vista;
    private Modelo modelo;
    private File ficheroSeleccionado;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        addListeners();
        modoEdicion(false);

        try {
            modelo.conectar();
            iniciarSesion();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        poblaComboBox();
        refrescarLista();
    }

    private void iniciarSesion(){
        boolean autenticado = false;
        Login login = new Login();
        int intentos = 1;

        do{
            login.mostrarDialogo();
            String usuario = login.getUsuario();
            String contrasena = login.getContrasena();

            try {
                autenticado= modelo.iniciarSesion(usuario,contrasena);
                if (!autenticado){
                    if(intentos > 2){
                        Util.mensajeError("Limite de intentos superado");
                        System.exit(0);
                    }
                    login.limpiarContrasena();
                    login.setMensaje("Usuario/Contraseña incorrectos");
                    intentos++;
                    continue;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }while (!autenticado);
    }


    public void refrescarLista(){

        vista.mListaJuegos.removeAllElements();
        try {
            for(Juego juego : modelo.getJuegos()){
                vista.mListaJuegos.addElement(juego);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void poblaComboBox(){
        for(Juego.Genero1 genero1: Juego.Genero1.values())
            vista.cbGenero1.addItem(genero1);

        for(Juego.Genero2 genero2 : Juego.Genero2.values())
            vista.cbGenero2.addItem(genero2);

        for(Juego.Plataforma plataforma : Juego.Plataforma.values())
            vista.cbPlataforma.addItem(plataforma);

    }

    private void addListeners(){
        vista.btNuevo.addActionListener(this);
        vista.btActualizar.addActionListener(this);
        vista.btBorrar.addActionListener(this);
        vista.btCancelar.addActionListener(this);
        vista.btGuardar.addActionListener(this);
        vista.btBorrarTodo.addActionListener(this);

        vista.lImagen.addMouseListener(this);
        vista.listaJuegos.addMouseListener(this);
    }

    private void modoEdicion(boolean activo){

        if(activo){
            vista.btGuardar.setEnabled(true);
            vista.btActualizar.setEnabled(false);
            vista.btCancelar.setEnabled(true);
            vista.btNuevo.setEnabled(false);
            vista.btBorrar.setEnabled(false);
            vista.lImagen.setEnabled(true);

            vista.tfRatingEdad.setEditable(true);
            vista.tfRating.setEditable(true);
            vista.tfNombre.setEditable(true);
        }else{
            vista.btGuardar.setEnabled(false);
            vista.btActualizar.setEnabled(false);
            vista.btCancelar.setEnabled(false);
            vista.btNuevo.setEnabled(true);
            vista.btBorrar.setEnabled(false);
            vista.lImagen.setEnabled(false);

            vista.tfRatingEdad.setEditable(false);
            vista.tfRating.setEditable(false);
            vista.tfNombre.setEditable(false);
        }
    }

    private void limpiarCosas(){
        vista.tfNombre.setText("");
        vista.tfRatingEdad.setText("");
        vista.tfRating.setText("");
        vista.cbPlataforma.setSelectedIndex(0);
        vista.cbGenero2.setSelectedIndex(0);
        vista.cbGenero1.setSelectedIndex(0);
        vista.lImagen.setIcon(null);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Nuevo":
                modoEdicion(true);
                limpiarCosas();
                break;
            case "Actualizar":
                if(!vista.tfNombre.isEnabled())
                    modoEdicion(true);
                else{
                    modelo.modificar(juego);
                }
                break;
            case "Guardar":
                modoEdicion(false);
                if(vista.tfNombre.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"El campo de nombre es necesario","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(vista.tfRating.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"El campo de rating no puede estar vacio","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(vista.tfRatingEdad.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"El campo de rating de edad no puede estar vacio","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!vista.tfRating.getText().matches("[0-9]*.[0-9]*")){
                    JOptionPane.showMessageDialog(null, "El campo de rating solo acepta numeros y un punto","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!vista.tfRatingEdad.getText().matches("[0-9]*")){
                    //TODO crear un array con los diferentes ratings de edad
                    JOptionPane.showMessageDialog(null, "El campo de rating de edad solo acepta numeros y un punto","Error",JOptionPane.ERROR_MESSAGE);
                    return;

                }
                String nombre = vista.tfNombre.getText();
                Juego.Genero1 genero1 = (Juego.Genero1) vista.cbGenero1.getSelectedItem();
                Juego.Genero2 genero2 = (Juego.Genero2) vista.cbGenero2.getSelectedItem();
                int ratingEdad = Integer.parseInt(vista.tfRatingEdad.getText());
                float rating = Float.parseFloat(vista.tfRating.getText());
                Juego.Plataforma plataforma = (Juego.Plataforma) vista.cbPlataforma.getSelectedItem();

                String imagen;

                if(ficheroSeleccionado!=null)
                    imagen= ficheroSeleccionado.getName();
                else
                    imagen = "fotoPorDefecto.jpg";

                Juego juego = new Juego();
                juego.setNombre(nombre);
                juego.setGenero1(genero1);
                juego.setGenero2(genero2);
                juego.setRatingEdad(ratingEdad);
                juego.setRating(rating);
                juego.setPlataforma(plataforma);
                juego.setImagen(imagen);

                try {
                    modelo.guardar(juego);
                    try {
                        if (ficheroSeleccionado != null)
                            Util.copiarImagen(ficheroSeleccionado.getAbsolutePath(), imagen);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    vista.mListaJuegos.addElement(juego);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                refrescarLista();

                limpiarCosas();
                break;

            case "Cancelar":
                modoEdicion(false);
                limpiarCosas();
                break;
            case "Borrar":
                modoEdicion(false);
                try {
                    modelo.eliminar(vista.listaJuegos.getSelectedValue());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                refrescarLista();

                limpiarCosas();
                break;
            case "Borrar todo":
                try {
                    modelo.eliminarTodo();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                refrescarLista();
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.lImagen && vista.lImagen.isEnabled()) {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
                return;
            ficheroSeleccionado = jfc.getSelectedFile();
            vista.lImagen.setIcon(new ImageIcon(ficheroSeleccionado.getAbsolutePath()));
        }else if (e.getSource() == vista.listaJuegos){
            vista.btActualizar.setEnabled(true);
            vista.btBorrar.setEnabled(true);

            vista.tfNombre.setText(vista.listaJuegos.getSelectedValue().getNombre());
            vista.tfRating.setText(String.valueOf(vista.listaJuegos.getSelectedValue().getRating()));
            vista.tfRatingEdad.setText(String.valueOf(vista.listaJuegos.getSelectedValue().getRatingEdad()));
            vista.cbGenero1.setSelectedItem(vista.listaJuegos.getSelectedValue().getGenero1());
            vista.cbGenero2.setSelectedItem(vista.listaJuegos.getSelectedValue().getGenero2());
            vista.cbPlataforma.setSelectedItem(vista.listaJuegos.getSelectedValue().getPlataforma());
            vista.lImagen.setIcon(new ImageIcon(System.getProperty("user.dir")+File.separator+"imagenes"+File.separator+vista.listaJuegos.getSelectedValue().getImagen()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

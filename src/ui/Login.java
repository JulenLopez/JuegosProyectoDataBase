package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Login extends JDialog implements ActionListener, KeyListener {

    private final JPanel contentPanel = new JPanel();
    public JTextField tfUsuario;
    public JPasswordField tfContrasena;
    public JLabel lblNewLabel;
    public JLabel lblNewLabel_1;
    public JButton btEntrar;
    public JButton btSalir;
    private String usuario;
    private String contrasena;

    public Login() {
        setTitle("Login");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        tfUsuario = new JTextField();
        tfUsuario.setBounds(161, 83, 239, 20);
        contentPanel.add(tfUsuario);
        tfUsuario.setColumns(10);

        tfContrasena = new JPasswordField();
        tfContrasena.setBounds(161, 145, 239, 20);
        contentPanel.add(tfContrasena);
        tfContrasena.setColumns(10);

        lblNewLabel = new JLabel("User");
        lblNewLabel.setBounds(73, 86, 46, 14);
        contentPanel.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setBounds(73, 148, 46, 14);
        contentPanel.add(lblNewLabel_1);

        JPanel contentPanel2 = new JPanel();
        contentPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(contentPanel2, BorderLayout.SOUTH);
        btEntrar = new JButton("Entrar");
        btEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        btEntrar.setActionCommand("OK");
        contentPanel2.add(btEntrar);
        getRootPane().setDefaultButton(btEntrar);
        btEntrar.addActionListener(this);


        btSalir = new JButton("Cancel");
        btSalir.setActionCommand("Salir");
        contentPanel2.add(btSalir);
        btSalir.addActionListener(this);

        //okButton.addActionListener(this);

        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setModal(true);
        setVisible(true);
    }

    public void mostrarDialogo(){
        setVisible(true);
    }

    public String getUsuario() {return usuario;}

    public String getContrasena() {return contrasena;}

    public void limpiarContrasena(){
        tfContrasena.setText("");
        tfContrasena.requestFocus();
    }

    public void setMensaje(String mensaje){
        lbMensaje.setText(mensaje);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Entrar":
                usuario = tfUsuario.getText();
                contrasena = String.valueOf(tfContrasena.getPassword());
                setVisible(false);
                break;
            case "Salir":
                System.exit(0);
                break;
            default:
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
                btEntrar.doClick();
            else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                btSalir.doClick();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
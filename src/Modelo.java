
import base.Juego;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Modelo {

    private static final String FICHERO_CONFIGURACION = "juegosql.conf";
    private Connection conexion;

    public void conectar() throws IOException, ClassNotFoundException, SQLException {
        Properties props = new Properties();
        props.load(new FileInputStream(FICHERO_CONFIGURACION));
        String host = props.getProperty("host");
        String usuarios = props.getProperty("usuario");
        String baseDatos = props.getProperty("base_datos");
        String contrasena = props.getProperty("contrasena");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://"+host+"/"+baseDatos,usuarios,contrasena);
    }

    public void desconector() throws SQLException {
        conexion.close();
    }

    public boolean iniciarSesion(String usuario, String contrasena) throws SQLException {

        String sql = "SELECT id FROM usuarios WHERE usuario = ?"+"AND contrasena=SHA1(sql)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1,usuario);
        sentencia.setString(2,contrasena);

        ResultSet resultado = sentencia.executeQuery();
        boolean encontrado = resultado.next();
        sentencia.close();

        if(!encontrado)
            return false;

        return true;
    }

    public void guardar (Juego juego) throws SQLException {
        String sql = "INSERT INTO juegos (nombre, genero1, genero2, rating_edad, rating, plataforma, imagen) "+"VALUES (?,?,?,?,?,?,?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, juego.getNombre());
        sentencia.setString(2,String.valueOf(juego.getGenero1()));
        sentencia.setString(3,String.valueOf(juego.getGenero2()));
        sentencia.setInt(4, juego.getRatingEdad());
        sentencia.setFloat(5, juego.getRating());
        sentencia.setString(6,String.valueOf(juego.getPlataforma()));
        sentencia.setString(7,juego.getImagen());
        sentencia.executeUpdate();
        sentencia.close();
    }

    public void modificar(Juego juego) throws SQLException {
        String sql = "UPDATE juegos SET nombre=?, genero1=?, genero2=?,rating_edad=?,rating=?,plataforma=?,imagen=?";

        PreparedStatement sentencia= conexion.prepareStatement(sql);
        sentencia.setString(1, juego.getNombre());
        sentencia.setString(2,String.valueOf(juego.getGenero1()));
        sentencia.setString(3,String.valueOf(juego.getGenero2()));
        sentencia.setInt(4, juego.getRatingEdad());
        sentencia.setFloat(5, juego.getRating());
        sentencia.setString(6,String.valueOf(juego.getPlataforma()));
        sentencia.setString(7,juego.getImagen());
        sentencia.executeUpdate();
        sentencia.close();
    }

    public void eliminar(Juego juego) throws SQLException {

        String sql = "DELETE FROM juegos WHERE id=?";
        PreparedStatement sentencia =conexion.prepareStatement(sql);
        sentencia.setLong(1, juego.getId());
        sentencia.executeUpdate();
        sentencia.close();
    }

    public List<Juego> getJuegos() throws SQLException {
        Juego juego = null;
        List<Juego> juegos= new ArrayList<>();
        String sql ="SELECT id, nombre, genero1,genero2,rating_edad,rating,plataforma,imagen FROM juegos";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()){
            juego = obtenerJuego(resultado);
            juegos.add(juego);
        }

        return juegos;
    }

    public List<Juego> getJuegos(String busqueda) throws SQLException {
        Juego juego = null;
        List<Juego> juegos= new ArrayList<>();
        String sql ="SELECT id, nombre, genero1,genero2,rating_edad,rating,plataforma,imagen FROM juegos WHERE busqueda =?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1,busqueda);
        ResultSet resultado = sentencia.executeQuery();
        while(resultado.next()){
            juego=obtenerJuego(resultado);
            juegos.add(juego);
        }

        return juegos;
    }

    private Juego obtenerJuego(ResultSet resultado) throws SQLException {

        Juego juego = new Juego();
        juego.setId(resultado.getLong(1));
        juego.setNombre(resultado.getString(2));
        juego.setGenero1(base.Juego.Genero1.valueOf(resultado.getString(3)));
        juego.setGenero2(base.Juego.Genero2.valueOf(resultado.getString(4)));
        juego.setRatingEdad(resultado.getInt(5));
        juego.setRating(resultado.getFloat(6));
        juego.setPlataforma(base.Juego.Plataforma.valueOf(resultado.getString(7)));
        juego.setImagen(resultado.getString(8));

        return juego;
    }
}

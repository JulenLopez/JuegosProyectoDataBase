package base;

import java.io.Serializable;

public class Juego implements Serializable {

    public enum Genero1 {
        Accion,Arcade,Aventura_Grafica,Aventuras,Baile,Belica,Comedia,Conduccion,Construccion,
        Deportes,Educativo,Estrategia,Fantastica,Fitness,Habilidad,Karaoke,Kinect,Lucha,Manga,
        Mmo,Moba,Move,Mundo_Abierto,Musical,Novela_Visual,Plataformas,Puzzle,Recopilatorio,Rol,
        Series_TV,Shooter,Simulador,Social,Survival_horror,Suspense,Terror,Udraw,Utilidad
    }

    public enum Genero2{
        Nada,Accion,Arcade,Aventura_Grafica,Aventuras,Baile,Belica,Comedia,Conduccion,Construccion,
        Deportes,Educativo,Estrategia,Fantastica,Fitness,Habilidad,Karaoke,Kinect,Lucha,Manga,
        Mmo,Moba,Move,Mundo_Abierto,Musical,Novela_Visual,Plataformas,Puzzle,Recopilatorio,Rol,
        Series_TV,Shooter,Simulador,Social,Survival_horror,Suspense,Terror,Udraw,Utilidad
    }

    public enum Plataforma {
        Atari,Master_System,Sega_Mega_Drive
        ,NES,Nintendo64,GameCube,Super_nintendo,Wii,WiiU,NintendoSwitch,Gameboy,GameBoy_color ,GameBoy_advance,NintendoDs,NintendoDsi,Nintendo3ds
        ,PLayStation,PlayStation2,PlayStation3,PlayStation4,PSP,PSP_Go,PSVita
        ,Xbox,Xbox360,Xbox_One,PC
    }

    private long id;
    public Genero1 genero1;
    public Genero2 genero2;
    public String nombre;
    public int ratingEdad;
    public float rating;
    public Plataforma plataforma;
    public String imagen;


    public Genero1 getGenero1() {return genero1;}

    public void setGenero1(Genero1 genero1) {this.genero1 = genero1;}

    public Genero2 getGenero2() {return genero2;}

    public void setGenero2(Genero2 genero2) {this.genero2 = genero2;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getRatingEdad() {return ratingEdad;}

    public void setRatingEdad(int ratingEdad) {this.ratingEdad = ratingEdad;}

    public float getRating() {return rating;}

    public void setRating(float rating) {this.rating = rating;}

    public Plataforma getPlataforma() {return plataforma;}

    public void setPlataforma(Plataforma plataforma) {this.plataforma = plataforma;}

    public String getImagen() {return imagen;}

    public void setImagen(String imagen) {this.imagen = imagen;}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

}


import com.mpatric.mp3agic.*;
import com.mpatric.mp3agic.Mp3File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class Audio {
    public static void mp3_fun(String cancion) throws Exception{
        Mp3File mp3file = new Mp3File(cancion);
        System.out.println("La duración de este mp3 es: " + mp3file.getLengthInSeconds() + " segundos");
        System.out.println("Tasa de bits: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
        System. out.println("Frecuencia de muestreo: " + mp3file.getSampleRate() + " Hz");
        System.out.println("Tiene etiqueta ID3v1?: " + (mp3file.hasId3v1Tag() ? "Si" : "No"));
        System.out.println("Tiene etiqueta ID3v2?: " + (mp3file.hasId3v2Tag() ? "Si" : "No"));
        System.out.println("Tiene etiqueta personalizada?: " + (mp3file.hasCustomTag() ? "Si" : "No"));
        if (mp3file.hasId3v1Tag()) {
            mp3file.removeId3v1Tag();
        }
        if (mp3file.hasId3v2Tag()) {
            mp3file.removeId3v2Tag();
        }
        if (mp3file.hasCustomTag()) {
            mp3file.removeCustomTag();
        }
        mp3file.save("Mp3FileWithoutTags.mp3");
        
        Mp3File mp3file2 = new Mp3File("Mp3FileWithoutTags.mp3");
        if (mp3file2.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3file2.getId3v1Tag();
            System.out.println("Track: " + id3v1Tag.getTrack());
            System.out.println("Artist: " + id3v1Tag.getArtist());
            System.out.println("Title: " + id3v1Tag.getTitle());
            System.out.println("Album: " + id3v1Tag.getAlbum());
            System.out.println("Year: " + id3v1Tag.getYear());
            System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
            System.out.println("Comment: " + id3v1Tag.getComment());
       }else{
            System.out.println("Falso ");
        }
        
        Mp3File mp3file3 = new Mp3File("Mp3FileWithoutTags.mp3");
        ID3v1 id3v1Tag;
        if (mp3file.hasId3v1Tag()) {
            id3v1Tag =  mp3file.getId3v1Tag();
        } else {
        // MP3 no tiene una etiqueta ID3v1, vamos a crear una ..
        id3v1Tag = new ID3v1Tag();
        mp3file.setId3v1Tag(id3v1Tag);
        }
        id3v1Tag.setTrack("5");
        id3v1Tag.setArtist("An Artist");
        id3v1Tag.setTitle("The Title");
        id3v1Tag.setAlbum("The Album");
        id3v1Tag.setYear("2001");
        id3v1Tag.setGenre(12);
        id3v1Tag.setComment("Some comment");
        mp3file.save("MyMp3File.mp3");
        
    }
    public static void main(String[] args) throws Exception{
        String cancion = "cancion.mp3"; // El nombre de la canción
        mp3_fun(cancion); // Se llama a la función que detalla el MP3
        
    }
}

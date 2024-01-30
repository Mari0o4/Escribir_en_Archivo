import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class InicioSesion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Registro de Foornus");
        System.out.print("Introduce tu correo: ");
        String correo = scanner.nextLine();
        System.out.print("Introduce la contraseña: ");
        String contrasenia = scanner.nextLine();

        if (!verificarDatos("CorreosContraseña.txt", correo, contrasenia)) {
            almacenarDatos("CorreosContraseña.txt", correo, contrasenia);
            System.out.println("Datos registrados correctamente");
        } else {
            System.out.println("El correo o la contraseña ya existen");
        }
    }

    public static void almacenarDatos(String archivo, String correo, String contrasenia) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write("Correo: " + correo + "/ Contraseña: " + contrasenia);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verificarDatos(String archivo, String correo, String contrasenia) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Correo: " + correo) || line.contains("Contraseña: " + contrasenia)) {
                    return true; // El correo o la contraseña ya existen
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El correo y la contraseña no existen
    }
}



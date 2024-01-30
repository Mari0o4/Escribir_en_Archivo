import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class InicioSesion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        System.out.println("REGISTRO DE FOORNUS\n");

        do {
            System.out.print("Introduce tu correo: ");
            String correo = scanner.nextLine();
            System.out.print("Introduce la contraseña: ");
            String contrasenia = scanner.nextLine();

            if (!verificarDatos("CorreosContraseña.txt", correo, contrasenia)) {
                almacenarDatos("CorreosContraseña.txt", correo, contrasenia);
                System.out.println("Datos registrados correctamente.\n");
            } else {
                System.out.println("El correo o la contraseña ya existen, prueba otra vez.\n");
            }
            System.out.println("¿Quieres registrar otro correo? (1-si/2-no)");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1:
                    scanner.nextLine();
                    break;

                case 2:
                    System.out.println("Saliendo...");
                    break;
            }

        }while(opcion<2);

    }

    public static void almacenarDatos(String archivo, String correo, String contrasenia) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write("Correo: " + correo + " / Contraseña: " + contrasenia);
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



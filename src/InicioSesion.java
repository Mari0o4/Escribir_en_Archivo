import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class InicioSesion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("BIENVENIDO A FOORNUS\n");
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1-Iniciar sesión");
            System.out.println("2-Registrar nuevo usuario");
            System.out.println("3-Salir");
            opcion = scanner.nextInt();


            switch (opcion) {
                case 1:
                    System.out.println("Inicio de Sesión:");
                    System.out.print("Introduce tu correo: ");
                    String correo = scanner.next();
                    System.out.print("Introduce la contraseña: ");
                    String contrasenia = scanner.next();


                    if (iniciarSesion("CorreosContraseña.txt", correo, contrasenia)) {
                        System.out.println("Inicio correcto, bienvenido.");
                        menuSesion();
                    } else {
                        System.out.println("Correo o contraseña incorrectos.");
                    }
                    break;

                case 2:
                    System.out.println("Registro:");
                    System.out.print("Introduce tu correo: ");
                    correo = scanner.nextLine();
                    System.out.print("Introduce la contraseña: ");
                    contrasenia = scanner.nextLine();

                    if (!verificarDatos("CorreosContraseña.txt", correo, contrasenia)) {
                        almacenarDatos("CorreosContraseña.txt", correo, contrasenia);
                        System.out.println("Datos registrados correctamente.");
                    } else {
                        System.out.println("El correo o la contraseña ya existen, prueba otra vez.\n");
                    }
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion < 3);
    }

    public static void menuSesion() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("BIENVENIDO A FOORNUS\n");
            System.out.println("1-Completa tu registro");
            System.out.println("2-Realiza nuestra encuesta");
            System.out.println("3-Compra tus entradas");
            System.out.println("4-Volver");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Completa tu registro:");
                    System.out.print("Nombre: ");
                    String nombre = scanner.next();
                    System.out.print("Apellidos: ");
                    String apellidos = scanner.next();
                    System.out.print("Número de teléfono: ");
                    String telefono = scanner.next();
                    System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
                    String fechaNacimiento = scanner.next();

                    almacenarRegistroCompleto("RegistroCompleto.txt", nombre, apellidos, telefono, fechaNacimiento);
                    System.out.println("Registro completado con éxito.");
                    break;

                case 2:

                    break;

                case 3:
                    System.out.println("Eventos disponibles por el momento:");
                    System.out.println("1-Apple vision");
                    System.out.println("2-Classroom");
                    System.out.println("3-ChatGpt");
                    System.out.println("4-Github");
                    System.out.println("5-Volver atrás");

                    int opcionCompra = scanner.nextInt();

                    switch (opcionCompra) {

                        case 1:
                            abrirEnlace("https://www.apple.com/apple-vision-pro/");
                            break;

                        case 2:
                            abrirEnlace("https://classroom.google.com/");
                            break;

                        case 3:
                            abrirEnlace("https://chat.openai.com/");
                            break;

                        case 4:
                            abrirEnlace("https://github.com/login");
                            break;

                        case 5:
                            System.out.println("Volviendo al menú principal.");
                            break;
                    }
            }
        } while (opcion < 4);
    }

    public static void abrirEnlace(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
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

    public static boolean iniciarSesion(String archivo, String correo, String contrasenia) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Correo: " + correo) && line.contains("Contraseña: " + contrasenia)) {
                    return true; // Inicio de sesión exitoso
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El correo o la contraseña no coinciden
    }

    public static void almacenarRegistroCompleto(String archivo, String nombre, String apellidos, String telefono, String fechaNacimiento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write("Nombre: " + nombre + " / Apellidos: " + apellidos + " / Teléfono: " + telefono + " / Fecha de nacimiento: " + fechaNacimiento);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


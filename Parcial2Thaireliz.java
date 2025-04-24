import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

// Clase Usuario
class Usuario {
    private String nombre;
    private String correo;
    private String contrasena;

    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Correo: " + correo;
    }
}

// Clase para validar campos con expresiones regulares
class Validador {
    public static boolean validarNombre(String nombre) {
        return nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{2,}$");
    }

    public static boolean validarCorreo(String correo) {
        return correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean validarContrasena(String pass) {
        if (pass.length() < 8) return false;

        int mayus = 0, minus = 0, digitos = 0;
        boolean especial = false;

        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) mayus++;
            else if (Character.isLowerCase(c)) minus++;
            else if (Character.isDigit(c)) digitos++;
            else especial = true;
        }

        return mayus >= 2 && minus >= 3 && digitos >= 1 && especial;
    }
}


public class Parcial2Thaireliz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Usuario> usuarios = new ArrayList<>();

        System.out.println("=== Registro de Usuarios ===");

        while (true) {
            System.out.print("Nombre (o 'fin' para salir): ");
            String nombre = sc.nextLine();
            if (nombre.equalsIgnoreCase("fin")) break;

            if (!Validador.validarNombre(nombre)) {
                System.out.println("❌ Nombre inválido. Solo letras y espacios, mínimo 2 caracteres.");
                continue;
            }

            System.out.print("Correo electrónico: ");
            String correo = sc.nextLine();
            if (!Validador.validarCorreo(correo)) {
                System.out.println("❌ Correo inválido.");
                continue;
            }

            System.out.print("Contraseña: ");
            String contrasena = sc.nextLine();
            if (!Validador.validarContrasena(contrasena)) {
                System.out.println("❌ Contraseña inválida. Mínimo 8 caracteres, 2 mayúsculas, 3 minúsculas, 1 número, 1 especial.");
                continue;
            }

            usuarios.add(new Usuario(nombre, correo, contrasena));
            System.out.println("✅ Usuario registrado exitosamente.\n");
        }

        System.out.println("\n📋 Usuarios registrados:");
        usuarios.forEach(System.out::println);
    }
}

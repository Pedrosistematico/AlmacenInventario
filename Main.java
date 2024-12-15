import java.util.Scanner;

public class Main {
    static String nombre[] = new String[10];
    static double precio[] = new double[10];
    static int cantidad[] = new int[10];
    static String categoria[] = {"verduras", "frutas", "utensilios", "limpieza"};
    static int contador = 0;
    static Usuario usuario = new Usuario("admin", "1234");
    
    public static void main(String[] args){
        int opcion;
        String Usuario;
        int contraseña;
        int longitud;
        Scanner intro = new Scanner(System.in);
        System.out.println("_____LOGIN_____");
        System.out.println("1: Ingresar usuario");
        System.out.println("2:registrarse");
        System.out.println("Seleccione una opcion");
        opcion = intro.nextInt();
        switch(opcion){
            case 1:
                for(int i=0;i<3;i++){
        System.out.println("Ingrese su usuario:");
        String userInput = intro.next();
        System.out.println("Ingrese su contraseña:");
        String contraInput = intro.next();
        
        if (usuario.validarUsuario(userInput, contraInput)) {
            System.out.println("Bienvenido");
            menu(intro); 
        } else {
            System.out.println("Usuario o contraseña incorrectos// Acceso denegado");
        }
         }
                break;
            case 2:
                System.out.println("Ingrese el nombre de usuario que desee");
                Usuario = intro.next();
                System.out.println("Ingrese la contraseña que desee");
                contraseña = intro.nextInt();
                for(int i=0;String.valueOf(contraseña).length()<8;i++){
                    System.out.println("La contraseña debe tener minimo 8 caracteres");
                    contraseña = intro.nextInt();
                }
                
                
                
                
        }
            
        
                
        

    } 
    public static void menu(Scanner intro){
        int opcion = 0;
        boolean opcionValida;

        do {
            System.out.println("\n1: Agregar producto");
            System.out.println("2: Quitar producto");
            System.out.println("3: Mostrar inventario");
            System.out.println("4: Salir");
            System.out.print("Seleccione una opcion: ");
            opcionValida = false;

            try {
                opcion = intro.nextInt();
                if (opcion < 1 || opcion > 4) {
                    System.out.println("Opcion no valida. Debe ser entre 1 y 4");
                } else {
                    opcionValida = true;
                }
            } catch (Exception e) {
                System.out.println("Entrada no valida. Debe ingresar un numero entero");
                intro.nextLine();
            }

            if (opcionValida) {
                switch (opcion) {
                    case 1:
                        Agregar(intro);
                        break;
                    case 2:
                        Quitar(intro);
                        break;
                    case 3:
                        Inventario();
                        break;
                    case 4:
                        System.out.println("Saliendo del sistema");
                        break;
                }
            }
        } while (opcion != 4);
    }
    
    public static void Agregar(Scanner intro) {
    if (contador < nombre.length) {
        String categoriaIngresada;
        boolean categoriaValida = false;
        do {
            System.out.println("Ingrese la categoria [verduras, frutas, utensilios, limpieza]:");
            categoriaIngresada = intro.next().toLowerCase();
            for (String cat : categoria) {
                if (cat.equalsIgnoreCase(categoriaIngresada)) {
                    categoriaValida = true;
                    break;
                }
            }

            if (!categoriaValida) {
                System.out.println("No pertenece a ninguna categoria valida. Intente nuevamente.");
            }
        } while (!categoriaValida);

        categoria[contador] = categoriaIngresada;
        System.out.println("Ingrese el nombre del producto:");
        nombre[contador] = intro.next();
        double precioIngresado = 0.0;
        boolean precioValido = false;
        do {
            System.out.println("Ingrese el precio del producto:");
            try {
                precioIngresado = Double.parseDouble(intro.next());
                if (precioIngresado > 0) {
                    precioValido = true;
                } else {
                    System.out.println("El precio debe ser mayor a 0. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Solo se permiten numeros. Intente nuevamente.");
            }
        } while (!precioValido);

        precio[contador] = precioIngresado;
        int cantidadIngresada = 0;
        boolean cantidadValida = false;
        do {
            System.out.println("Ingrese la cantidad del producto:");
            try {
                cantidadIngresada = Integer.parseInt(intro.next());
                if (cantidadIngresada >= 0) {
                    cantidadValida = true;
                } else {
                    System.out.println("La cantidad no puede ser negativa. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Solo se permiten numeros enteros. Intente nuevamente.");
            }
        } while (!cantidadValida);

        cantidad[contador] = cantidadIngresada;

        System.out.println("Producto agregado con exito.");
        contador++;
    } else {
        System.out.println("Inventario lleno. No se puede agregar mas productos.");
    }
}

    public static void Quitar(Scanner intro) {
        System.out.print("Ingrese el nombre del producto a retirar: ");
        String nombreProducto = intro.next();
        boolean encontrado = false;

        for (int i = 0; i < contador; i++) {
            if (nombre[i].equalsIgnoreCase(nombreProducto)) {
                System.out.print("Ingrese la cantidad a retirar: ");
                int cantidadARetirar = intro.nextInt();

                if (cantidad[i] >= cantidadARetirar) {
                    cantidad[i] -= cantidadARetirar;
                    System.out.println("Cantidad retirada");
                    if (cantidad[i] == 0) {
                        eliminarProducto(i);
                    }
                } else {
                    System.out.println("No hay suficiente cantidad para retirar");
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Producto no encontrado");
        }
    }

   public static void Inventario() {
    System.out.println("*_*_*_INVENTARIO_*_*_*");
    
    if (contador == 0) {
        System.out.println("No hay nada en el inventario");
    } else {
        for (int i = 0; i < contador; i++) {
            System.out.println("");
            System.out.println("Categoria: " + categoria[i]);
            System.out.println("Nombre: " + nombre[i]);
            System.out.println("Precio: " + precio[i]);
            System.out.println("Cantidad: " + cantidad[i]);
            System.out.println("Valor total: " + (precio[i] * cantidad[i]));
            System.out.println("");
        }
    }
}

    public static void eliminarProducto(int indice) {
        for (int i = indice; i < contador - 1; i++) {
            nombre[i] = nombre[i + 1];
            precio[i] = precio[i + 1];
            cantidad[i] = cantidad[i + 1];
        }
        contador--;
        System.out.println("Producto eliminado del inventario");
        
        
    }
}

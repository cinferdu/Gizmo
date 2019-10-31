package socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion {
	private final int PUERTO = 9999; //Puerto para la conexión
    private final String HOST = "localhost"; //Host para la conexión
    protected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket sServidor; //Socket del servidor
    protected Socket sCliente; //Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente; //Flujo de datos de salida

    public Conexion(String tipo) throws IOException //Constructor
    {
        if(tipo.equalsIgnoreCase("servidor"))
        {
            sServidor = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
            sCliente = new Socket(); //Socket para el cliente
        }
        else
        {
            sCliente = new Socket(HOST, PUERTO); //Socket para el cliente en localhost en puerto 1234
        }
    }
}

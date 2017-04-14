package com.example.chema.qradmin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import org.apache.commons.net.util.Base64;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

/**
 * Created by chema on 01/03/2017.
 */

public class base extends AsyncTask<String, Void, Alumno> {
    private String lo;
    private String pa;
    private Context ActivityActual;
    base(String us){
        lo=us;
    }

    @Override
    protected Alumno doInBackground(String... params) {
        try {
            String io = null;

            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());  //Network interfaces devuelve un enumerado con un listado de interfazes del movil
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses()); //Cogemos el siguiente objecto network interfaces y cogemos el inetaddress que no s la ip que usamos
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) { //metodo comprueba si la ip es localhost
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);


                        io = sAddr;
                        break;

                    }
                }
            }

            System.out.println(io);
            if (io.equals("10.10.4.150")) {

            } else {
                io = "www.iesmurgi.org:3306";
            }
            System.out.println(io);
            Connection con;//Creamos en objeto conexion
            Class.forName("com.mysql.jdbc.Driver");//cargamos la clase con los drivers mysql previamente tenemos que tener cargar los driver en la libreria
            con = DriverManager.getConnection("jdbc:mysql://" + io + "/base20173", "ubase20173", "pbase20173");

            Statement coger = con.createStatement();

            String sql = "select * from USUARIOS where user='" + lo + "'";

            ResultSet a = coger.executeQuery(sql);
            Alumno alumno = null;
            System.out.println("a");
            if (!a.next()) {
                System.out.println("no hay nada");


            } else {
                String uno = a.getString(1);
                String dos = a.getString(2);
                String tres = a.getString(3);
                    System.out.println("a"+tres);

                    sql = "select * from " + tres + " where usuario='" + uno + "'";
                    a = coger.executeQuery(sql);

                    if (a.next()) {

                        URL url=new URL(a.getString(4));
                        URLConnection uc = url.openConnection();
                        String userpass = "usegundodam" + ":" + "segundodam2017";
                        String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
                        uc.setRequestProperty ("Authorization", basicAuth);

                        InputStream in = uc.getInputStream();
                        Bitmap fot= BitmapFactory.decodeStream(in);

                        alumno = new Alumno(a.getString(2), tres, a.getString(5));
                        alumno.setApelli(a.getString(3));
                        alumno.setImage(fot);
                    }



            }

            return alumno;

        } catch (Exception e) {
            System.out.println("a" + e.toString());

            return null;
        }


    }

}

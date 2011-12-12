
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ViCoOo
 */
public class FaceB {
    private RandomAccessFile user=null;
    private RandomAccessFile pw=null;
    private RandomAccessFile friends= null;
    public static Scanner lea = new Scanner(System.in);
    
            public FaceB(){
               lea.useDelimiter(System.getProperty("line.separator"));
                try{
                    user= new RandomAccessFile("Profile.fbn","rw");
                    pw= new RandomAccessFile("Gerencia.fbn", "rw");
                    friends= new RandomAccessFile("manageFriends.fbn", "rw");

                }catch(Exception e){

                }
            }


            public void crearCuenta(String nombre,char genero,Date bday,String email,Date inicial
                    ,int tel, String status,long fechapost,String password)throws IOException{
                user.seek(user.length());
                pw.seek(pw.length());
                user.writeUTF(nombre);
                user.writeChar(genero);
                user.writeLong(bday.getTime());

                if(buscarEmail(email)){

                user.writeUTF(email);
                //Si la cuenta de correo no existe se crea en el archivo gerencial
                pw.writeUTF(email);

                }else{
                    System.out.println("Esta cuenta ya existe");
                }

                user.writeLong(inicial.getTime());
                user.writeInt(tel);
                user.writeUTF(status);
                user.writeLong(fechapost);
                //Creando el archivo gerencial
                pw.writeUTF(password);
                pw.writeBoolean(true);

            }



            public boolean buscarEmail(String email)throws IOException{
                pw.seek(pw.length());

                while(pw.getFilePointer()<pw.length()){
                    if((email.equalsIgnoreCase(pw.readUTF()))){
                        return false;

                    }else{
                        return true;
                    }
                }
                return true;
            }
}

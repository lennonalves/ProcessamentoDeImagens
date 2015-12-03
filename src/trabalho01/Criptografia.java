/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho01;


/**
 *
 * @author Usuario
 */
import org.jasypt.util.text.BasicTextEncryptor;

public class Criptografia {
    
public String CriptografarMensagem (String msg, String pass) {

    
    BasicTextEncryptor bte = new BasicTextEncryptor();

//inserimos uma senha qualquer
        bte.setPassword(pass);

//criamos uma String que recebe a senha criptografada
        String TextoCriptografado = bte.encrypt(msg);
        
        return TextoCriptografado;

    }


public String DescriptografarMensagem (String msg, String pass) {
    String textoDescriptografado = null;  
    try{
          
        BasicTextEncryptor bte = new BasicTextEncryptor();  
        bte.setPassword(pass);
        
        textoDescriptografado = bte.decrypt(msg);
        //System.out.println("Texto descriptografado = " + seuTextoNovamenteDescriptografado);
        

        }catch(Exception e){
            
        }
    return textoDescriptografado; 
      
}
}

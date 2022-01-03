package org.example.process;

public class UpperCase {

    public String formatoTexto(String caracteres){
        char [] texto = caracteres.toCharArray();
        texto [0] = Character.toUpperCase( texto[0] );
        for (int i = 0; i < texto.length ; i++) {
            if (texto[i]== ' '){
                texto[i + 1 ] = Character.toUpperCase( texto[i + 1] );
            }
        }
        return new String( texto);
    }
}

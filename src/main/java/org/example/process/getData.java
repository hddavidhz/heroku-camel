package org.example.process;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.example.pojo.Prestamo;
import org.example.pojo.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class getData implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        UpperCase upper = new UpperCase();

        String jsonString = exchange.getIn().getBody( String.class );
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable( DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT );
        JavaType type = mapper.getTypeFactory().constructParametrizedType( List.class, List.class, User.class );
        List<User> user = mapper.readValue( jsonString, type );

        //---------------------------------------------------------------

//        for (User item : user) {
//            List<Prestamo> prestamo = Arrays.asList( item.getPrestamos() );
//            prestamo.sort( Collections.reverseOrder() );
//        }
        user.stream().forEach( users -> {
            List<Prestamo> prestamos = Arrays.asList( users.getPrestamos() );
            prestamos.sort( Collections.reverseOrder() );
        });

        //---------------------------------------------------------------

        user.forEach( users -> {
            String nombres = upper.formatoTexto( users.getNombre() );
            users.setNombre( nombres );

            String apellidos = upper.formatoTexto( users.getApellido() );
            users.setApellido( apellidos );
        });

        //--------------------------------------------------------------

        exchange.getIn().setBody( user );

    }
}










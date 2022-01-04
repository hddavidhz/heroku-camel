package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.example.pojo.User;
import org.example.process.getData;
import org.example.util.ResponsEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyRouteBuilder extends RouteBuilder {

        public static String PORT = System.getenv("PORT");

    public void configure() {


        JacksonDataFormat jacksonDataFormat = new JacksonDataFormat( User.class );
        //jacksonDataFormat.setPrettyPrint( true );

        restConfiguration()
                .component( "jetty" )
                .enableCORS( true )
                .port( PORT );

        rest().path( "/listar" )
                .get()
                .route().routeId("MAIN")
                .removeHeaders( "CamelHttp*" )
                .setHeader( Exchange.HTTP_METHOD, constant( "GET " ) )
                .to( ResponsEnum.API_REST_CLIENTE.getDescription() )
                .convertBodyTo( String.class )
                .process( new getData() )
                .to( ResponsEnum.VELOCITY_TRANSFORM.getDescription() )
                .log( "...::::FIN DE LA RUTA GET:::..." );
//                //.log( "${body.get(0).getPrestamos[0].getId_prestamo}" )

//----------------------------------------------------------------------------------------------------------------------

        rest().path( "/prueba" )
                .get("/v1")
                .description( "DESCRIPCION PRUEBA V1" ).outType( User.class )
                .route().routeId("PRUEBA")
                .removeHeaders( "CamelHttp*" )
                .setHeader( Exchange.HTTP_METHOD, constant( "GET " ) )
                .to( ResponsEnum.API_REST_CLIENTE.getDescription() )
                .process( new getData() )
                .choice()
                    .when().simple( "${body.get(0).getNombre} == 'Maria Juliana'" ).to( "direct:casoA" )
                    .when().simple( "${body.get(1).getNombre} == 'diego alejandro'" ).to( "direct:casoB" )
                    .otherwise().to( "direct:casoC" )
                .log( "...::::FIN DE LA RUTA GET:::..." );


        from( "direct:casoA" )
                .routeId( "MARIA J." )
                .to( ResponsEnum.VELOCITY_TRANSFORM_2.getDescription() )
                .end();

        from( "direct:casoB" )
                .routeId( "DIEGO A." )
                .to( ResponsEnum.VELOCITY_TRANSFORM.getDescription() )
                .end();

        from( "direct:casoC" )
                .routeId( "CASO_C" )
                .log( "..:::CASO_C ::......." )
                .end();


        rest().path( "/listar" )
                .post()
                .route()
                .routeId( "POST" )
                .removeHeaders( "CamelHttp*" )
                .setHeader( Exchange.HTTP_METHOD, constant( "POST" ) )
                .to( ResponsEnum.API_REST_CLIENTE.getDescription() )
                .log( "...::::FIN DE LA RUTA POST:::..." )
                .end();


    }

}

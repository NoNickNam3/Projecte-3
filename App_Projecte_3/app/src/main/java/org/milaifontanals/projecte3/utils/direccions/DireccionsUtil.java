package org.milaifontanals.projecte3.utils.direccions;

import java.util.ArrayList;
import java.util.List;

public class DireccionsUtil {

    /***
     *
     * @param entrada
     * entrada es una coordenada separada per ',' en format double
     * @return retorna una llista de coordenades (2 coords)
     */
    public static List<Double> parseCoords(String entrada){
        ArrayList<Double> coords = new ArrayList<>();
        String [] strCoords;

        strCoords = entrada.split(",");

        for(String x: strCoords){
            coords.add(Double.parseDouble(x));
        }

        if(coords.size() == 2){
            return coords;
        }

        return null;
    }



}

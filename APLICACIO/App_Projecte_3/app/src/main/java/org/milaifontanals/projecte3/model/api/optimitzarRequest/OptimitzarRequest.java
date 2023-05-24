package org.milaifontanals.projecte3.model.api.optimitzarRequest;

public class OptimitzarRequest {

    private CoordenadesRequest coordenadas;

    public OptimitzarRequest(CoordenadesRequest coordenades) {
        this.coordenadas = coordenades;
    }

    public CoordenadesRequest getCoordenades() {
        return coordenadas;
    }

    public void setCoordenades(CoordenadesRequest coordenades) {
        this.coordenadas = coordenades;
    }
}

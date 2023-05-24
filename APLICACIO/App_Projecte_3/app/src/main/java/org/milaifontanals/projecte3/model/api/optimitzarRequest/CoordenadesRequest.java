package org.milaifontanals.projecte3.model.api.optimitzarRequest;

import java.util.List;

public class CoordenadesRequest {
    String sortida;
    List<CoordsParada> parades;

    public CoordenadesRequest(String sortida, List<CoordsParada> parades) {
        setSortida(sortida);
        setParades(parades);
    }

    public String getSortida() {
        return sortida;
    }

    public void setSortida(String sortida) {
        this.sortida = sortida;
    }

    public List<CoordsParada> getParades() {
        return parades;
    }

    public void setParades(List<CoordsParada> parades) {
        this.parades = parades;
    }
}

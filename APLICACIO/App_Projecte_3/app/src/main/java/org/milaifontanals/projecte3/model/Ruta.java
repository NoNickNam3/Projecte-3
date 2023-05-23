package org.milaifontanals.projecte3.model;

import org.milaifontanals.projecte3.model.api.apiRuta.DataGetRutas;
import org.milaifontanals.projecte3.model.api.apiRuta.RespostaGetRutas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ruta {

    int id;
    LocalDateTime moment;
    String nomRuta;

    private static List<Ruta> _mRutes;

    public Ruta(int id, LocalDateTime moment) {
        this.id = id;
        this.moment = moment;
    }

    public Ruta(int id, LocalDateTime moment, String nomRuta) {
        this.id = id;
        this.moment = moment;
        this.nomRuta = nomRuta;
    }

    public static List<Ruta> setRutes(RespostaGetRutas ll) {
        List<Ruta> res = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for(DataGetRutas d: ll.getData()){
            res.add(new Ruta(d.getId(), LocalDateTime.parse(d.getMomentoTemporal(), formatter), d.getNombre()));
        }

        _mRutes = res;

        return res;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public static List<Ruta> getRutes() {

        if(_mRutes == null){
            _mRutes = new ArrayList<Ruta>();
        }
        if(_mRutes.size() == 0){
            _mRutes.add(new Ruta(0, LocalDateTime.now()));
        }

        return _mRutes;
    }

    public String getNomRuta() {
        return nomRuta;
    }

    public void setNomRuta(String nomRuta) {
        this.nomRuta = nomRuta;
    }
}

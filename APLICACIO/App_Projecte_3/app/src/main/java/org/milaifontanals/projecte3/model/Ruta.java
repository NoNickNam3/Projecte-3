package org.milaifontanals.projecte3.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ruta {

    int id;
    LocalDateTime moment;

    private static ArrayList<Ruta> _mRutes;

    public Ruta(int id, LocalDateTime moment) {
        this.id = id;
        this.moment = moment;
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

    public static ArrayList<Ruta> getRutes() {

        _mRutes = new ArrayList<Ruta>();

        if(_mRutes.size() == 0){
            _mRutes.add(new Ruta(0, LocalDateTime.now()));
        }

        return _mRutes;
    }
}

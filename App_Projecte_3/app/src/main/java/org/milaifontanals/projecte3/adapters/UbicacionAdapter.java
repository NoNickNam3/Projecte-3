package org.milaifontanals.projecte3.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.model.Ubicacion;

import java.util.List;

public class UbicacionAdapter extends RecyclerView.Adapter<UbicacionAdapter.ViewHolder>{

    private List<Ubicacion> mUbicaciones;
    private Context mContext;

    public UbicacionAdapter(List<Ubicacion> pUbicaciones, Context c){
        mUbicaciones = pUbicaciones;
        mContext = c;
    }

    @Override
    public int getItemCount() {
        return mUbicaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txvId;
        TextView txvNombre;
        TextView txvDireccion;
        TextView txvCoordenada;
        TextView txvObservacion;
        CheckBox cbxFav;

        public ViewHolder(@NonNull View filaActual) {
            super(filaActual);
            txvId = filaActual.findViewById(R.id.txvIdUbicacion);
            txvNombre = filaActual.findViewById(R.id.txvNombreUbicacion);
            txvDireccion = filaActual.findViewById(R.id.txvDireccionUbicacion);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_ubicacion, parent, false);
        ViewHolder vh = new ViewHolder(fila);

        /**
         * ========================================
         * Metodos de interacción con las casillas
         * ========================================
         */

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ubicacion u = mUbicaciones.get(position);

        holder.txvId.setText("" + u.getId());
        holder.txvNombre.setText("" + u.getNombre());
        holder.txvDireccion.setText("" + u.getDirecccion());

        Log.d("UBICACION RECIBIDA", "Actualizando la ubicacion en la posicion  " + position);
    }

}

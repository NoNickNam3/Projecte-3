package org.milaifontanals.projecte3.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.model.Ruta;
import org.milaifontanals.projecte3.model.Ubicacion;
import org.milaifontanals.projecte3.utils.comprovacions.ComprovacionsUtil;

import java.time.LocalDateTime;
import java.util.List;

public class RutaAdapter extends RecyclerView.Adapter<RutaAdapter.ViewHolder> {

    private List<Ruta> mRutes;
    private RutaAdapter.OnRutaClickedListener mListener;
    private Context mContext;

    public RutaAdapter(List<Ruta> mRutes, Context mContext) {
        this.mRutes = mRutes;
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        return mRutes.size();
    }

    public void setOnRutaClickedListener(RutaAdapter.OnRutaClickedListener listener){
        this.mListener = listener;
    }

    public interface OnRutaClickedListener{
        public void onRutaClicked(Ruta r);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView momentTemporal;
        TextView horaRuta;
        TextView nombreRuta;

        public ViewHolder(@NonNull View filaActual) {
            super(filaActual);
            momentTemporal = filaActual.findViewById(R.id.txvMomentRuta);
            horaRuta = filaActual.findViewById(R.id.txvHoraRuta);
            nombreRuta = filaActual.findViewById(R.id.txvNombreRuta);
        }
    }

    @NonNull
    @Override
    public RutaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_ruta, parent, false);
        RutaAdapter.ViewHolder vh = new RutaAdapter.ViewHolder(fila);
        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onRutaClicked(mRutes.get(vh.getAdapterPosition()));
                }
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RutaAdapter.ViewHolder holder, int position) {
        Ruta r = mRutes.get(position);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.weight = 0.0f;

        LocalDateTime ldt = r.getMoment();

        holder.momentTemporal.setText("" + ldt.getDayOfWeek() + ", " + ldt.getDayOfMonth() + " " + ldt.getMonth() + " " + ldt.getYear());
        holder.horaRuta.setText("" + ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond());
        holder.nombreRuta.setText("" + ComprovacionsUtil.getStringNN(r.getNomRuta()));
        if(holder.nombreRuta.getText().equals("")){
            holder.nombreRuta.setText("Esta ruta no tiene nombre");
        }

        Log.d("XXX", "Actualizando la ubicacion en la posicion  " + position);
    }
}

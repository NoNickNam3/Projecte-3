// Generated by view binder compiler. Do not edit!
package org.milaifontanals.projecte3.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.milaifontanals.projecte3.R;

public final class FilaUbicacionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final GridLayout grdl;

  @NonNull
  public final LinearLayout lloNombreDireccion;

  @NonNull
  public final TextView txvDireccionUbicacion;

  @NonNull
  public final TextView txvNombreUbicacion;

  @NonNull
  public final TextView txvObservacionUbicacion;

  @NonNull
  public final View vieSelected;

  private FilaUbicacionBinding(@NonNull ConstraintLayout rootView, @NonNull GridLayout grdl,
      @NonNull LinearLayout lloNombreDireccion, @NonNull TextView txvDireccionUbicacion,
      @NonNull TextView txvNombreUbicacion, @NonNull TextView txvObservacionUbicacion,
      @NonNull View vieSelected) {
    this.rootView = rootView;
    this.grdl = grdl;
    this.lloNombreDireccion = lloNombreDireccion;
    this.txvDireccionUbicacion = txvDireccionUbicacion;
    this.txvNombreUbicacion = txvNombreUbicacion;
    this.txvObservacionUbicacion = txvObservacionUbicacion;
    this.vieSelected = vieSelected;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FilaUbicacionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FilaUbicacionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fila_ubicacion, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FilaUbicacionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.grdl;
      GridLayout grdl = ViewBindings.findChildViewById(rootView, id);
      if (grdl == null) {
        break missingId;
      }

      id = R.id.lloNombreDireccion;
      LinearLayout lloNombreDireccion = ViewBindings.findChildViewById(rootView, id);
      if (lloNombreDireccion == null) {
        break missingId;
      }

      id = R.id.txvDireccionUbicacion;
      TextView txvDireccionUbicacion = ViewBindings.findChildViewById(rootView, id);
      if (txvDireccionUbicacion == null) {
        break missingId;
      }

      id = R.id.txvNombreUbicacion;
      TextView txvNombreUbicacion = ViewBindings.findChildViewById(rootView, id);
      if (txvNombreUbicacion == null) {
        break missingId;
      }

      id = R.id.txvObservacionUbicacion;
      TextView txvObservacionUbicacion = ViewBindings.findChildViewById(rootView, id);
      if (txvObservacionUbicacion == null) {
        break missingId;
      }

      id = R.id.vieSelected;
      View vieSelected = ViewBindings.findChildViewById(rootView, id);
      if (vieSelected == null) {
        break missingId;
      }

      return new FilaUbicacionBinding((ConstraintLayout) rootView, grdl, lloNombreDireccion,
          txvDireccionUbicacion, txvNombreUbicacion, txvObservacionUbicacion, vieSelected);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

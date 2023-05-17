package org.milaifontanals.projecte3.utils.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.milaifontanals.projecte3.R;
import org.milaifontanals.projecte3.ui.crearUbicacio.DialogFragmentUbicacio;

public class DialogUtils {
    public static void obrirDialogAddUbicacio(FragmentManager f) {
        DialogFragmentUbicacio d = new DialogFragmentUbicacio();
        d.setCancelable(true);

        d.show(f, "");
    }
}

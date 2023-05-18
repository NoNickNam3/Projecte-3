package org.milaifontanals.projecte3.utils.dialogs;

import androidx.fragment.app.FragmentManager;

import org.milaifontanals.projecte3.ui.dialogFragments.DialogFragmentEnllacEmpresa;
import org.milaifontanals.projecte3.ui.dialogFragments.DialogFragmentUbicacio;

public class DialogUtils {
    public static void obrirDialogAddUbicacio(FragmentManager f) {
        DialogFragmentUbicacio d = new DialogFragmentUbicacio();
        d.setCancelable(true);

        d.show(f, null);
    }

    public static void obrirDialogenllacEmpresa(FragmentManager f) {
        DialogFragmentEnllacEmpresa d = new DialogFragmentEnllacEmpresa();
        d.setCancelable(true);

        d.show(f, null);
    }
}

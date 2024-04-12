package ru.mirea.tikhonovva.dialog;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;


public class MyProgressDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new ProgressDialog(getActivity());
    }

}

package com.farhan.sps.utils;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.farhan.sps.interfaces.DialogSingleButtonListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BaseFragment extends Fragment {
    SweetAlertDialog alertDialog;


    public void showProgressDialog(Context context) {
        alertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        alertDialog.setTitleText("Loading");
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void hideProgressDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.hide();
        }
    }

    protected void dismissProgressDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    public void showSingleButtonDialog(Context context, String messageString, final DialogSingleButtonListener listener) {

        alertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        alertDialog.setTitleText("Info");
        alertDialog.setCancelable(false);
        alertDialog.setContentText(messageString);
        alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                listener.positiveButtonClicked(alertDialog);
            }
        });
        alertDialog.show();

    }

}

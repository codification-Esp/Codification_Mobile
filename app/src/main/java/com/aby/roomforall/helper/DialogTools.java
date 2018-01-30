package com.aby.roomforall.helper;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.aby.roomforall.R;

import java.io.Serializable;


public class DialogTools implements Serializable {

    private DialogTools() {}

    private static DialogTools instance = null;

    public static DialogTools getInstance() {
        if (instance == null) {
            synchronized (DialogTools.class) {
                if (instance == null) instance = new DialogTools();
            }
        }
        return instance;
    }

    AlertDialog b;
    AlertDialog.Builder dialogBuilder;

    public void ShowProgressDialog(Context context) {
        dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.progress_dialog_popup, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        b = dialogBuilder.create();
        b.show();
    }

    public void HideProgressDialog(){
        b.dismiss();
    }
}

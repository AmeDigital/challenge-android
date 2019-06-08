package com.amedigital.alodjinha.util;

import android.graphics.Paint;
import android.widget.TextView;

import java.util.Locale;

public class FormataCampoUtil {

    public static String formatarDecimal(double valor){
        return String.format(Locale.getDefault(), "%.2f", valor);
    }

    public static void riscarTextView(TextView txtView){
        txtView.setPaintFlags(txtView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}

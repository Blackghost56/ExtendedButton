package com.blackghost.extendedbuttons;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.ma.extendedbuttons.R;


public class ImageButtonWithConfirmationDialog extends androidx.appcompat.widget.AppCompatImageButton {

    private Dialog dialog;
    private String title, msg;
    private Drawable ic;

    public ImageButtonWithConfirmationDialog(@NonNull Context context) {
        this(context, null);
    }

    public ImageButtonWithConfirmationDialog(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageButtonWithConfirmationDialog(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageButtonWithConfirmationDialog, defStyleAttr, 0);

        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);

            if (attr == R.styleable.ImageButtonWithConfirmationDialog_title) {
                title = a.getString(attr);
            } else if (attr == R.styleable.ImageButtonWithConfirmationDialog_msg) {
                msg = a.getString(attr);
            } else if (attr == R.styleable.ImageButtonWithConfirmationDialog_ico) {
                ic = a.getDrawable(attr);
            } else if (attr == R.styleable.ImageButtonWithConfirmationDialog_onConfirmPressed) {
                if (context.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot "
                            + "be used within a restricted context");
                }

                final String handlerName = a.getString(attr);
                if (handlerName != null) {
                    setOnConfirmPressed(new DeclaredOnClickListener(this, handlerName));
                }
            }

        }

        a.recycle();

        if (title == null)
            title = context.getString(R.string.dialog_title_def);

        if (msg == null)
            msg = context.getString(R.string.dialog_msg_def);

        if (ic == null)
            ic = ContextCompat.getDrawable(context, R.drawable.ic_round_warning_24);

        makeDialog();

        setOnClickListener(v -> {
            if (dialog != null) {
                dialog.show();
            }
        });
    }

    public OnClickListener onConfirmPressedListener;

    public void setOnConfirmPressed(@Nullable OnClickListener l){
        onConfirmPressedListener = l;
    }

    private void makeDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setIcon(ic);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            if (onConfirmPressedListener != null)
                onConfirmPressedListener.onClick(this);
            dialog.cancel();
        });
        dialogBuilder.setNegativeButton(android.R.string.cancel, (dialog1, which) -> dialog.cancel());
        dialog = dialogBuilder.create();
    }
}

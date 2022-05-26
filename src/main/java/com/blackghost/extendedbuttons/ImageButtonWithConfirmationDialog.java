package com.blackghost.extendedbuttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

public class ImageButtonWithConfirmationDialog extends androidx.appcompat.widget.AppCompatImageButton {
    public ImageButtonWithConfirmationDialog(@NonNull Context context) {
        super(context);
    }

    public ImageButtonWithConfirmationDialog(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageButtonWithConfirmationDialog(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

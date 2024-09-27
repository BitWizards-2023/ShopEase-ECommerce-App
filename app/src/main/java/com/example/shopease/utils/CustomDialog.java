package com.example.shopease.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopease.R;

public class CustomDialog {

    public static void showDialog(Context context, boolean isSuccess, String title, String message) {
        // Create the dialog
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);

        // Set background to transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // Get the elements in the dialog
        ImageView iconImageView = dialog.findViewById(R.id.dialog_icon);
        TextView titleTextView = dialog.findViewById(R.id.dialog_title);
        TextView messageTextView = dialog.findViewById(R.id.dialog_message);

        // Set the title and message
        titleTextView.setText(title);
        messageTextView.setText(message);

        // Set the icon and color based on whether it's a success or error message
        if (isSuccess) {
            iconImageView.setImageResource(R.drawable.ic_success);  // Set your success icon here
            iconImageView.setColorFilter(context.getResources().getColor(R.color.green)); // Success color
        } else {
            iconImageView.setImageResource(R.drawable.ic_error);  // Set your error icon here
            iconImageView.setColorFilter(context.getResources().getColor(R.color.red)); // Error color
        }

        // Show the dialog
        dialog.show();
    }
}

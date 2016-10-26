package es.costular.gistchallenge.ui.dialogs;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import es.costular.gistchallenge.R;

/**
 * Created by costular on 26/10/16.
 */

public class InfoDialog {

    public static AlertDialog makeDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        View dialogView = inflater.inflate(R.layout.dialog_info, null);
        TextView info = (TextView) dialogView.findViewById(R.id.info_text);

        Spannable span = new SpannableString(context.getString(R.string.made_by));
        Drawable icon = ContextCompat.getDrawable(context, R.drawable.ic_favorite);
        icon.setBounds(0, 0, 48, 48);

        ImageSpan love = new ImageSpan(icon, ImageSpan.ALIGN_BASELINE);
        span.setSpan(love, 9, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        info.setText(span);

        builder.setView(dialogView);
        builder.setTitle("Info")
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        return builder.create();
    }

}



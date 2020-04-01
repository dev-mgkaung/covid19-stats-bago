package gov.mm.covid19statsbago.mmfont.components;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.Html;
import gov.mm.covid19statsbago.mmfont.MMFontUtils;

/**
 * Created by mgkaung on 4/1/2020.
 */
@SuppressWarnings("deprecation")
public class MMProgressDialog extends ProgressDialog {

    public MMProgressDialog(Context context) {
        super(context);
    }

    public MMProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void setMessage(CharSequence message) {
        if (!MMFontUtils.isSupportUnicode()) {
            super.setMessage(Html.fromHtml(MMFontUtils.uni2zg(message.toString())));
        } else {
            super.setMessage(Html.fromHtml(message.toString()));
        }
    }
}

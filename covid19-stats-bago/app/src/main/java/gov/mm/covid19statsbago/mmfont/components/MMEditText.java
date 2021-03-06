package gov.mm.covid19statsbago.mmfont.components;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import gov.mm.covid19statsbago.mmfont.MMFontUtils;


/**
 * Created by mgkaung on 4/1/2020.
 */

public class MMEditText extends AppCompatEditText {

    public MMEditText(Context context) {
        super(context);
    }

    public MMEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MMEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!MMFontUtils.isSupportUnicode()) {
            super.setText(Html.fromHtml(MMFontUtils.mmText(text, MMFontUtils.TEXT_UNICODE, true, true)), type);
        } else {
            super.setText(Html.fromHtml(text.toString()), type);
        }
    }
}

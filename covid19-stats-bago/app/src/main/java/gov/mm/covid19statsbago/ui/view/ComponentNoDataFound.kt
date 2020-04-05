package gov.mm.covid19statsbago.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.util.getInflateView
import kotlinx.android.synthetic.main.view_not_found_data.view.*

/**
 * @author kyawhtut
 * @date 05/04/2020
 */
class ComponentNoDataFound : FrameLayout {

    private var onClickListener: (View) -> Unit = {}
    fun setOnClickListener(onClickListener: (View) -> Unit) {
        this.onClickListener = onClickListener
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        addView(context.getInflateView(R.layout.view_not_found_data, this))
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        pno_ok.setOnClickListener {
            onClickListener(it)
        }
    }

    fun setMessage(text: String) {
        pno_message.text = text
    }
}
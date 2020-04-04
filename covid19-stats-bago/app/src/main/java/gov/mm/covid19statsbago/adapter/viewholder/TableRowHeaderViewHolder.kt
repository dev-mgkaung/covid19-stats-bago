package gov.mm.covid19statsbago.adapter.viewholder

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.datas.TableRowHeaderVO
import gov.mm.covid19statsbago.mmfont.components.MMTextView
import gov.mm.covid19statsbago.util.getColorValue
import kotlinx.android.synthetic.main.tableview_row_header_layout.view.*

/**
 * @author kyawhtut
 * @date 02/04/2020
 */

class TableRowHeaderViewHolder(private val view: View) : AbstractViewHolder(view) {

    private val tvRowData: MMTextView by lazy {
        view.tv_row_header
    }

    fun bind(data: TableRowHeaderVO) {
        tvRowData.text = data.data
        view.tv_row_header.requestLayout()
    }

    override fun setSelected(selectionState: SelectionState?) {
        super.setSelected(selectionState)
        view.setBackgroundColor(
            view.context.getColorValue(
                when (selectionState) {
                    SelectionState.SELECTED -> R.color.selected_background_color
                    SelectionState.UNSELECTED -> R.color.unselected_header_background_color
                    else -> R.color.shadow_background_color
                }
            )
        )
        tvRowData.setTextColor(
            view.context.getColorValue(
                when (selectionState) {
                    SelectionState.SELECTED -> R.color.selected_text_color
                    else -> R.color.unselected_text_color
                }
            )
        )
    }
}

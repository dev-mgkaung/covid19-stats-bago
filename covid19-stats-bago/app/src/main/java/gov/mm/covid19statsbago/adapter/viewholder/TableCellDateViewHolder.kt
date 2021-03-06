package gov.mm.covid19statsbago.adapter.viewholder

import android.view.View
import android.widget.LinearLayout
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.datas.TableCellVO
import gov.mm.covid19statsbago.util.getColorValue
import kotlinx.android.synthetic.main.tableview_cell_date_layout.view.*
import kotlinx.android.synthetic.main.tableview_cell_item_layout.view.*

/**
 * @author kyawhtut
 * @date 02/04/2020
 */
class TableCellDateViewHolder(private val view: View) : AbstractViewHolder(view) {

    fun bind(data: TableCellVO) {
        view.tv_date.text = data.data as String
    //    view.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
      //  view.tv_date.requestLayout()
    }
    override fun setSelected(selectionState: SelectionState) {
        super.setSelected(selectionState)

        view.tv_date.setTextColor(
            view.context.getColorValue(
                if (selectionState == SelectionState.SELECTED) R.color.selected_text_color
                else R.color.colorPrimaryDark
            )
        )
    }
}

package gov.mm.covid19statsbago.adapter.viewholder

import android.view.View
import android.widget.LinearLayout
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.datas.TableCellVO
import gov.mm.covid19statsbago.util.getColorValue
import kotlinx.android.synthetic.main.tableview_cell_item_layout.view.*

/**
 * @author kyawhtut
 * @date 02/04/2020
 */
class TableCellItemViewHolder(private val view: View) : AbstractViewHolder(view) {

    fun bind(data: TableCellVO, columnPosition: Int) {
        view.tv_cell_data.apply {
            text = data.data as String
        }
        requestLayout()
    }

    private fun requestLayout() {
        view.cell_container.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        view.tv_cell_data.requestLayout()
    }

    override fun setSelected(selectionState: SelectionState) {
        super.setSelected(selectionState)

        view.tv_cell_data.setTextColor(
            view.context.getColorValue(
                if (selectionState == SelectionState.SELECTED) R.color.selected_text_color
                else R.color.colorPrimaryDark
            )
        )
    }
}

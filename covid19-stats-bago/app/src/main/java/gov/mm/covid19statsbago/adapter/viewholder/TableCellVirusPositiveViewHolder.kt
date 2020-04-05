package gov.mm.covid19statsbago.adapter.viewholder

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.datas.TableCellVO
import gov.mm.covid19statsbago.generals.toUniNumber
import gov.mm.covid19statsbago.util.getColorValue
import kotlinx.android.synthetic.main.tableview_cell_date_layout.view.*

/**
 * Created by Mg Kaung on 4/5/2020.
 */

class TableCellVirusPositiveViewHolder(private val view: View) : AbstractViewHolder(view) {

    fun bind(data: TableCellVO, columnPosition: Int) {
        view.tv_date.apply {
            text = data.data as String
        }
        requestLayout(view.tv_date.text.toString())
    }

    private fun requestLayout(text: String) {
        view.tv_date.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        if(text.toInt()>0) {
            view.tv_date.setPadding(8,8,8,8)
            view.tv_date.setTextColor (view.context.getColorValue(
                R.color.colorWhite
            ))
            view.tv_date.setBackgroundColor(Color.parseColor("#FB8C00"))
        }  else{  view.tv_date.setBackgroundColor(Color.WHITE)}
        view.tv_date.text=text.toString().toUniNumber()
        view.tv_date.requestLayout()
    }

    override fun setSelected(selectionState: SelectionState) {
        super.setSelected(selectionState)

        view.tv_date.setTextColor(
            view.context.getColorValue(
                if (selectionState == SelectionState.SELECTED) R.color.unselected_text_color
                else R.color.unselected_text_color
            )
        )
    }
}

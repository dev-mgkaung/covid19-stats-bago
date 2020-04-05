package gov.mm.covid19statsbago.adapter.viewholder

import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginLeft
import com.evrencoskun.tableview.ITableView
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder
import com.evrencoskun.tableview.sort.SortState
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.datas.TableColumnHeaderVO
import gov.mm.covid19statsbago.mmfont.components.MMTextView
import gov.mm.covid19statsbago.util.getColorValue
import kotlinx.android.synthetic.main.tableview_column_header_layout.view.*
import kotlinx.android.synthetic.main.tableview_row_header_layout.view.*

/**
 * @author kyawhtut
 * @date 02/04/2020
 */

class TableColumnHeaderViewHolder(private val view: View, private val table: ITableView) :
    AbstractSorterViewHolder(view) {

    private val container: ConstraintLayout by lazy {
        view.column_header_container
    }
    private val btnSort: ImageView by lazy {
      view.column_header_sort_imageButton
    }
    private val tvCellData: MMTextView by lazy {
        view.column_header_textView
    }

    init {
        btnSort.setOnClickListener {
            table.sortColumn(
                adapterPosition,
                when (sortState) {
                    SortState.DESCENDING -> SortState.ASCENDING
                    else -> SortState.DESCENDING
                }
            )
        }
    }

    fun bind(data: TableColumnHeaderVO, columnPosition: Int) {
        tvCellData.apply {
            text = data.data
        }
        container.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        container.maxWidth=300
        requestLayout()
    }

    private fun requestLayout() {
        tvCellData.requestLayout()
        btnSort.requestLayout()
        container.requestLayout()
        view.requestLayout()
    }

    override fun setSelected(selectionState: SelectionState?) {
        super.setSelected(selectionState)

        container.setBackgroundColor(
            view.context.getColorValue(
                when (selectionState) {
                    SelectionState.SELECTED -> R.color.selected_background_color
                    SelectionState.UNSELECTED -> R.color.unselected_background_color
                    else -> R.color.shadow_background_color
                }
            )
        )
        tvCellData.setTextColor(
            view.context.getColorValue(
                when (selectionState) {
                    SelectionState.SELECTED -> R.color.selected_text_color
                    else -> R.color.unselected_text_color
                }
            )
        )
    }

    override fun onSortingStatusChanged(pSortState: SortState) {
        super.onSortingStatusChanged(pSortState)
        container.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT

        controlSortState(pSortState)

        requestLayout()
    }

    private fun controlSortState(stortState: SortState) {
        btnSort.apply {
            visibility = View.VISIBLE
            when (stortState) {
                SortState.ASCENDING -> {
                    setImageResource(R.drawable.ic_arrow_drop_down_black)
                }
                SortState.DESCENDING -> {
                    setImageResource(R.drawable.ic_arrow_drop_up_black)
                }
                else -> visibility = View.INVISIBLE
            }
        }
    }
}

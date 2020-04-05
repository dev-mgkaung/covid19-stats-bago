package gov.mm.covid19statsbago.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.evrencoskun.tableview.adapter.AbstractTableAdapter
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.adapter.viewholder.*
import gov.mm.covid19statsbago.datas.TableCellVO
import gov.mm.covid19statsbago.datas.TableColumnHeaderVO
import gov.mm.covid19statsbago.datas.TableRowHeaderVO
import gov.mm.covid19statsbago.util.getInflateView

/**
 * @author kyawhtut
 * @date 02/04/2020
 */
class TreatmentAdapter(
    private val ctx: Context,
    private var lastDatePosition: Int = -1
) : AbstractTableAdapter<TableColumnHeaderVO, TableRowHeaderVO, TableCellVO>(ctx) {

    companion object {
        const val COUNTRY_NAME_TYPE = 1
    }

    override fun onCreateCellViewHolder(parent: ViewGroup?, viewType: Int): AbstractViewHolder {
        return when (viewType) {
            COUNTRY_NAME_TYPE -> TableCellDateViewHolder(
                ctx.getInflateView(
                    R.layout.tableview_cell_date_layout,
                    parent,
                    false
                )
            )
            else -> TableCellItemViewHolder(
                ctx.getInflateView(
                    R.layout.tableview_cell_item_layout,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindCellViewHolder(
        holder: AbstractViewHolder,
        cellItemModel: Any?,
        columnPosition: Int,
        rowPosition: Int
    ) {
        when (holder) {
            is TableCellItemViewHolder -> {
                holder.bind(cellItemModel as TableCellVO, columnPosition)
            }
            is TableCellDateViewHolder -> {
                holder.bind(cellItemModel as TableCellVO)
            }
        }
    }

    override fun onCreateColumnHeaderViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): AbstractViewHolder {
        return when (viewType) {
            else -> TableColumnHeaderViewHolder(
                ctx.getInflateView(
                    R.layout.tableview_column_header_layout,
                    parent,
                    false
                ),
                tableView
            )
        }
    }

    override fun onBindColumnHeaderViewHolder(
        holder: AbstractViewHolder?,
        columnHeaderItemModel: Any?,
        columnPosition: Int
    ) {
        when (holder) {
            is TableColumnHeaderViewHolder -> holder.bind(
                columnHeaderItemModel as TableColumnHeaderVO,
                columnPosition
            )
        }
    }

    override fun onCreateRowHeaderViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): AbstractViewHolder {
        return TableRowHeaderViewHolder(
            ctx.getInflateView(
                R.layout.tableview_row_header_layout,
                parent,
                false
            )
        )
    }

    override fun onBindRowHeaderViewHolder(
        holder: AbstractViewHolder?,
        rowHeaderItemModel: Any?,
        rowPosition: Int
    ) {
        (holder as TableRowHeaderViewHolder).bind(rowHeaderItemModel as TableRowHeaderVO)
    }

    override fun onCreateCornerView(): View {
        return ctx.getInflateView(
            R.layout.tableview_corner_layout, null
        )
    }

    override fun getColumnHeaderItemViewType(position: Int): Int {
        return getColumnHeaderType(position)
    }

    override fun getRowHeaderItemViewType(position: Int): Int {
        return 0
    }

    override fun getCellItemViewType(position: Int): Int {
        return getCellViewType(position)
    }

    private fun getColumnHeaderType(column: Int): Int = when (column) {
        else -> TreatmentAdapter.COUNTRY_NAME_TYPE
    }

    private fun getCellViewType(column: Int): Int = when (column) {
        0 -> COUNTRY_NAME_TYPE
        1 -> COUNTRY_NAME_TYPE
        2 -> COUNTRY_NAME_TYPE
        3->  COUNTRY_NAME_TYPE

        else -> 0
    }
}

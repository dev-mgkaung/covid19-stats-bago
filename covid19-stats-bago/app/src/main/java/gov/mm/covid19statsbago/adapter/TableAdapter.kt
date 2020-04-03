package gov.mm.covid19statsbago.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.evrencoskun.tableview.adapter.AbstractTableAdapter
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.adapter.viewholder.TableCellDateViewHolder
import gov.mm.covid19statsbago.adapter.viewholder.TableCellItemViewHolder
import gov.mm.covid19statsbago.adapter.viewholder.TableColumnHeaderViewHolder
import gov.mm.covid19statsbago.adapter.viewholder.TableRowHeaderViewHolder
import gov.mm.covid19statsbago.datas.TableCellVO
import gov.mm.covid19statsbago.datas.TableColumnHeaderVO
import gov.mm.covid19statsbago.datas.TableRowHeaderVO
import gov.mm.covid19statsbago.util.getInflateView

/**
 * @author kyawhtut
 * @date 02/04/2020
 */
class TableAdapter(
    private val ctx: Context,
    private var lastDatePosition: Int = -1
) : AbstractTableAdapter<TableColumnHeaderVO, TableRowHeaderVO, TableCellVO>(ctx) {

    companion object {
        const val DATE_TYPE = 1
        const val ACTION_TYPE = 2
        const val COLOR_TYPE = 3
        const val ACTIVE_TYPE = 4
    }

    override fun onCreateCellViewHolder(parent: ViewGroup?, viewType: Int): AbstractViewHolder {
        return when (viewType) {
            DATE_TYPE -> TableCellDateViewHolder(
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
        else -> 0
    }

    private fun getCellViewType(column: Int): Int = when (column) {
        lastDatePosition -> COLOR_TYPE
        else -> 0
    }
}

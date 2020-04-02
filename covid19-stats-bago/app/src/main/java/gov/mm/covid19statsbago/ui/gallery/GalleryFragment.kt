package gov.mm.covid19statsbago.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.adapter.TableAdapter
import gov.mm.covid19statsbago.datas.TableCellVO
import gov.mm.covid19statsbago.datas.columnHeaderList
import gov.mm.covid19statsbago.datas.rowHeaderList
import gov.mm.covid19statsbago.datas.tableCellList
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val tableAdapter: TableAdapter by lazy {
        TableAdapter(requireContext(), 1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        table_view.apply {
            setHasFixedWidth(false)
            adapter = tableAdapter
        }

        val tableCellData = mutableListOf<MutableList<TableCellVO>>()
        (1..15).forEach {
            tableCellData.add(
                tableCellList {
                    tableCell {
                        cellId = "1"
                        data = "ပဲခူး"
                    }
                    tableCell {
                        cellId = "1"
                        data = "0$it-04-2020"
                    }
                    tableCell {
                        cellId = "1"
                        data = "1"
                    }
                    tableCell {
                        cellId = "1"
                        data = "1"
                    }
                }
            )
        }

        tableAdapter.setAllItems(
            columnHeaderList {
                (1..4).forEach {
                    columnHeader {
                        data = when (it) {
                            1 -> "စဥ်"
                            2 -> "နေ့စွဲ"
                            3 -> "ခရိုင်"
                            else -> "မြို့နယ်"
                        }
                    }
                }
            },
            rowHeaderList {
                (1..15).forEach {
                    rowHeader {
                        data = "$it"
                    }
                }
            },
            tableCellData
        )
    }
}

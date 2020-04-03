package gov.mm.covid19statsbago.ui.treatment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.adapter.TableAdapter
import gov.mm.covid19statsbago.datas.*
import gov.mm.covid19statsbago.generals.toUniNumber
import gov.mm.covid19statsbago.jsonparsings.JsonParsingForTreatment
import gov.mm.covid19statsbago.jsonparsings.JsonParsingReturnedPeople
import kotlinx.android.synthetic.main.fragment_treatment.*

class TreatmentFragment :Fragment(R.layout.fragment_treatment)  {

    private val tableAdapter: TableAdapter by lazy {
        TableAdapter(requireContext(), 1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         treatment_table_view.apply {
            setHasFixedWidth(false)
            adapter = tableAdapter
        }

        refreshData()
        treatment_swiperefresh.apply {
            isRefreshing = true
            setOnRefreshListener {
                refreshData()
            }
        }
    }
    private fun tableDataBind(tabledatalist :List<QurantineData>)
    {
        val tableCellData = mutableListOf<MutableList<TableCellVO>>()
        for(index in 0 .. tabledatalist.size-1)
        {
            tableCellData?.add(
                tableCellList {
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).district.toString()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).township.toString()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).date.toString()
                    }

                })
        }


        tableAdapter.setAllItems(
            columnHeaderList {
                (1..16).forEach {
                    columnHeader {
                        data = when (it) {
                            1 -> "ခရိုင် "
                            2 -> "မြို့နယ်"
                            3 -> "ပြည်ပမှဝင်ရောက်လာသည့် ဦးရေ"
                            4 -> "Home Quarantines"
                            5 -> "ဘာသာရေးအဆောက်အဦး"
                            6 -> "ရိပ်သာ / ခန်းမ"
                            7-> "Hotel"
                                8-> "စာသင်ကျောင်း /တက္ကသိုလ်"
                                9-> "အခြားနေရာများ"
                                10 -> "CBFQ စုစုပေါင်းလူဦးရေ"
                                11 -> "ပြည်လည်စေလွှတ်သူဦးရေ"
                                12-> "ဆေးရုံ "
                                    13-> "ပြည်လည်စေလွှတ်သူဦးရေ"
                                14-> "ဆေးရုံ"
                            15->" သေဆုံး"
                            else -> "ပြည်လည်စေလွှတ်သူဦးရေပေါင်း"
                        }
                    }
                }}, rowHeaderList {
                (0 .. tabledatalist.size-1).forEach {
                    rowHeader {
                        data = "$it"
                    }
                }
            },
            tableCellData
        )
    }
    private fun refreshData() {
        treatment_swiperefresh?.isRefreshing = true
        treatment_shimmerlayout.visibility=View.VISIBLE
        treatment_shimmerlayout.startShimmerAnimation()
        treatment_table_view.visibility=View.GONE
        JsonParsingForTreatment().getResponseForReturnedPeople(
            success = { datas->
                treatment_swiperefresh?.isRefreshing = false
                treatment_shimmerlayout.visibility=View.GONE
                treatment_shimmerlayout.stopShimmerAnimation()
                treatment_table_view.visibility=View.VISIBLE
                tableDataBind(datas)

            },
            error = {
                treatment_swiperefresh.isRefreshing = false
            }
        )
    }

    override fun onResume() {
        refreshData()
        super.onResume()
    }
}

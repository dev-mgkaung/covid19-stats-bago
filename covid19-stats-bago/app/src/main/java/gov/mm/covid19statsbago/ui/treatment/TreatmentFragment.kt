package gov.mm.covid19statsbago.ui.treatment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.adapter.TableAdapter
import gov.mm.covid19statsbago.adapter.TreatmentAdapter
import gov.mm.covid19statsbago.datas.*
import gov.mm.covid19statsbago.generals.toUniNumber
import gov.mm.covid19statsbago.jsonparsings.JsonParsingForTreatment
import kotlinx.android.synthetic.main.fragment_treatment.*

class TreatmentFragment : Fragment(R.layout.fragment_treatment) {

    private val tableAdapter: TreatmentAdapter by lazy {
        TreatmentAdapter(requireContext(), 1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        treatment_table_view.apply {
            setHasFixedWidth(false)
            adapter = tableAdapter
        }
        refreshData()
//        treatment_swiperefresh.apply {
//            isRefreshing = true
//            setOnRefreshListener {
//                refreshData()
//            }
//        }
    }

    private fun tableDataBind(tabledatalist: List<QurantineData>) {
        Log.e("da=",tabledatalist.get(0).quarantine.toString())
        val tableCellData = mutableListOf<MutableList<TableCellVO>>()
        for (index in 0..tabledatalist.size - 1) {
            tableCellData?.add(
                tableCellList {
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).date.toString()
                    }
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
                        data = tabledatalist.get(index).quarantine?.returned_count.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.total_release_count.toString().toUniNumber()
                    }


                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.keepCount?.religious_building.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.keepCount?.avenue.toString().toUniNumber()
                    }

                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.keepCount?.hotel.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.keepCount?.schools.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.keepCount?.others.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.keepCount?.total.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.keepCount?.release_count.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.suspicion_count?.hospital.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.suspicion_count?.release_count.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.positive_count?.hospital.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.positive_count?.death.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.positive_count?.release_count.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine?.total_release_count.toString().toUniNumber()
                    }

                })
        }


        tableAdapter.setAllItems(
            columnHeaderList {
                (1..18).forEach {
                    columnHeader {
                        data = when (it) {
                            1 -> "နေ့စွဲ"
                            2 -> "ခရိုင် "
                            3 -> "မြို့နယ်"
                            4 -> "ပြည်ပမှဝင်ရောက်ဦးရေ"
                            5 -> "Home Quarantines"
                            6 -> "ဘာသာရေးအဆောက်အဦး"
                            7 -> "ရိပ်သာ/ခန်းမ"
                            8 -> "Hotel"
                            9 -> "စာသင်ကျောင်း/တက္ကသိုလ်"
                            10 -> "အခြားနေရာများ"
                            11 -> "CBFQစုစုပေါင်းလူဦးရေ"
                            12 -> "ပြည်လည်စေလွှတ်(Quarantine)"
                            13 -> "ဆေးရုံ "
                            14 -> "ပြည်လည်စေလွှတ်(သံသယလူနာ)"
                            15 -> "ဆေးရုံ"
                            16 -> " သေဆုံး"
                            17 -> "ပြည်လည်စေလွှတ်(ရောဂါပိုးတွေ့ရှိသူ)"
                            18 -> "ပြည်လည်စေလွှတ်သူဦးရေပေါင်း"
                            else -> ""
                        }
                    }
                }
            }, rowHeaderList {
                (0..tabledatalist.size - 1).forEach {
                    rowHeader {
                        data = "$it"
                    }
                }
            },
            tableCellData
        )
    }

    private fun refreshData() {
        //treatment_swiperefresh?.isRefreshing = true
        treatment_shimmerlayout.visibility = View.VISIBLE
        treatment_shimmerlayout.startShimmerAnimation()
        treatment_table_view.visibility = View.GONE
        JsonParsingForTreatment().getResponseForReturnedPeople(
            success = { datas ->
           //     treatment_swiperefresh?.isRefreshing = false
                treatment_shimmerlayout.visibility = View.GONE
                treatment_shimmerlayout.stopShimmerAnimation()
                treatment_table_view.visibility = View.VISIBLE
                tableDataBind(datas)

            },
            error = {
                //treatment_swiperefresh.isRefreshing = false
            }
        )
    }

    override fun onResume() {
        refreshData()
        super.onResume()
    }
}

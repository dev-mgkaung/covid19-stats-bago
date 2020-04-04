package gov.mm.covid19statsbago.ui.treatment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.activities.BottomNavigationActivity
import gov.mm.covid19statsbago.adapter.TreatmentAdapter
import gov.mm.covid19statsbago.datas.QurantineData
import gov.mm.covid19statsbago.datas.columnHeaderList
import gov.mm.covid19statsbago.datas.rowHeader
import gov.mm.covid19statsbago.datas.tableCellList
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

        swipe_refresh.apply {
            isRefreshing = true
            setOnRefreshListener {
                refreshData()
            }
        }
    }

    private fun tableDataBind(treatmentDataList: List<QurantineData>) {
        tableAdapter.setAllItems(
            columnHeaderList {
                (1..18).forEach {
                    columnHeader {
                        data = when (it) {
                            1 -> "နေ့စွဲ"
                            2 -> "ခရိုင်"
                            3 -> "မြို့နယ်"
                            4 -> "ပြည်ပမှဝင်ရောက်လာသည့်ဦးရေ"
                            5 -> "Home Quarantines"
                            6 -> "ဘာသာရေးအဆောက်အဦး"
                            7 -> "ရိပ်သာ/ခန်းမ"
                            8 -> "Hotel"
                            9 -> "စာသင်ကျောင်း/တက္ကသိုလ်"
                            10 -> "အခြားနေရာများ"
                            11 -> "CBFQ စုစုပေါင်းလူဦးရေ"
                            12 -> "ပြည်လည်စေလွှတ်သူဦးရေ(Quarantine)"
                            13 -> "ဆေးရုံ"
                            14 -> "ပြည်လည်စေလွှတ်သူဦးရေ(သံသယလူနာ)"
                            15 -> "ဆေးရုံ"
                            16 -> "သေဆုံး"
                            17 -> "ပြည်လည်စေလွှတ်သူဦးရေ(ရောဂါပိုးတွေ့ရှိသူ)"
                            18 -> "ပြည်လည်စေလွှတ်သူဦးရေပေါင်း"
                            else -> ""
                        }
                    }
                }
            },
            treatmentDataList.mapIndexed { index, _ ->
                rowHeader {
                    data = "${index + 1}"
                }
            },
            treatmentDataList.mapIndexed { index, qurantineData ->
                tableCellList {
                    tableCell {
                        cellId = "$index"
                        data = qurantineData?.date
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData?.district
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData?.township
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData?.quarantine?.returned_count.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData?.quarantine?.home_quarantine.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.keepCount.religious_building.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.keepCount.avenue.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.keepCount.hotel.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.keepCount.schools.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.keepCount.others.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.keepCount.total.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.keepCount.release_count.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.suspicion_count.hospital.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.suspicion_count.release_count.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.positive_count.hospital.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.positive_count.death.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.positive_count.release_count.toUniNumber()
                    }
                    tableCell {
                        cellId = "$index"
                        data = qurantineData.quarantine.total_release_count.toUniNumber()
                    }
                }
            }
        )
    }

    private fun refreshData() {
        treatment_shimmerlayout.startShimmerAnimation()
        JsonParsingForTreatment().getResponseForReturnedPeople(
            success = {
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 2) return@getResponseForReturnedPeople
                swipe_refresh.apply {
                    isRefreshing = false
                    isEnabled = false
                }
                treatment_shimmerlayout.visibility = View.GONE
                treatment_shimmerlayout.stopShimmerAnimation()
                treatment_table_view.visibility = View.VISIBLE
                tableDataBind(it)
            },
            error = {
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 2) return@getResponseForReturnedPeople
                swipe_refresh.isRefreshing = false
            }
        )
    }

    override fun onResume() {
        (activity as BottomNavigationActivity).apply {
            currentFragment = 2
        }
        refreshData()
        super.onResume()
    }
}

package gov.mm.covid19statsbago.ui.treatment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.activities.BottomNavigationActivity
import gov.mm.covid19statsbago.adapter.TreatmentAdapter
import gov.mm.covid19statsbago.datas.*
import gov.mm.covid19statsbago.generals.toUniNumber
import gov.mm.covid19statsbago.jsonparsings.JsonParsingForTreatment
import kotlinx.android.synthetic.main.fragment_treatment.*
import java.text.SimpleDateFormat
import java.util.*

class TreatmentFragment : Fragment(R.layout.fragment_treatment) {

    private val tableAdapter: TreatmentAdapter by lazy {
        TreatmentAdapter(requireContext())
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

        datechoose.text = SimpleDateFormat("d/m/yyyy", Locale.ENGLISH).format(Date())

        datechoose.setOnClickListener {
            chooseDatePicker()
        }
        treatmentgetalllist.setOnClickListener {
            datechoose.text = "00/00/0000"
            refreshData()
        }

        empty_view.setOnClickListener {
            chooseDatePicker()
        }

    }

    private fun chooseDatePicker() {
        val mcurrentTime = Calendar.getInstance()
        val year = mcurrentTime.get(Calendar.YEAR)
        val month = mcurrentTime.get(Calendar.MONTH)
        val day = mcurrentTime.get(Calendar.DAY_OF_MONTH)


        val datePicker = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, _, _, dayOfMonth ->
                datechoose.text = String.format("%d/%d/%d", dayOfMonth, month + 1, year)
                FetchDataByDate(String.format("%d/%d/%d", dayOfMonth, month + 1, year))
            }, year, month, day
        )

        datePicker.show()
    }

    private fun tableDataBind(tabledatalist: List<QurantineData>) {
        if (tabledatalist.isEmpty() || tabledatalist.size == 1) {
            treatment_table_view.visibility = View.GONE
            empty_view.apply {
                setMessage(getString(R.string.lbl_no_data_not_found, datechoose.text.toString()))
                visibility = View.VISIBLE
            }
            return
        }
        empty_view.visibility = View.GONE
        treatment_table_view.visibility = View.VISIBLE
        val tableCellData = mutableListOf<MutableList<TableCellVO>>()
        for (index in 1 until tabledatalist.size) {
            tableCellData.add(
                tableCellList {
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].date
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].district
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].township
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].q_r_count.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].q_h_quarantine.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data =
                            tabledatalist[index].q_religious_building.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].quarantine_avenue.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].quarantine_hotel.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].quarantine_schools.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].quarantine_others.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].quarantine_total.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].q_release_count.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].s_count_hospital.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data =
                            tabledatalist[index].s_count_release_count.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].p_count_hospital
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].p_count_death
                    }
                    tableCell {
                        cellId = index.toString()
                        data =
                            tabledatalist[index].p_count_release_count.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist[index].total_release_count.toUniNumber()
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
                            6 -> "ဘာသာရေးအဆောက်အဦး(Quarantines)"
                            7 -> "ရိပ်သာ/ခန်းမ(Quarantines)"
                            8 -> "Hotel(Quarantines)"
                            9 -> "စာသင်ကျောင်း/တက္ကသိုလ်(Quarantines)"
                            10 -> "အခြားနေရာများ(Quarantines)"
                            11 -> "CBFQစုစုပေါင်းလူဦးရေ"
                            12 -> "ပြန်လည်စေလွှတ်(Quarantine)"
                            13 -> "ဆေးရုံ(သံသယလူနာ)"
                            14 -> "ပြန်လည်စေလွှတ်(သံသယလူနာ)"
                            15 -> "ဆေးရုံ(ရောဂါပိုးတွေ့ရှိသူ)"
                            16 -> " သေဆုံး(ရောဂါပိုးတွေ့ရှိသူ)"
                            17 -> "ပြန်လည်စေလွှတ်(ရောဂါပိုးတွေ့ရှိသူ)"
                            18 -> "ပြန်လည်စေလွှတ်သူဦးရေပေါင်း"
                            else -> ""
                        }
                    }
                }
            }, rowHeaderList {
                (1 until tabledatalist.size).forEach {
                    rowHeader {
                        data = "$it"
                    }
                }
            },
            tableCellData
        )
    }

    private fun dataShowHide(control: Boolean) {
        if (control) {

            treatment_shimmerlayout.stopShimmerAnimation()
            treatment_shimmerlayout.visibility = View.GONE
            treatmentlayout.visibility = View.VISIBLE
        } else {
            treatment_shimmerlayout.startShimmerAnimation()
            treatment_shimmerlayout.visibility = View.VISIBLE
            treatmentlayout.visibility = View.GONE
        }
    }

    private fun refreshData() {
        dataShowHide(false)
        JsonParsingForTreatment().getResponseForReturnedPeople(
            success = {
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 2) return@getResponseForReturnedPeople
                swipe_refresh.apply {
                    isRefreshing = false
                    isEnabled = false
                }
                dataShowHide(true)
                tableDataBind(it)
            },
            error = {
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 2) return@getResponseForReturnedPeople
                swipe_refresh.isRefreshing = false
            }
        )
    }

    private fun FetchDataByDate(sdate: String) {
        dataShowHide(false)
        JsonParsingForTreatment().getResponseForReturnedPeopleByDate(
            querydate = sdate,
            success = {
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 2) return@getResponseForReturnedPeopleByDate
                swipe_refresh.apply {
                    isRefreshing = false
                    isEnabled = false
                }
                dataShowHide(true)
                tableDataBind(it)
            },
            error = {
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 2) return@getResponseForReturnedPeopleByDate
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

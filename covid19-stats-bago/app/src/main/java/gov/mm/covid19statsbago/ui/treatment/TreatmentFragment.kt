package gov.mm.covid19statsbago.ui.treatment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.activities.BottomNavigationActivity
import gov.mm.covid19statsbago.adapter.TreatmentAdapter
import gov.mm.covid19statsbago.datas.*
import gov.mm.covid19statsbago.generals.toUniNumber
import gov.mm.covid19statsbago.jsonparsings.JsonParsingForTreatment
import kotlinx.android.synthetic.main.fragment_treatment.*
import kotlinx.android.synthetic.main.fragment_treatment.datechoose
import java.util.*

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

        datechoose.setOnClickListener{
            chooseDatePicker()
        }
        treatmentgetalllist.setOnClickListener{
            datechoose.text="00/00/0000"
            refreshData()
        }

    }
    private fun chooseDatePicker()
    {
        val mcurrentTime = Calendar.getInstance()
        val year = mcurrentTime.get(Calendar.YEAR)
        val month = mcurrentTime.get(Calendar.MONTH)
        val day = mcurrentTime.get(Calendar.DAY_OF_MONTH)


        var datePicker = DatePickerDialog(this!!.activity!!, object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                datechoose.setText(String.format("%d/%d/%d", dayOfMonth, month + 1, year))
                FetchDataByDate(String.format("%d/%d/%d", dayOfMonth, month + 1, year))
            }
        }, year, month, day);

        datePicker.show()
    }

    private fun tableDataBind(tabledatalist: List<QurantineData>) {
        val tableCellData = mutableListOf<MutableList<TableCellVO>>()
        for (index in 1..tabledatalist.size - 1) {
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
                        data = tabledatalist.get(index).q_r_count.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).q_h_quarantine.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).q_religious_building.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine_avenue.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine_hotel.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine_schools.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine_others.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).quarantine_total.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).q_release_count.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).s_count_hospital.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).s_count_release_count.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).p_count_hospital.toString()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).p_count_death.toString()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).p_count_release_count.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).total_release_count.toString().toUniNumber()
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
                (1..tabledatalist.size - 1).forEach {
                    rowHeader {
                        data = "$it"
                    }
                }
            },
            tableCellData
        )
    }

    private fun dataShowHide(control :Boolean)
    {
        if(control) {

            treatment_shimmerlayout.stopShimmerAnimation()
            treatment_shimmerlayout.visibility = View.GONE
            treatmentlayout.visibility = View.VISIBLE
        }else
        {
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
    private fun FetchDataByDate(sdate:String) {
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

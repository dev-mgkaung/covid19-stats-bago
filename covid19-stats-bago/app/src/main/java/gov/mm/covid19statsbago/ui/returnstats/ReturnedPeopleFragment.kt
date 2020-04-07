package gov.mm.covid19statsbago.ui.returnstats

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.activities.BottomNavigationActivity
import gov.mm.covid19statsbago.adapter.ReturnPeopleAdapter
import gov.mm.covid19statsbago.datas.*
import gov.mm.covid19statsbago.generals.getUpdatedDate
import gov.mm.covid19statsbago.generals.toUniNumber
import gov.mm.covid19statsbago.jsonparsings.JsonParsingReturnedPeople
import kotlinx.android.synthetic.main.fragment_return.*
import java.text.SimpleDateFormat
import java.util.*


class ReturnedPeopleFragment : Fragment(R.layout.fragment_return) {

    private val tableAdapter: ReturnPeopleAdapter by lazy {
        ReturnPeopleAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        returnpeople_table_view.apply {
            setHasFixedWidth(false)
            adapter = tableAdapter
        }

        return_swiperefresh.apply {
            isRefreshing = true
            setOnRefreshListener {
                refreshData()
            }
        }

        returndatechoose.text = getUpdatedDate()

        returngetalllist.setOnClickListener {
            returndatechoose.text = "00/00/0000"
            refreshData()
        }
        returndatechoose.setOnClickListener {
            chooseDatePicker()
        }

        empty_view.setOnClickListener {
            chooseDatePicker()
        }
    }

    private fun dataShowHide(control: Boolean) {
        empty_view.visibility = View.GONE
        if (control) {
            return_shimmerlayout.stopShimmerAnimation()
            return_shimmerlayout.visibility = View.GONE
            returnpeoplelayout.visibility = View.VISIBLE
        } else {
            return_shimmerlayout.startShimmerAnimation()
            return_shimmerlayout.visibility = View.VISIBLE
            returnpeoplelayout.visibility = View.GONE
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
                returndatechoose.setText(String.format("%d/%d/%d", dayOfMonth, month + 1, year))
                FetchDataByDate(String.format("%d/%d/%d", dayOfMonth, month + 1, year))
            }, year, month, day
        );

        datePicker.show()
    }

    private fun tableDataBind(tabledatalist: List<ReturnedPeople>) {
        if (tabledatalist.isEmpty() || tabledatalist.size == 1) {
            returnpeople_table_view.visibility = View.GONE
            empty_view.apply {
                setMessage(
                    getString(
                        R.string.lbl_no_data_not_found,
                        returndatechoose.text.toString()
                    )
                )
                visibility = View.VISIBLE
            }
            return
        }
        returnpeople_table_view.visibility = View.VISIBLE
        val tableCellData = mutableListOf<MutableList<TableCellVO>>()
        for (index in 1 until tabledatalist.size) {
            tableCellData.add(
                tableCellList {
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).date
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).district
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).township
                    }

                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).china.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).laro.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).thai.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).american.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).malaysia.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).singapore.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).japan.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).koera.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).india.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).russia.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).england.toUniNumber()
                    }

                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).myawaddy.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).other.toUniNumber()
                    }

                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).total.toUniNumber()
                    }
                    ///
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).suspicion
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).remark
                    }
                })
        }



        tableAdapter.setAllItems(
            columnHeaderList {
                (1..19).forEach {
                    columnHeader {
                        data = when (it) {
                            1 -> "နေ့စွဲ"
                            2 -> "ခရိုင် "
                            3 -> "မြို့နယ်"
                            4 -> "တရုတ်"
                            5 -> "လာအို"
                            6 -> "ထိုင်း"
                            7 -> "အမေရိကန်"
                            8 -> "မလေးရှား"
                            9 -> "စင်္ကာပူ"
                            10 -> "ဂျပန်"
                            11 -> "ကိုရီးယား"
                            12 -> "အိန္ဒိယ"
                            13 -> "ရုရှား"
                            14 -> "အင်္ဂလန်"
                            15 -> "မြဝတီ"
                            16 -> "အခြားနိုင်ငံ/အခြားမြို့"
                            17 -> "စုစုပေါင်း"
                            18 -> "သံသယလူနာဦးရေ"
                            19 -> "မှတ်ချက်"
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

    private fun refreshData() {
        dataShowHide(false)
        JsonParsingReturnedPeople().getResponseForReturnedPeople(
            success = { data ->
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 1) return@getResponseForReturnedPeople
                return_swiperefresh.apply {
                    isRefreshing = false
                    isEnabled = false
                }
                dataShowHide(true)
                tableDataBind(data)

            },
            error = {
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 1) return@getResponseForReturnedPeople
                return_swiperefresh.isRefreshing = false
            }
        )
    }

    private fun FetchDataByDate(sdate: String) {
        dataShowHide(false)
        JsonParsingReturnedPeople().getResponseForReturnedPeopleByDate(
            querydate = sdate,
            success = { data ->
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 1) return@getResponseForReturnedPeopleByDate
                return_swiperefresh.apply {
                    isRefreshing = false
                    isEnabled = false
                }
                dataShowHide(true)
                tableDataBind(data)

            },
            error = {
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 1) return@getResponseForReturnedPeopleByDate
                return_swiperefresh.isRefreshing = false
            })
    }

    override fun onResume() {
        (activity as BottomNavigationActivity).apply {
            currentFragment = 1
        }
        refreshData()
        super.onResume()
    }
}

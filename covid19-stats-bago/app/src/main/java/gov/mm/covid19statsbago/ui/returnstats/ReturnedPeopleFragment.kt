package gov.mm.covid19statsbago.ui.returnstats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.activities.BottomNavigationActivity
import gov.mm.covid19statsbago.adapter.TableAdapter
import gov.mm.covid19statsbago.datas.*
import gov.mm.covid19statsbago.generals.toUniNumber
import gov.mm.covid19statsbago.jsonparsings.JsonParsingReturnedPeople
import kotlinx.android.synthetic.main.fragment_return.*


class ReturnedPeopleFragment : Fragment(R.layout.fragment_return) {

    private val tableAdapter: TableAdapter by lazy {
        TableAdapter(requireContext(), 1)
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
    }

    private fun tableDataBind(tabledatalist: List<ReturnedPeople>) {
        val tableCellData = mutableListOf<MutableList<TableCellVO>>()
        for(index in 1 .. tabledatalist.size-1)
        {
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
                        data = tabledatalist.get(index).china.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).laro.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).thai.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).american.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).malaysia.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).singapore.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).japan.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).koera.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).india.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).russia.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).england.toString().toUniNumber()
                    }

                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).myawaddy.toString().toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).other.toString().toUniNumber()
                    }

                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).total.toString().toUniNumber()
                    }
                    ///
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).suspicion.toString()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).remark.toString()
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
                            3 ->  "မြို့နယ်"
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
        return_shimmerlayout.visibility = View.VISIBLE
        return_shimmerlayout.startShimmerAnimation()
        returnpeople_table_view.visibility = View.GONE
        JsonParsingReturnedPeople().getResponseForReturnedPeople(
            success = { data ->
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 1) return@getResponseForReturnedPeople
                return_swiperefresh.apply {
                    isRefreshing = false
                    isEnabled = false
                }
                return_shimmerlayout.visibility = View.GONE
                return_shimmerlayout.stopShimmerAnimation()
                returnpeople_table_view.visibility = View.VISIBLE
                tableDataBind(data)

            },
            error = {
                if (activity == null || (activity as BottomNavigationActivity).currentFragment != 1) return@getResponseForReturnedPeople
                return_swiperefresh.isRefreshing = false
            }
        )
    }

    override fun onResume() {
        (activity as BottomNavigationActivity).apply {
            currentFragment = 1
        }
        refreshData()
        super.onResume()
    }
}

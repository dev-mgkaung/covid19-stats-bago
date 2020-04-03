package gov.mm.covid19statsbago.ui.returnstats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
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

        refreshData()
        return_swiperefresh.apply {
            isRefreshing = true
            setOnRefreshListener {
                refreshData()
            }
        }
    }
    private fun tableDataBind(tabledatalist :List<ReturnedPeople>)
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
//                    //for loop country
//                    for(i in 0 ..(tabledatalist.get(index).returned?.byCountry?.size?.minus(1)
//                        ?: 1)) {
//                        tableCell {
//                            cellId = index.toString()
//                            data = tabledatalist.get(index).returned?.byCountry?.get(i)?.country.toString().toUniNumber()
//                        }
//                        tableCell {
//                            cellId = index.toString()
//                            data = tabledatalist.get(index).returned?.byCountry?.get(i)?.total.toString().toUniNumber()
//                        }
//                    }

                    tableCell {
                        cellId = index.toString()
                        data = tabledatalist.get(index).returned?.total.toString().toUniNumber()
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
                            1 -> "ခရိုင် "
                            2 -> "မြို့နယ်"
                            3 -> "နေ့စွဲ"
                            4 -> "တရုတ်"
                            5 -> "လာအို"
                            6 -> "ထိုင်း"
                            7 -> "အမေရိကန်"
                            8 -> "မလေး"
                            9 -> "စက်ာပူ"
                            10 -> "ဂျပန်"
                            11 -> "ကိုရီးယား"
                            12 -> "အိန္ဒိယ"
                            13 -> "ရှရှား"
                            14 -> "အဂ်လန်"
                            15 -> "မြဝတီ"
                            16 -> "အခြား"
                            17 -> "စုစုပေါင်း"
                            18 -> "သံသယရှိသူ"
                            else -> "မှတ်ချက်"
                        }
                    }
                }},rowHeaderList {
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
        return_swiperefresh?.isRefreshing = true
        return_shimmerlayout.visibility=View.VISIBLE
        return_shimmerlayout.startShimmerAnimation()
        returnpeople_table_view.visibility=View.GONE
        JsonParsingReturnedPeople().getResponseForReturnedPeople(
            success = { data->
                return_swiperefresh?.isRefreshing = false
                return_shimmerlayout.visibility=View.GONE
                return_shimmerlayout.stopShimmerAnimation()
                returnpeople_table_view.visibility=View.VISIBLE
                tableDataBind(data)

            },
            error = {
                return_swiperefresh.isRefreshing = false
            }
        )
    }

    override fun onResume() {
        refreshData()
        super.onResume()
    }
}

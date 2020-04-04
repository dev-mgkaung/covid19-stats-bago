package gov.mm.covid19statsbago.ui.returnstats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.activities.BottomNavigationActivity
import gov.mm.covid19statsbago.adapter.TableAdapter
import gov.mm.covid19statsbago.datas.ReturnedPeople
import gov.mm.covid19statsbago.datas.columnHeader
import gov.mm.covid19statsbago.datas.rowHeader
import gov.mm.covid19statsbago.datas.tableCellList
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

    private fun tableDataBind(bagoData: List<ReturnedPeople>) {
        tableAdapter.setAllItems(
            bagoData.first().returned.byCountry.map {
                columnHeader {
                    data = it.country
                }
            }.toMutableList().apply {
                add(0, columnHeader {
                    data = "နေ့စွဲ"
                })
                add(1, columnHeader {
                    data = "ခရိုင်"
                })
                add(2, columnHeader {
                    data = "မြို့နယ်"
                })
                add(columnHeader {
                    data = "စုစုပေါင်း"
                })
                add(columnHeader {
                    data = "သံသယလူနာဦးရေ"
                })
                add(columnHeader {
                    data = "မှတ်ချက်"
                })
            },
            bagoData.mapIndexed { index, _ ->
                rowHeader {
                    data = "${index + 1}"
                }
            },
            bagoData.mapIndexed { index, returnedPeople ->
                tableCellList {
                    tableCell {
                        cellId = index.toString()
                        data = returnedPeople.date
                    }
                    tableCell {
                        cellId = index.toString()
                        data = returnedPeople.district
                    }
                    tableCell {
                        cellId = index.toString()
                        data = returnedPeople.township
                    }
                    returnedPeople.returned.byCountry.forEach {
                        tableCell {
                            cellId = index.toString()
                            data = it.total.toUniNumber()
                        }
                    }
                    tableCell {
                        cellId = index.toString()
                        data = returnedPeople.returned.total.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = returnedPeople.suspicion.toUniNumber()
                    }
                    tableCell {
                        cellId = index.toString()
                        data = returnedPeople.remark.toUniNumber()
                    }
                }
            }
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

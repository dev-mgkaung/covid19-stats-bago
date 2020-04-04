package gov.mm.covid19statsbago.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.adapter.TableAdapter
import gov.mm.covid19statsbago.datas.*
import gov.mm.covid19statsbago.generals.toMMDate
import gov.mm.covid19statsbago.generals.toUniNumber
import gov.mm.covid19statsbago.jsonparsings.JsonParsingDashboardList
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.table_view

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val tableAdapter: TableAdapter by lazy {
        TableAdapter(requireContext(), 1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        table_view.apply {
            setHasFixedWidth(false)
            adapter = tableAdapter
        }
        refreshData()
        swipe_refresh?.apply {
            isRefreshing = true
            setOnRefreshListener {
                refreshData()
            }
        }

    }
 private fun tableDataBind(tabledatalist :List<CovidCountry>)
 {
     val tableCellData = mutableListOf<MutableList<TableCellVO>>()
     for(index in 1 .. tabledatalist.size-2)
     {
         tableCellData?.add(
             tableCellList {
                 tableCell {
                     cellId = index.toString()
                     data = tabledatalist.get(index).country.toString()
                 }
                 tableCell {
                     cellId = index.toString()
                     data = tabledatalist.get(index).totalConfirmed.toString().toUniNumber()
                 }
                 tableCell {
                     cellId = index.toString()
                     data = tabledatalist.get(index).totalDeaths.toString().toUniNumber()
                 }
                 tableCell {
                     cellId = index.toString()
                     data = tabledatalist.get(index).totalRecovered.toString().toUniNumber()
                 }
             })
     }


     tableAdapter.setAllItems(
         columnHeaderList {
             (1..4).forEach {
                 columnHeader {
                     data = when (it) {
                         1 -> "နိုင်ငံများ "
                         2 -> "ပိုးတွေ့ရှိသူ"
                         3 -> "သေဆုံးသူ"
                         else -> "ပြန်ကောင်းသူ"
                     }
                 }
             }
         },
         rowHeaderList {
             (1 .. tabledatalist.size-2).forEach {
                 rowHeader {
                     data = "$it"
                 }
             }
         },
         tableCellData
     )
 }
    private fun refreshData() {
        shimmerlayout?.visibility=View.VISIBLE
        shimmerlayout?.startShimmerAnimation()
        allcountrylist?.visibility=View.GONE
        JsonParsingDashboardList().getResponseForDashboard(
            success = { data, date ->
                swipe_refresh?.isRefreshing = false
                tv_global_confirm_count?.text = data.sumBy { it.totalConfirmed }.toUniNumber()
                tv_global_death_count?.text = data.sumBy { it.totalDeaths }.toUniNumber()
                tv_global_recover_count?.text = data.sumBy { it.totalRecovered }.toUniNumber()


                tv_today_date?.text = date.toMMDate()

                if (data.any { it?.country == "Burma" }) {
                    with(data.first { it?.country == "Burma" }) {
                        tv_mm_confirm_count?.text = totalConfirmed.toUniNumber()
                        tv_mm_death_count?.text = totalDeaths.toUniNumber()
                        tv_mm_recover_count?.text = totalRecovered.toUniNumber()
                    }
                }
                countrylistcardview?.visibility=View.VISIBLE
                shimmerlayout?.visibility=View.GONE
                shimmerlayout?.stopShimmerAnimation()
                allcountrylist?.visibility=View.VISIBLE
                tableDataBind(data)

            },
            error = {
                swipe_refresh?.isRefreshing = false
            }
        )
    }

    override fun onResume() {
        refreshData()
        super.onResume()
    }
}

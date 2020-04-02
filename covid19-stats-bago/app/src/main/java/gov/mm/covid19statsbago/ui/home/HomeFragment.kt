package gov.mm.covid19statsbago.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R
import gov.mm.covid19statsbago.generals.toUniNumber
import gov.mm.covid19statsbago.jsonparsings.JsonParsingDashboardList
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_refresh.apply {
            isRefreshing = true
            setOnRefreshListener {
                refreshData()
            }
        }

    }

    private fun refreshData() {
        JsonParsingDashboardList().getResponseForDashboard(
            success = { data, date ->
                swipe_refresh.isRefreshing = false
                tv_global_confirm_count.text = data.sumBy { it.totalConfirmed }.toUniNumber()
                tv_global_death_count.text = data.sumBy { it.totalDeaths }.toUniNumber()
                tv_global_recover_count.text = data.sumBy { it.totalRecovered }.toUniNumber()

                tv_today_date.text = date
                if (data.any { it.country == "Burma" }) {
                    with(data.first { it.country == "Burma" }) {
                        tv_mm_confirm_count.text = totalConfirmed.toUniNumber()
                        tv_mm_death_count.text = totalDeaths.toUniNumber()
                        tv_mm_recover_count.text = totalRecovered.toUniNumber()
                    }
                }
            },
            error = {
                swipe_refresh.isRefreshing = false
            }
        )
    }

    override fun onResume() {
        refreshData()
        super.onResume()
    }
}

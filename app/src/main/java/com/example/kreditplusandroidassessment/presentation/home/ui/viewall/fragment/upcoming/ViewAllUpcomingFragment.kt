package com.example.kreditplusandroidassessment.presentation.home.ui.viewall.fragment.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kreditplusandroidassessment.databinding.FragmentViewAllUpcomingBinding
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.adapter.upcoming.ViewAllUpcomingAdapter
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.event.ViewAllEvent
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.viewmodel.upcoming.ViewAllUpcomingViewModel
import com.example.kreditplusandroidassessment.util.GridMarginItemDecoration
import com.example.kreditplusandroidassessment.util.isLastVisible
import com.example.kreditplusandroidassessment.util.removeView
import com.example.kreditplusandroidassessment.util.showView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ViewAllUpcomingFragment : Fragment() {

    private var _binding: FragmentViewAllUpcomingBinding? = null

    private val binding get() = _binding!!

    private var viewAllUpcomingAdapter: ViewAllUpcomingAdapter? = null

    private val viewmodel: ViewAllUpcomingViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewAllUpcomingBinding.inflate(inflater, container, false)
        viewAllUpcomingAdapter = ViewAllUpcomingAdapter.instance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initView()
        initPagination()
        initLaunch()
    }

    private fun initPagination() {
        binding.rvAll.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastVisible(recyclerView)) {
                    viewmodel.onEvent(ViewAllEvent.LoadMore)
                }
            }
        })
    }

    private fun initLaunch() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewmodel.state.collectLatest { result ->
                        if (result.isLoading) {
                            binding.pbLaoding.showView()
                        } else {
                            binding.pbLaoding.removeView()
                        }

                        if (result.upcoming.isNotEmpty()) {
                            viewAllUpcomingAdapter?.differ?.submitList(result.upcoming)
                        }
                    }
                }
            }
        }
    }

    private fun initView() {
        viewmodel.onEvent(
            event = ViewAllEvent.DefaultPage
        )
    }

    private fun initAdapter() {
        viewAllUpcomingAdapter?.let { adapter ->
            binding.rvAll.apply {
                this.adapter = adapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                addItemDecoration(GridMarginItemDecoration(8))
                ViewCompat.setNestedScrollingEnabled(this, true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewAllUpcomingAdapter = null
    }
}
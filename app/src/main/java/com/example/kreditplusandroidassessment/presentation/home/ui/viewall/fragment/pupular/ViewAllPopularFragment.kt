package com.example.kreditplusandroidassessment.presentation.home.ui.viewall.fragment.pupular

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
import com.example.kreditplusandroidassessment.databinding.FragmentViewAllPopularBinding
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.adapter.popular.ViewAllPopularAdapter
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.event.ViewAllEvent
import com.example.kreditplusandroidassessment.util.GridMarginItemDecoration
import com.example.kreditplusandroidassessment.util.isLastVisible
import com.example.kreditplusandroidassessment.util.removeView
import com.example.kreditplusandroidassessment.util.showView
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.viewmodel.popular.ViewAllPopularViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ViewAllPopularFragment : Fragment() {

    private var _binding: FragmentViewAllPopularBinding? = null

    private val binding get() = _binding as FragmentViewAllPopularBinding

    private var viewAllPopularAdapter: ViewAllPopularAdapter? = null

    private val viewModel: ViewAllPopularViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewAllPopularBinding.inflate(inflater, container, false)
        viewAllPopularAdapter = ViewAllPopularAdapter.instance()
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
                    viewModel.onEvent(ViewAllEvent.LoadMore)
                }
            }
        })
    }

    private fun initLaunch() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collectLatest { result ->
                        if (result.isLoading) {
                            binding.pbLaoding.showView()
                        } else {
                            binding.pbLaoding.removeView()
                        }
                        if (result.popular.isNotEmpty()) {
                            viewAllPopularAdapter?.differ?.submitList(result.popular)
                        }
                    }
                }
            }
        }
    }

    private fun initView() {
        viewModel.onEvent(
            event = ViewAllEvent.DefaultPage
        )
    }

    private fun initAdapter() {
        viewAllPopularAdapter?.let { adapter ->
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
        viewAllPopularAdapter = null
    }
}
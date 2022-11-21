package com.example.kreditplusandroidassessment.presentation.home.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kreditplusandroidassessment.R
import com.example.kreditplusandroidassessment.databinding.FragmentHomeBinding
import com.example.kreditplusandroidassessment.domain.adapter.nowplaying.NowPlayingAdapter
import com.example.kreditplusandroidassessment.domain.adapter.popular.PopularAdapter
import com.example.kreditplusandroidassessment.domain.adapter.upcoming.UpcomingAdapter
import com.example.kreditplusandroidassessment.presentation.home.ui.home.viewmodel.HomeViewModel
import com.example.kreditplusandroidassessment.util.MESSAGE.STATUS_ERROR
import com.example.kreditplusandroidassessment.util.MarginItemDecorationHorizontal
import com.example.kreditplusandroidassessment.util.removeView
import com.example.kreditplusandroidassessment.util.showView
import com.example.kreditplusandroidassessment.util.snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

   private var _binding: FragmentHomeBinding? = null

   private val binding get() = _binding as FragmentHomeBinding

   private lateinit var nowPlayingAdapter: NowPlayingAdapter

   private lateinit var upcomingAdapter: UpcomingAdapter

   private lateinit var popularAdapter: PopularAdapter

   private val homeViewModel: HomeViewModel by inject()

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentHomeBinding.inflate(inflater, container, false)
      nowPlayingAdapter = NowPlayingAdapter.instance()
      upcomingAdapter = UpcomingAdapter.instance()
      popularAdapter = PopularAdapter.instance()
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      initAdapter()
      initLaunch()
   }

   private fun initLaunch() {
      lifecycleScope.launchWhenStarted {
         lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
               homeViewModel.state.collectLatest { state ->
                  if (state.isLoading) {
                     binding.pbLaoding.showView()
                  } else {
                     binding.pbLaoding.removeView()
                  }
                  nowPlayingAdapter.differ.submitList(state.nowPlaying)

                  upcomingAdapter.differ.submitList(state.upcoming)

                  popularAdapter.differ.submitList(state.popular)
               }
            }
         }
      }
   }

   private fun initAdapter() {
      nowPlayingAdapter.let { adapter ->
         binding.rvNowPlaying?.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(MarginItemDecorationHorizontal(requireContext(), R.dimen.fragment_horizontal_8_margin))
            ViewCompat.setNestedScrollingEnabled(this, true)
         }

         adapter.setOnItemClickListener { id ->
            if (id <= 0) {
               snackbar(binding.root, "Id Movie tidak ditemukan", STATUS_ERROR)
               return@setOnItemClickListener
            }
//            startActivity(Intent(requireContext(), DetailActivity::class.java).putExtra(ID, id))
         }
      }

      upcomingAdapter.let { adapter ->
         binding.rvUpcoming.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(MarginItemDecorationHorizontal(requireContext(), R.dimen.fragment_horizontal_8_margin))
            ViewCompat.setNestedScrollingEnabled(this, true)
         }

         adapter.setOnItemClickListener { id ->
            if (id <= 0) {
               snackbar(binding.root, "Id Movie tidak ditemukan", STATUS_ERROR)
               return@setOnItemClickListener
            }
//            startActivity(Intent(requireContext(), DetailActivity::class.java).putExtra(ID, id))
         }
      }

      popularAdapter.let { adapter ->
         binding.rvPopular.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(MarginItemDecorationHorizontal(requireContext(), R.dimen.fragment_horizontal_8_margin))
            ViewCompat.setNestedScrollingEnabled(this, true)
         }

         adapter.setOnItemClickListener { id ->
            if (id <= 0) {
               snackbar(binding.root, "Id Movie tidak ditemukan", STATUS_ERROR)
               return@setOnItemClickListener
            }
//            startActivity(Intent(requireContext(), DetailActivity::class.java).putExtra(ID, id))
         }
      }
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }
}
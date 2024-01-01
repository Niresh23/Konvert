package com.niresh23.konvert.ui.rate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.niresh23.konvert.databinding.FragmentFavoriteBinding
import com.niresh23.konvert.model.Rate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FavoriteFragment : Fragment() {


    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()

    private val favoriteClicked = fun (rate: Rate) {
        viewModel.onRateFavoriteClicked(rate)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        viewModel.onCreateView()
        observe()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ratesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.ratesRv.adapter = RateAdapter(emptyList(), favoriteClicked)
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.ratesListFlow.collectLatest { rateList ->
                        binding.ratesRv.swapAdapter(RateAdapter(rateList, favoriteClicked), false)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
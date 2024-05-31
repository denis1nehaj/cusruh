package com.example.cuswork.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.cuswork.R
import com.example.cuswork.databinding.FragmentHomeBinding
import org.w3c.dom.Text

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.titleTv.alpha=0f
        binding.image1.alpha=0f
        binding.image2.alpha=0f
        binding.image3.alpha=0f
        binding.container1.alpha=0f
        binding.container2.alpha=0f
        binding.container3.alpha=0f
        binding.text1.alpha=0f
        binding.text2.alpha=0f
        binding.text3.alpha=0f
        binding.titleTv.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700
        binding.image1.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700
        binding.image2.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700
        binding.image3.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700
        binding.container1.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700
        binding.container2.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700
        binding.container3.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700
        binding.text1.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700
        binding.text2.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700
        binding.text3.animate().alpha(1f).translationYBy((0).toFloat()).setStartDelay(300).duration=700

        binding.container1.setOnClickListener {
            root.findNavController().navigate(R.id.action_home_to_photos)

        }


        binding.container2.setOnClickListener {
            root.findNavController().navigate (R.id.action_home_to_photos_2)

        }

        binding.container3.setOnClickListener {
            root.findNavController().navigate (R.id.action_home_to_photos_3)

        }

        return root

    }

    private fun FragmentNavigatorExtras(sharedElements: Pair<Text, String>) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
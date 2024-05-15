package com.example.neobischallengeandroidapp.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.neobischallengeandroidapp.R
import com.example.neobischallengeandroidapp.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //        requireActivity().window.statusBarColor = resources.getColor(R.color.green_info)
        val window: Window = requireActivity().window
        val background = ContextCompat.getDrawable(requireActivity(), R.drawable.gradient_theme)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor =
            ContextCompat.getColor(requireActivity(), android.R.color.transparent)

        window.setBackgroundDrawable(background)
    }
}
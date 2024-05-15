package com.example.neobischallengeandroidapp.ui.bottomDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.neobischallengeandroidapp.databinding.FragmentBottomDialogBinding
import com.example.neobischallengeandroidapp.ui.detail.DetailVIewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.robinhood.ticker.TickerUtils
import java.text.NumberFormat

class BottomDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomDialogBinding? = null
    val binding get() = _binding!!
    lateinit var viewModel: DetailVIewModel

    private val args: BottomDialogFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailVIewModel::class.java)
        // Set up behavior
        val bottomSheet =
            dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        val behavior = BottomSheetBehavior.from(bottomSheet!!)
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        val layoutParams = bottomSheet?.layoutParams
        val windowHeight = resources.displayMetrics.heightPixels
        layoutParams?.height = windowHeight
        bottomSheet?.layoutParams = layoutParams
        behavior.peekHeight = windowHeight / 2

        with(binding) {
            ivPlus.setOnClickListener {
                viewModel.incrementCount()

            }
            btnMinus.setOnClickListener {
                viewModel.decrementCount()
            }
            tvDesc.text = args.product!!.description
            tvName.text = args.product!!.title
            ivProduct.load(args.product!!.image)
        }


        viewModel.count.observe(viewLifecycleOwner) { count ->
            binding.tvCount.text = count.toString()
            var totalPrice = args.product!!.price!! * count
            binding.tvMetricLabel.text =  NumberFormat.getInstance().format(totalPrice) + " c"
        }
        //ticker
        binding.tvMetricLabel.setCharacterLists(TickerUtils.provideNumberList())
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
}
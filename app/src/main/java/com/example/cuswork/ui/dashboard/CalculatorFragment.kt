package com.example.cuswork.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context
import android.util.Log
import android.widget.PopupMenu
import com.example.cuswork.MainActivity
import com.example.cuswork.ManagerActivity
import com.example.cuswork.R
import com.example.cuswork.databinding.FragmentCalculatorBinding
import com.example.cuswork.domain.Fitting
import com.example.cuswork.domain.Order
import com.example.cuswork.domain.Furniture
import com.example.cuswork.domain.Material

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null

    companion object {
        const val FurnitureType = "FurnitureType"
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

        ):
            View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val popUpBtn = binding.PopupBtn

        val preferences = activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val furnitureType = preferences?.getString(FurnitureType, "")
        popUpBtn.text = furnitureType
        with(preferences?.edit()) {
            this!!.putString("FurnitureType", "")
            apply()
        }

        var selectedFurnitureTitle = ""
        val materialList = listOf(Material("Дерево", 70), Material("ДСП", 0), Material("МДФ", 40))
        val fittingList = listOf(Fitting("Blum", 50), Fitting("Aks", 0), Fitting("GTV", 20))
        val furnitureList = listOf(
            Furniture("Тумба", 200.00, 2),
            Furniture("Шкаф", 600.00, 6),
            Furniture("Стол с Тумбой", 400.00, 4)
        )
        val furniture = furnitureList.find { it.title == furnitureType }
        if (furniture != null) {
            popUpBtn.text = furniture.title
            selectedFurnitureTitle = furniture.title
        } else {
            popUpBtn.text = "Выберите тип Изделия"

        }


        popUpBtn.setOnClickListener { view ->
            val popupUpMenu = PopupMenu(requireContext(), view)
            popupUpMenu.inflate(R.menu.popup_menu)
            popupUpMenu.setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId) {

                    R.id.furniture1 -> {
                        selectedFurnitureTitle = "Тумба"
                        popUpBtn.text = selectedFurnitureTitle
                        true
                    }
                    R.id.furniture2 -> {
                        selectedFurnitureTitle = "Шкаф"
                        popUpBtn.text = selectedFurnitureTitle
                        true
                    }
                    R.id.furniture3 -> {
                        selectedFurnitureTitle = "Стол с Тумбой"
                        popUpBtn.text = selectedFurnitureTitle
                        true
                    }
                    else -> {
                        false
                    }
                }

            }
            popupUpMenu.show()
        }
        val manager = (activity as ManagerActivity).getManager()
        val popUpBtn1 = binding.PopupBtn1
        var selectedMaterialTitle = ""
        val pluscoast1 = binding.pluscoast1
        var pluscoast2 = binding.pluscoast2

        popUpBtn1.setOnClickListener { view ->
            val popupUpMenu1 = PopupMenu(requireContext(), view)
            popupUpMenu1.inflate(R.menu.pop_up_menu_1)
            popupUpMenu1.setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId) {

                    R.id.material1 -> {
                        selectedMaterialTitle = "ДСП"
                        pluscoast1.text = ""
                        popUpBtn1.text = selectedMaterialTitle
                        true
                    }
                    R.id.material2 -> {
                        selectedMaterialTitle = "Дерево"
                        pluscoast1.text = "+70Byn"
                        popUpBtn1.text = selectedMaterialTitle
                        true
                    }
                    R.id.material3 -> {
                        selectedMaterialTitle = "МДФ"
                        pluscoast1.text = "+40Byn"
                        popUpBtn1.text = selectedMaterialTitle
                        true
                    }
                    else -> {
                        false
                    }
                }

            }
            popupUpMenu1.show()
        }
        val popUpBtn2 = binding.PopupBtn2
        var selectedFittingTitle = ""
        popUpBtn2.setOnClickListener { view ->
            val popupUpMenu2 = PopupMenu(requireContext(), view)
            popupUpMenu2.inflate(R.menu.popup_menu_2)
            popupUpMenu2.setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId) {

                    R.id.fittings1 -> {
                        selectedFittingTitle = "Blum"
                        pluscoast2.text = "+50Byn"
                        popUpBtn2.text = selectedFittingTitle
                        true
                    }
                    R.id.fittings2 -> {
                        selectedFittingTitle = "Aks"
                        popUpBtn2.text = selectedFittingTitle
                        true
                    }
                    R.id.fittings3 -> {
                        selectedFittingTitle = "GTV"
                        pluscoast2.text = "+20Byn"
                        popUpBtn2.text = selectedFittingTitle
                        true
                    }
                    else -> {
                        false
                    }
                }

            }
            popupUpMenu2.show()
        }


        binding.btncoast.setOnClickListener {
            val order = Order()

            Log.d("ORDER_LIST_BEFORE", manager.orderList.toString())

            if (binding.width.text.toString().isNotEmpty() && binding.height.text.toString()
                    .isNotEmpty() && binding.length.text.toString().isNotEmpty() &&
                selectedFittingTitle.isNotEmpty() && selectedMaterialTitle.isNotEmpty() && selectedFurnitureTitle.isNotEmpty()
            ) {

                order.width = binding.width.text.toString().toFloat()
                order.height = binding.height.text.toString().toFloat()
                order.length = binding.length.text.toString().toFloat()

                order.furniture = furnitureList.find { it.title == selectedFurnitureTitle }
                order.furniture?.let {
                    order.fitting = fittingList.find { it.title == selectedFittingTitle }
                    order.material = materialList.find { it.title == selectedMaterialTitle }

                    val metersCount = order.width!! * order.height!! * order.length!!
                    val costForSize = order.furniture!!.priceForMeter * metersCount
                    val fittingCost = order.fitting!!.price * order.furniture!!.fittingCount
                    val cost = costForSize + fittingCost + order.material!!.price
                    val name = "BYN"

                    order.price = cost.toInt()
                    manager.orderList.add(order)
                    manager.saveOrderList(activity as MainActivity)
                    binding.roundcoast.text = order.price.toString() + name
                }
            } else {
                binding.roundcoast.text = "Не ввели/Ввели неверное значение "
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
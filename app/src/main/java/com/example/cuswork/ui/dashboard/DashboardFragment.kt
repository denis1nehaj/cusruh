package com.example.cuswork.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProvider
import android.widget.PopupMenu
import com.example.cuswork.R
import com.example.cuswork.databinding.FragmentDashboardBinding
import com.example.cuswork.ui.Furniture

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val dashboardViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val popUpBtn = binding.PopupBtn
        var selectedFurnitureTitle = ""
        popUpBtn.setOnClickListener{view ->
            val popupUpMenu = PopupMenu(requireContext(),view)
            popupUpMenu.inflate(R.menu.popup_menu)
            popupUpMenu.setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId){

                    R.id.furniture1 ->{
                      selectedFurnitureTitle = "Тумба"
                        popUpBtn.text =  selectedFurnitureTitle
                        true
                    }
                    R.id.furniture2 ->{
                        selectedFurnitureTitle = "Шкаф"
                        popUpBtn.text =  selectedFurnitureTitle
                        true
                    }
                    R.id.furniture3 ->{
                        selectedFurnitureTitle = "Стол с Тумбой"
                        popUpBtn.text =  selectedFurnitureTitle
                        true
                    }
                    else ->{
                        false
                    }
                }

            }
            popupUpMenu.show()
        }

        val popUpBtn1 = binding.PopupBtn1
        var selectedMaterialTitle = ""
        popUpBtn1.setOnClickListener{view ->
            val popupUpMenu1 = PopupMenu(requireContext(),view)
            popupUpMenu1.inflate(R.menu.pop_up_menu_1)
            popupUpMenu1.setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId){

                    R.id.material1 ->{
                        selectedMaterialTitle = "ДСП"
                        popUpBtn1.text =  selectedMaterialTitle
                        true
                    }
                    R.id.material2 ->{
                        selectedMaterialTitle = "Дерево"
                        popUpBtn1.text =  selectedMaterialTitle
                        true
                    }
                    R.id.material3 ->{
                        selectedMaterialTitle = "МДФ"
                        popUpBtn1.text =  selectedMaterialTitle
                        true
                    }
                    else ->{
                        false
                    }
                }

            }
            popupUpMenu1.show()
        }
        val popUpBtn2 = binding.PopupBtn2
        var selectedFittingTitle = ""
        popUpBtn2.setOnClickListener{view ->
            val popupUpMenu2 = PopupMenu(requireContext(),view)
            popupUpMenu2.inflate(R.menu.popup_menu_2)
            popupUpMenu2.setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId){

                    R.id.fittings1 ->{
                        selectedFittingTitle = "Bluem"
                        popUpBtn2.text =  selectedFittingTitle
                        true
                    }
                    R.id.fittings2 ->{
                        selectedFittingTitle = "Aks"
                        popUpBtn2.text =  selectedFittingTitle
                        true
                    }
                    R.id.fittings3 ->{
                        selectedFittingTitle = "GTV"
                        popUpBtn2.text =  selectedFittingTitle
                        true
                    }
                    else ->{
                        false
                    }
                }

            }
            popupUpMenu2.show()
        }

        val furnitureList = listOf(Furniture("Тумба", 200.00, 2), Furniture("Шкаф", 600.00, 6),Furniture("Стол с Тумбой",400.00,4))
        val materialMap = mapOf(Pair("Дерево", 70), Pair("ДСП", 0),Pair("МДФ",40))
        val fittingMap = mapOf(Pair("Blum", 50),Pair("Aks",0),Pair("GTV",20))

        val selectedFurniture = furnitureList.find { it.title == selectedFurnitureTitle}
        selectedFurniture?.let {
            val selectedFitting = fittingMap[selectedFittingTitle] ?: throw java.lang.Exception("fitting not found")
            val selectedMaterial = materialMap[selectedMaterialTitle] ?: throw java.lang.Exception("material not found")
            val width = 0.2
            val height = 0.5
            val length = 0.25

            val metersCount = width * height * length
            val costForSize = selectedFurniture.priceForMeter * metersCount
            val fittingCost = selectedFitting * selectedFurniture.fittingCount

            val cost = costForSize + fittingCost + selectedMaterial

            binding.cost.text = cost.toString()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
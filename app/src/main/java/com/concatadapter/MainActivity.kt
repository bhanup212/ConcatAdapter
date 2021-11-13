package com.concatadapter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.concatadapter.adapters.*
import com.concatadapter.databinding.ActivityMainBinding
import com.concatadapter.model.Field
import com.concatadapter.model.ViewType

class MainActivity : AppCompatActivity(), Listener {

    private lateinit var homeViewModel: HomeViewModel

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // private val adapterList = ArrayList<RecyclerView.Adapter<out RecyclerView.ViewHolder>>()
    private lateinit var mergeAdapter: MergeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getDynamicData()
        observeViewModel()
    }

    private fun observeViewModel() {
        homeViewModel.isLoading.observe(
            this,
            Observer {
                binding.progressBar.isVisible = it
            }
        )
        homeViewModel.fieldList.observe(
            this,
            { list ->
                addToAdapters(list)
            }
        )
        homeViewModel.errorMsg.observe(
            this,
            { msg ->
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            }
        )
    }

    private fun addToAdapters(list: ArrayList<Field>) {
        mergeAdapter = MergeAdapter(getMergeConfig())
        createAdapters(list, mergeAdapter)
        binding.recyclerview.adapter = mergeAdapter
        mergeAdapter.notifyDataSetChanged()
    }

    private fun getMergeConfig(): MergeAdapter.Config {
        return MergeAdapter.Config.Builder().setIsolateViewTypes(true)
                .build()
    }
    private fun createAdapters(list: ArrayList<Field>, mergeAdapter: MergeAdapter){
        list.forEach {
            when (it.viewType) {
                ViewType.Label.name -> {
                    val labelAdapter = LabelAdapter(this)
                    labelAdapter.addField(it)
                    mergeAdapter.addAdapter(labelAdapter)
                }
                ViewType.Input.name -> {
                    val inputAdapter = InputAdapter(this)
                    inputAdapter.addField(it)
                    mergeAdapter.addAdapter(inputAdapter)
                }
                ViewType.Image.name -> {
                    val imageAdapter = ImageAdapter(this)
                    imageAdapter.addField(it)
                    mergeAdapter.addAdapter(imageAdapter)
                }
                ViewType.Button.name -> {
                    val buttonAdapter = ButtonAdapter(this)
                    buttonAdapter.addField(it)
                    mergeAdapter.addAdapter(buttonAdapter)
                }
            }
            if (it.childList?.isNotEmpty() == true){
                val childAdapter = MergeAdapter(getMergeConfig())
                createAdapters(it.childList, childAdapter)
                mergeAdapter.addAdapter(childAdapter)
            }
        }
    }

    override fun addField(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>?, field: Field) {
        when (adapter) {
            is InputAdapter -> {
                adapter.addField(field.copy(isAddViewEnable = false))
                adapter.notifyDataSetChanged()
            }
            is ImageAdapter -> {
                adapter.addField(field.copy(isAddViewEnable = false))
                adapter.notifyDataSetChanged()
            }
        }
    }
}

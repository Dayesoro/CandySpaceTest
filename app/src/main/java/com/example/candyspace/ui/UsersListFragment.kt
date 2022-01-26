package com.example.candyspacetest.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.candyspace.R
import com.example.candyspace.databinding.FragmentUsersListBinding
import com.example.candyspace.domain.entity.UsersData
import com.example.candyspace.ui.UsersListFragmentDirections
import com.example.candyspace.ui.adapter.UserAdapter
import com.example.candyspacetest.common.DataState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class UsersListFragment : Fragment() {
    private val viewModel: UserViewModel by viewModels()
    lateinit var binding:FragmentUsersListBinding
    private lateinit var  userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater,container,false)
        subscribeObserver()
        setUpViews()
        return binding.root
    }

    private fun setUpViews() {
        with(binding){
            userAdapter = UserAdapter(::onClick)
            usersList.layoutManager= LinearLayoutManager(context, VERTICAL,false)
            usersList.adapter = userAdapter
            val itemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(resources.getDrawable(R.drawable.dashed_line))
            usersList.addItemDecoration(itemDecoration)
        }
    }

    private fun subscribeObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userUiState.collect {
                    when(it){
                        is DataState.Error -> {
                            Timber.i("error ${it.error}")
                            binding.indicator.visibility= INVISIBLE
                            binding.indicator.setVisibilityAfterHide(GONE)
                            Snackbar.make(binding.root,"Something went wrong ${it.error}",Snackbar.LENGTH_SHORT).show()
                        }
                        is DataState.Loading -> {
                            Timber.i("Loading")
                            binding.indicator.visibility= VISIBLE
                        }
                        is DataState.Success -> {
                            binding.indicator.visibility= INVISIBLE
                            binding.indicator.setVisibilityAfterHide(GONE)
                            userAdapter.submitList(it.data?.items)
                        }
                    }
                }
            }
        }
    }
    private fun onClick(usersData: UsersData) {
        val action = UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(usersData)
        findNavController().navigate(action)

    }
}



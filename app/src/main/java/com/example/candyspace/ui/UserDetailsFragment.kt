package com.example.candyspace.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.candyspace.databinding.FragmentUserDetailsBinding
import com.example.candyspace.utils.DateExtension.convertLongToTime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    lateinit var binding: FragmentUserDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater,container,false)
        setUpViews()
        return binding.root
    }

    private fun setUpViews() {
        arguments?.let {
            val user = UserDetailsFragmentArgs.fromBundle(it).UsersData
            with(binding){
                userNameLabel.text = user.DisplayName
                locationLabel.text = user.Location?:"No Location"
                reputationLabel.text = user.Reputation.toString()
                creationDateLabel.text = convertLongToTime(user.CreationDate?:0)
                imageView.load(user.ProfileImage)
                toolbar.setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
                with(user.Badge){
                    badgeLabel.text = "Gold: ${this?.gold} \nSilver:${this?.silver}\nBronze:${this?.bronze} "
                }

            }
        }

    }
}
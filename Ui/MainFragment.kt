package com.example.userlistapp.ui

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.userlistapp.R
import com.example.userlistapp.data.User
import com.example.userlistapp.databinding.FragmentMainBinding
import com.example.userlistapp.viewmodel.UserViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        adapter = UserAdapter(
            onClick = { user ->
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(user.name, user.age)
                findNavController().navigate(action)
            },
            onLongClick = { user ->
                userViewModel.delete(user)
                Toast.makeText(requireContext(), "Удалено", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerView.adapter = adapter

        userViewModel.allUsers.observe(viewLifecycleOwner, Observer { users ->
            adapter.submitList(users)
        })

        binding.buttonAdd.setOnClickListener {
            val name = binding.editName.text.toString()
            val age = binding.editAge.text.toString().toIntOrNull()

            if (name.isNotBlank() && age != null) {
                userViewModel.insert(User(name = name, age = age))
                binding.editName.text.clear()
                binding.editAge.text.clear()
            } else {
                Toast.makeText(requireContext(), "Введите имя и возраст", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}

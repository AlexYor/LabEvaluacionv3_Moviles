package com.flores.yorsh.laboratoriocalificado03

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examem_sem13.databinding.ActivityEjercicio01Binding


class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding
    private lateinit var viewModel: TeacherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TeacherViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
        viewModel.fetchTeachers()
    }

    private fun setupRecyclerView() {
        binding.rvTeachers.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        viewModel.teacherList.observe(this) { teachers ->
            binding.rvTeachers.adapter = TeacherAdapter(teachers, this)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        }
    }
}
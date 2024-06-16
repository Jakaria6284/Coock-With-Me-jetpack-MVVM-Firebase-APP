package com.kamaljakaria.cookwithme.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamaljakaria.cookwithme.Data.Recipe
import com.kamaljakaria.cookwithme.Data.cheifdata
import com.kamaljakaria.cookwithme.Data.repository
import kotlinx.coroutines.launch

class viewModel : ViewModel() {
    private val repository = repository()

    private val _items = MutableLiveData<List<cheifdata>>()
    val items: LiveData<List<cheifdata>> = _items

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun fetchChefData() {
        viewModelScope.launch {
            _items.value = repository.fetchAllchefData()
        }
    }

    fun fetchRecipesForChef(chefId: String) {
        viewModelScope.launch {
            _recipes.value = repository.fetchRecipesForChef(chefId)
        }
    }
}

package com.mealotopia.client.view.activity.DetailMealActivity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.mealotopia.client.R
import com.mealotopia.client.databinding.ActivityDetailMealBinding
import com.mealotopia.client.model.data_class.meal.DetailMealResponse
import com.mealotopia.client.model.data_class.meal.MealIngridients
import com.mealotopia.client.view.adapter.ListIngridientAdapter
import com.mealotopia.client.viewmodel.DetailnBookmarkViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMealActivity : AppCompatActivity() {
    private val TAG = DetailMealActivity::class.java.simpleName
    private lateinit var binding: ActivityDetailMealBinding
    private lateinit var deliveredMealResponse: DetailMealResponse
    private lateinit var cleanListMealIngridient: List<MealIngridients>
    private var isBookmarked: Boolean = false

    private lateinit var detailnBookmarkViewModel: DetailnBookmarkViewModel

    companion object{
        private const val DELIVERED_MEAL = "DELIVERED_MEAL"
        fun newIntent(context: Context, deliveredMeal: DetailMealResponse): Intent = Intent(context, DetailMealActivity::class.java)
            .putExtra(DELIVERED_MEAL, deliveredMeal)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailnBookmarkViewModel = ViewModelProvider(this)[DetailnBookmarkViewModel::class.java]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            deliveredMealResponse = intent.extras!!.getParcelable(DELIVERED_MEAL, DetailMealResponse::class.java)!!
        }else{
            deliveredMealResponse = intent.extras!!.get(DELIVERED_MEAL) as DetailMealResponse
        }

        setStatusBookmarked(deliveredMealResponse)

        setUpView()
    }

    private fun setUpView(){
        Log.d(TAG, "deliveredMealResponse: ${deliveredMealResponse}")

        val rawListMealIngridient = listOf<MealIngridients>(
            MealIngridients(deliveredMealResponse.strIngredient1, deliveredMealResponse.strMeasure1),
            MealIngridients(deliveredMealResponse.strIngredient2, deliveredMealResponse.strMeasure2),
            MealIngridients(deliveredMealResponse.strIngredient3, deliveredMealResponse.strMeasure3),
            MealIngridients(deliveredMealResponse.strIngredient4, deliveredMealResponse.strMeasure4),
            MealIngridients(deliveredMealResponse.strIngredient5, deliveredMealResponse.strMeasure5),
            MealIngridients(deliveredMealResponse.strIngredient6, deliveredMealResponse.strMeasure6),
            MealIngridients(deliveredMealResponse.strIngredient7, deliveredMealResponse.strMeasure7),
            MealIngridients(deliveredMealResponse.strIngredient8, deliveredMealResponse.strMeasure8),
            MealIngridients(deliveredMealResponse.strIngredient9, deliveredMealResponse.strMeasure9),
            MealIngridients(deliveredMealResponse.strIngredient10, deliveredMealResponse.strMeasure10),
            MealIngridients(deliveredMealResponse.strIngredient11, deliveredMealResponse.strMeasure11),
            MealIngridients(deliveredMealResponse.strIngredient12, deliveredMealResponse.strMeasure12),
            MealIngridients(deliveredMealResponse.strIngredient13, deliveredMealResponse.strMeasure13),
            MealIngridients(deliveredMealResponse.strIngredient14, deliveredMealResponse.strMeasure14),
            MealIngridients(deliveredMealResponse.strIngredient15, deliveredMealResponse.strMeasure15),
            MealIngridients(deliveredMealResponse.strIngredient16, deliveredMealResponse.strMeasure16),
            MealIngridients(deliveredMealResponse.strIngredient17, deliveredMealResponse.strMeasure17),
            MealIngridients(deliveredMealResponse.strIngredient18, deliveredMealResponse.strMeasure18),
            MealIngridients(deliveredMealResponse.strIngredient19, deliveredMealResponse.strMeasure19),
            MealIngridients(deliveredMealResponse.strIngredient20, deliveredMealResponse.strMeasure20)
        )

        cleanListMealIngridient = filterListIngridients(rawListMealIngridient)

        binding.apply {

            setSupportActionBar(detailToolbar)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_white)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowTitleEnabled(false)

            binding.detailToolbar.findViewById<TextView>(R.id.tvToolbarTitle).text = getString(R.string.tvToolbarTitle)

            Glide.with(this@DetailMealActivity)
                .load(deliveredMealResponse.strMealThumb)
                .into(ivMeal)

            tvMealName.text = deliveredMealResponse.strMeal

            rvMealIngridient.apply {
                layoutManager = GridLayoutManager(this@DetailMealActivity, 2)
                adapter = ListIngridientAdapter(cleanListMealIngridient.toMutableList())
            }

            tvInstruction.text = deliveredMealResponse.strInstructions
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_meal_menu, menu)
        val bookmarkItem: MenuItem = menu!!.findItem(R.id.menu_BookmarkMeal)
        if(isBookmarked){
            bookmarkItem.setIcon(R.drawable.ic_bookmark_yellow)
        }else{
            bookmarkItem.setIcon(R.drawable.ic_bookmark_grey)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }
            R.id.menu_BookmarkMeal ->{
                if(isBookmarked){
                    item.setIcon(R.drawable.ic_bookmark_grey)
                    isBookmarked = false
                    //Ada proses remove dari room
                    deliveredMealResponse.idMeal?.let { detailnBookmarkViewModel.removeFromBookmarkedMeal(it) }
                    displaySnackbar("Meal unbookmarked from Saved meal...")
                }else{
                    item.setIcon(R.drawable.ic_bookmark_yellow)
                    isBookmarked = true
                    //Ada proses add ke room
                    detailnBookmarkViewModel.addMealToBookmarkedMeal(deliveredMealResponse)
                    displaySnackbar("Meal added to saved meal...")
                }
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun filterListIngridients(listIngridients: List<MealIngridients>): List<MealIngridients>{
        return listIngridients.filter { !it.ingridientName.isNullOrBlank() && !it.ingridientMeasure.isNullOrBlank()}
    }

    private fun displaySnackbar(text: String){
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }

    private fun setStatusBookmarked(dataMeal: DetailMealResponse){
        CoroutineScope(Dispatchers.IO).launch {
            val count = dataMeal.idMeal?.let { detailnBookmarkViewModel.isMealBookmarked(it) }
            isBookmarked = count!! > 0
        }
    }
}
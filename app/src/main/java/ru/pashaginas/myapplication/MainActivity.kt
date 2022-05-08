package ru.pashaginas.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), BaseRouter {

    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        routeTo(fragment = FragmentA())

        fab = findViewById(R.id.fab)
        fab.setOnClickListener(fablistener)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView.adapter = MoneyItemsAdapter(fillList())

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    private fun fillList(): MutableList<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i item") }
        //todo locale
        return data
    }

    private val fablistener = View.OnClickListener { view ->
        when (view.id) {
            R.id.fab -> {
                val intent = Intent(this, AddItemActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun routeTo(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity_id, fragment)
            .commit()
    }
}

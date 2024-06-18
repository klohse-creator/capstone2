package com.example.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.example.myapplication.R;
import com.example.myapplication.database.Repository;
import com.example.myapplication.entities.Excursion;
import com.example.myapplication.entities.Vacation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VacationList extends AppCompatActivity {
    private Repository repository;
    private RecyclerView recyclerView;
    private VacationAdapter vacationAdapter;
    private SearchView vacationSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vacation_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        SearchView vacationSearch = findViewById(R.id.vacationSearch);

        repository = new Repository(getApplication());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vacationAdapter = new VacationAdapter(this);
        recyclerView.setAdapter(vacationAdapter);

        List<Vacation> allVacations = repository.getmAllVacations();
        vacationAdapter.setVacations(allVacations);

        vacationSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                vacationAdapter.getFilter().filter(newText);
                return true;
            }
        });


        FloatingActionButton fab = findViewById(R.id.floatingActionButton3);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VacationList.this, VacationDetails.class);
                startActivity(intent);
            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
        List<Vacation> allVacations = repository.getmAllVacations();
        vacationAdapter.setVacations(allVacations);
    }

}
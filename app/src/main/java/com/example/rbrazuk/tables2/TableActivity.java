package com.example.rbrazuk.tables2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

public class TableActivity extends AppCompatActivity {

    private Team[] mTeams;

    @Bind(R.id.lv_table) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        String leagueId = intent.getStringExtra(MainActivity.TAG);

        String tableUrl = "http://api.football-data.org/v1/soccerseasons/" + leagueId + "/leagueTable";

        System.out.println(tableUrl);

        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();






    }
}

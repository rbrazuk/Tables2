package com.example.rbrazuk.tables2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TableActivity extends AppCompatActivity {

    private Team[] mTeams;
    private String leagueName;
    private String leagueId;

    @Bind(R.id.lv_table) ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        ButterKnife.bind(this);

        Intent intent = getIntent();

        leagueId = intent.getStringExtra(MainActivity.TAG);

        String tableUrl = "http://api.football-data.org/v1/soccerseasons/" + leagueId + "/leagueTable";

        System.out.println(tableUrl);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(tableUrl).addHeader("X-Auth-Token","95468454664049ca81f3fc478ede7ef2").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                String jsonData = response.body().string();
                System.out.println(jsonData);

                try {
                    mTeams = parseJsonTeam(jsonData);
                    leagueName = getLeagueName(jsonData);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getSupportActionBar().setTitle(leagueName);
                            TableAdapter adapter = new TableAdapter(getApplicationContext(),mTeams);
                            mListView.setAdapter(adapter);

                            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(TableActivity.this, TeamDetail.class);
                                    Team parcelableTeam = (Team) parent.getItemAtPosition(position);
                                    intent.putExtra("team", parcelableTeam);
                                    startActivity(intent);
                                }
                            });

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("leagueId", leagueId);
        System.out.println("added " + leagueId + " to bundle");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        leagueId = savedInstanceState.getString("leagueId");
    }



    private String getLeagueName(String json) throws JSONException {
        JSONObject table = new JSONObject(json);
        String seasonName = table.getString("leagueCaption");

        return seasonName;
    }

    private Team[] parseJsonTeam(String json) throws JSONException {
        JSONObject table = new JSONObject(json);
        JSONArray standing = table.getJSONArray("standing");

        System.out.println(standing.length());

        mTeams = new Team[standing.length()];

        for (int i = 0; i < standing.length(); i++) {
            JSONObject jsonTeam = standing.getJSONObject(i);
            String url = jsonTeam.getJSONObject("_links").getJSONObject("team").getString("href");
            Team team = new Team();

            team.setTeamName(jsonTeam.getString("teamName"));
            team.setPosition(jsonTeam.getInt("position"));
            team.setGoalDifference(jsonTeam.getInt("goalDifference"));
            team.setPoints(jsonTeam.getInt("points"));
            team.setWins(jsonTeam.getInt("wins"));
            team.setDraws(jsonTeam.getInt("draws"));
            team.setLosses(jsonTeam.getInt("losses"));
            team.setUrl(url);

            mTeams[i] = team;

        }

        return mTeams;
    }

    
}

package com.example.rbrazuk.tables2;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeamDetail extends AppCompatActivity {

    @Bind(R.id.tv_record) TextView mRecordLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        Team team = getIntent().getParcelableExtra("team");
        getSupportActionBar().setTitle(team.getTeamName());

        String wins = team.getWins() + "";
        String draws = team.getDraws() + "";
        String losses = team.getLosses() + "";

        mRecordLabel.setText(wins + " - " + draws + " - " + losses);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // This is the up button
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

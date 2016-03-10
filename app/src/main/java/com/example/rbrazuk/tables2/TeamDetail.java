package com.example.rbrazuk.tables2;


import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;


import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TeamDetail extends AppCompatActivity {

    @Bind(R.id.tv_record) TextView mRecordLabel;
    @Bind(R.id.iv_team_crest) ImageView mTeamCrest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);



        Team team = getIntent().getParcelableExtra("team");
        getSupportActionBar().setTitle(team.getTeamName());

        String crestUrl = team.getCrestUrl();

        Log.d("TeamDetail", "image url: " + crestUrl);

        String wins = team.getWins() + "";
        String draws = team.getDraws() + "";
        String losses = team.getLosses() + "";

        Log.d("TeamDetail","team URL: " + team.getUrl());

        /*try {
            SVG svg = SVG.(crestUrl);
            Log.d("TeamDetail", "svg: " + svg.toString());
        } catch (SVGParseException e) {
            Log.e("TeamDetail", e.getMessage());
        }*/

        Glide.with(this).load(Uri.parse(crestUrl)).into(mTeamCrest);
        //Picasso.with(this).load(crestUrl).into(mTeamCrest);

        /*if(crestUrl.contains(".png")) {
            Glide.with(this).load(Uri.parse(crestUrl)).into(mTeamCrest);
        } else {



        }*/





        mRecordLabel.setText(wins + " - " + draws + " - " + losses);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}

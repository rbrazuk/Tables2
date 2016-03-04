package com.example.rbrazuk.tables2;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mLeagues;
    private static final String PREMIER_LEAGUE_ID = "398";
    private static final String BUNDESLIGA_ID = "394";
    private static final String LA_LIGA_ID = "399";
    private static final String SERIE_A_ID = "401";
    private static final String LIGUE_1_ID = "396";
    private static final String EREDIVISIE_ID = "404";

    public static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.lv_leagues)
    ListView mLeaguesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mLeagues = new ArrayList();
        mLeagues.add("Premier League");
        mLeagues.add("Bundesliga");
        mLeagues.add("Ligue 1");
        mLeagues.add("La Liga");
        mLeagues.add("Serie A");
        mLeagues.add("Eredivisie");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mLeagues);
        mLeaguesListView.setAdapter(adapter);

        mLeaguesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(parent.getItemAtPosition(position));

                Intent intent = new Intent(MainActivity.this, TableActivity.class);
                switch (position) {
                    case 0:
                        intent.putExtra(TAG, PREMIER_LEAGUE_ID);
                        break;
                    case 1:
                        intent.putExtra(TAG,BUNDESLIGA_ID);
                        break;
                    case 2:
                        intent.putExtra(TAG,LIGUE_1_ID);
                        break;
                    case 3:
                        intent.putExtra(TAG,LA_LIGA_ID);
                        break;
                    case 4:
                        intent.putExtra(TAG,SERIE_A_ID);
                        break;
                    case 5:
                        intent.putExtra(TAG,EREDIVISIE_ID);
                        break;
                    default:
                        break;


                }
                startActivity(intent);
            }
        });


    }
}

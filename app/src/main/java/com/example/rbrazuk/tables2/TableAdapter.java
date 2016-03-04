package com.example.rbrazuk.tables2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TableAdapter extends ArrayAdapter<Team> {
    public TableAdapter(Context context,Team[] teams) {
        super(context,0,teams);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Team team = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.team_list_item,parent,false);
        }

        TextView tvPosition = (TextView) convertView.findViewById(R.id.tv_position);
        TextView tvTeamName = (TextView) convertView.findViewById(R.id.tv_team_name);
        TextView tvGoalDifference = (TextView) convertView.findViewById(R.id.tv_goal_difference);
        TextView tvPoints = (TextView) convertView.findViewById(R.id.tv_points);

        tvPosition.setText(team.getPosition() + "");
        tvTeamName.setText(team.getTeamName() + "");
        tvGoalDifference.setText(team.getGoalDifference() + "");
        tvPoints.setText(team.getPoints() + "");


        return convertView;
    }
}

package com.example.rbrazuk.tables2;


import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {

    private int position;
    private String teamName;
    private int goalDifference;
    private int points;

    private String url;
    private int wins;
    private int draws;
    private int losses;
    private String crestUrl;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.position);
        dest.writeString(this.teamName);
        dest.writeInt(this.goalDifference);
        dest.writeInt(this.points);
        dest.writeString(this.url);
        dest.writeInt(this.wins);
        dest.writeInt(this.draws);
        dest.writeInt(this.losses);
        dest.writeString(this.crestUrl);
    }

    public Team() {
    }

    protected Team(Parcel in) {
        this.position = in.readInt();
        this.teamName = in.readString();
        this.goalDifference = in.readInt();
        this.points = in.readInt();
        this.url = in.readString();
        this.wins = in.readInt();
        this.draws = in.readInt();
        this.losses = in.readInt();
        this.crestUrl = in.readString();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}

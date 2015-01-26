package ws.mahesh.cwc2015.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ws.mahesh.cwc2015.R;
import ws.mahesh.cwc2015.databasehelpers.DatabaseHelper;
import ws.mahesh.cwc2015.teams.TeamsAdapter;
import ws.mahesh.cwc2015.teams.TeamsObject;
import ws.mahesh.cwc2015.utils.DividerItemDecoration;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private Cursor team_cursor;
    private List<TeamsObject> teamsObject = new ArrayList<>();
    private TeamsAdapter adapter;

    public TeamsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new DatabaseHelper(getActivity());
        team_cursor = dbHelper.getTeams();
        fillData();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.teams_recyclerview);
        adapter = new TeamsAdapter(getActivity(), teamsObject);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void fillData() {
        int i = 0;
        if (team_cursor != null) {
            if (team_cursor.moveToFirst()) {
                do {
                    TeamsObject temp = new TeamsObject(i, getIcon(team_cursor.getString(team_cursor.getColumnIndex("team_id"))), team_cursor.getString(team_cursor.getColumnIndex("team")), team_cursor.getString(team_cursor.getColumnIndex("captain")), team_cursor.getString(team_cursor.getColumnIndex("coach")), team_cursor.getString(team_cursor.getColumnIndex("players")));
                    teamsObject.add(temp);
                } while (team_cursor.moveToNext());
            }
        }
    }

    private int getIcon(String team_id) {
        switch (team_id) {
            case "AFG":
                return R.drawable.ic_afg_24;
            case "AUS":
                return R.drawable.ic_aus_24;
            case "BAN":
                return R.drawable.ic_ban_24;
            case "ENG":
                return R.drawable.ic_eng_24;
            case "IND":
                return R.drawable.ic_ind_24;
            case "IRE":
                return R.drawable.ic_ire_24;
            case "NZ":
                return R.drawable.ic_nz_24;
            case "PAK":
                return R.drawable.ic_pak_24;
            case "SCT":
                return R.drawable.ic_sct_24;
            case "SA":
                return R.drawable.ic_sa_24;
            case "SL":
                return R.drawable.ic_sri_24;
            case "UAE":
                return R.drawable.ic_uae_24;
            case "WI":
                return R.drawable.ic_wi_24;
            case "ZIM":
                return R.drawable.ic_zim_24;
            default:
                return R.drawable.ic_public_black_24dp;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        dbHelper.close();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
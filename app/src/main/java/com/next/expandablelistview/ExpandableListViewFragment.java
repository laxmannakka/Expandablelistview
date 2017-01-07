package com.next.expandablelistview;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by next on 6/1/17.
 */
public class ExpandableListViewFragment extends Fragment {

    View mView;
    ExpandableListView listview;
    private int lastExpandedPosition = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.listfragment, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listview = (ExpandableListView) mView.findViewById(R.id.expanndablelistview);
        ArrayList<ChildModel> childDataList = setChildDetails(); // this function set the child data

        listview.setAdapter(new ExpandableAdaptor(getActivity(), childDataList)); // setting the adapter

        //this method will call when the listview is expanded
        //if listview is expanded it close the previous one open the new one
        listview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    listview.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        // when collasping that time it will call
        listview.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(), "Group Collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        // when child clicks this method will call
        listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), "positions of group and child" + groupPosition + childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // this function not doing mean click events for parent view group
        listview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Doing nothing
                Toast.makeText(getActivity(), "Group in not doing", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    ArrayList<ChildModel> setChildDetails() {
        ArrayList<ChildModel> dataList = new ArrayList<>();
        String[] name = {"Dhoni", "Kohli", "raina", "vijay"};
        String[] role = {"Captain", "Batsman", "All Rounder", "Batsman"};
        int[] age = {38, 29, 32, 30};
        for (int i = 0; i < 4; i++) {
            dataList.add(new ChildModel(name[i], role[i], age[i]));
        }
        return dataList;
    }
}

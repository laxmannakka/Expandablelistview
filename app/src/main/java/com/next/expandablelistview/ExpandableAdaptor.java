package com.next.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by next on 6/1/17.
 */
public class ExpandableAdaptor extends BaseExpandableListAdapter {

    Context mContext;
    int[] mImageArray = {R.drawable.dhoni, R.drawable.kohli, R.drawable.raina, R.drawable.vijay};
    ArrayList<ChildModel> childdatalist;

    public ExpandableAdaptor(Context mContext, ArrayList<ChildModel> childDataList) {
        this.mContext = mContext;
        this.childdatalist = childDataList;
    }

    @Override
    public int getGroupCount() {
        return mImageArray.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mImageArray[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childdatalist.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.parentlayout, null);
     //   ImageView imageview = (ImageView) convertView.findViewById(R.id.imageview);
        TextView textView = (TextView) convertView.findViewById(R.id.details);
     //   imageview.setImageResource(mImageArray[groupPosition]);
      //  convertView.setClickable(false);
        if(groupPosition==0){
            textView.setText("1");
        }
        else if(groupPosition==1){
            textView.setText("2");
        }
        else if(groupPosition==2){
            textView.setText("3");
        }
        else {
            textView.setText("4");
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isExpanded)
                    ((ExpandableListView) parent).collapseGroup(groupPosition);
                else
                    ((ExpandableListView) parent).expandGroup(groupPosition, true);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.childlayout2, null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView age = (TextView) convertView.findViewById(R.id.age);
        TextView role = (TextView) convertView.findViewById(R.id.role);
        name.setText(childdatalist.get(groupPosition).getName());
        age.setText(Integer.toString(childdatalist.get(groupPosition).getAge()));
        role.setText(childdatalist.get(groupPosition).getRole());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

package com.example.pubu.myapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.pubu.myapplication.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pubu on 2016/1/14.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> groupTitle;
    HashMap<String, List<String>> groupDetail;

    public MyExpandableListAdapter(Context context, List<String> groupTitle, HashMap<String, List<String>> groupDetail) {
        this.context = context;
        this.groupTitle = groupTitle;
        this.groupDetail = groupDetail;
    }

    @Override
    public int getGroupCount() {
        return groupTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupDetail.get(groupTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupDetail.get(groupTitle.get(groupPosition)).get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);
        GroupTitleHolder groupTitleHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandable_view_list_group, null);
            groupTitleHolder = new GroupTitleHolder((TextView)convertView.findViewById(R.id.expandableListGroup));
            convertView.setTag(groupTitleHolder);
        } else {
            groupTitleHolder = (GroupTitleHolder) convertView.getTag();
        }

        groupTitleHolder.expandableListGroup.setTypeface(null, Typeface.BOLD);
        groupTitleHolder.expandableListGroup.setText(groupTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childItem = (String) getChild(groupPosition, childPosition);
        ChildHolder childHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandable_view_list_item, null);
            childHolder = new ChildHolder((TextView)convertView.findViewById(R.id.expandableListItem));
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.expandableListItem.setTypeface(null, Typeface.BOLD);
        childHolder.expandableListItem.setText(childItem);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * ViewHolder pattern: to limit the number of call to findViewById
     */
    private static class GroupTitleHolder {
        private TextView expandableListGroup;

        public GroupTitleHolder(TextView expandableListGroup) {
            this.expandableListGroup = expandableListGroup;
        }
    }

    /**
     * ViewHolder pattern: to limit the number of call to findViewById
     */
    private static class ChildHolder {
        private TextView expandableListItem;

        public ChildHolder(TextView expandableListItem) {
            this.expandableListItem = expandableListItem;
        }
    }
}

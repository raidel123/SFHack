package com.example.admin.sfhack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SF on 6/14/2017.
 */

public class PocketAgentMenuAdapter extends BaseAdapter{
    private Context context;
    private List<PocketAgentMenu> pocketAgentMenuList;

    public PocketAgentMenuAdapter(Context context, List<PocketAgentMenu> pocketAgentMenuList){
        this.context = context;
        this.pocketAgentMenuList = pocketAgentMenuList;
    }

    @Override
    public int getCount() {
        return pocketAgentMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return pocketAgentMenuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PocketAgentMenu entry = pocketAgentMenuList.get(position);

        if(null == convertView){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.pocket_agent_list_items, null);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.imgAvatar);
        imageView.setImageBitmap(entry.getButtonIcon());

        TextView text = (TextView)convertView.findViewById(R.id.menuText);
        text.setText(entry.getMenuName());

        return convertView;
    }
}

package com.example.woundscanada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PlayersModel> playersModelArrayList;

    public CustomAdapter(Context context, ArrayList<PlayersModel> playersModelArrayList) {
        this.context = context;
        this.playersModelArrayList = playersModelArrayList;
    }

    @Override
    public int getCount() {
        return playersModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return playersModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.best_practices_fragment, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvcountry = (TextView) convertView.findViewById(R.id.country);
            holder.tvcity = (TextView) convertView.findViewById(R.id.city);
            holder.tvfirstpdf = (TextView) convertView.findViewById(R.id.firstpdf);

//            maybe I can put everything in here to setup and then just have them called in the xml?
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();

        }

        holder.tvname.setText("Name: " + playersModelArrayList.get(position).getName());
        holder.tvcountry.setText("Country: " + playersModelArrayList.get(position).getCountry());
        holder.tvcity.setText("City: " + playersModelArrayList.get(position).getCity());
        holder.tvfirstpdf.setText("Test: " + playersModelArrayList.get(position).getCity());


        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private class ViewHolder {
        protected TextView tvname, tvcountry, tvcity, tvfirstpdf;
    }

}

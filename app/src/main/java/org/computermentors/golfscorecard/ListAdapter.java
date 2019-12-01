package org.computermentors.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    private final Context context;
    private final Hole[] holes;

    public ListAdapter (Context context, Hole[] holes){
        this.context = context;
        this.holes = holes;
    }

    @Override
    public int getCount() {
        return holes.length;
    }

    @Override
    public Object getItem(int position) {
        return holes[position];
    }

    // Not used
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.holeLable = convertView.findViewById(R.id.holeLable);
            holder.strokeCount = convertView.findViewById(R.id.strokeCount);
            holder.removeStrokeButton = convertView.findViewById(R.id.removeStrokeButton);
            holder.addStrokeButton = convertView.findViewById(R.id.addStrokeButton);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.holeLable.setText(holes[position].getLable());
        holder.strokeCount.setText(holes[position].getStrokeCount() + "");
        holder.removeStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updatedStrokeCount = holes[position].getStrokeCount() - 1;
                if (updatedStrokeCount < 0) updatedStrokeCount = 0;
                holes[position].setStrokeCount(updatedStrokeCount);
                holder.strokeCount.setText(updatedStrokeCount + "");
            }
        });
        holder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int updatedStrokeCount = holes[position].getStrokeCount() + 1;
                holes[position].setStrokeCount(updatedStrokeCount);
                holder.strokeCount.setText(updatedStrokeCount + "");
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        TextView holeLable;
        TextView strokeCount;
        Button removeStrokeButton;
        Button addStrokeButton;

    }
}

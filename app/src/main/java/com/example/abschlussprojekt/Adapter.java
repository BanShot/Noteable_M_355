package com.example.abschlussprojekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    RealmResults<Event> eventList;

    public Adapter(Context context, RealmResults<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.timeOutput.setText(event.getTitle());
        holder.descriptionOutput.setText(event.getDescription());

        String formatedTime = DateFormat.getDateTimeInstance().format(event.createdTime);
        holder .timeOutput.setText(formatedTime);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu menu = new PopupMenu(context, view);
                menu.getMenu().add("Löschen");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(item.getTitle().equals("Delete")){
                            // delete the event
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            event.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context, "Event gelöscht", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleOutput;
        TextView descriptionOutput;
        TextView timeOutput;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleoutput);
            descriptionOutput = itemView.findViewById(R.id.descriptionoutput);
            timeOutput = itemView.findViewById(R.id.timeoutput);
        }

    }
}

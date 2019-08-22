package dev.oruizp.feature.room.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import dev.oruizp.databinding.ItemTaskBinding;
import dev.oruizp.feature.room.model.db.TaskEntity;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";
    private List<TaskEntity> itemList;
    private ItemClickListener itemClickListener;
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public TaskAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new TaskViewHolder(ItemTaskBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bindView(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        if (itemList != null) return itemList.size();
        else return 0;
    }

    public void setItemList(List<TaskEntity> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }


    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemTaskBinding itemView;

        TaskViewHolder(@NonNull ItemTaskBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        void bindView(TaskEntity item) {
            itemView.taskDescription.setText(item.getDescription());
            itemView.taskUpdatedAt.setText(dateFormat.format(item.getUpdateAt()));
            itemView.priorityTextView.setText(String.valueOf(item.getPriority()));
            itemView.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = itemList.get(getAdapterPosition()).getId();
            itemClickListener.onItemClickListener(id);
        }
    }
}

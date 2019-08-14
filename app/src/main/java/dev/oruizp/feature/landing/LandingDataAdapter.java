package dev.oruizp.feature.landing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.oruizp.databinding.ItemLandingBinding;


/**
 * This LandingDataAdapter creates and binds ViewHolders, that hold the title for feature,
 * to a RecyclerView to efficiently display data.
 */
public class LandingDataAdapter extends RecyclerView.Adapter<LandingDataAdapter.LandingViewHolder> {

    private List<LandingData> itemList;
    private ItemClickListener itemClickListener;


    /**
     * Constructor for LandingDataAdapter that initialize the List Data and item click handler
     *
     * @param itemList          List tah holds landing data
     * @param itemClickListener Interface to handle item click
     */
    public LandingDataAdapter(List<LandingData> itemList, ItemClickListener itemClickListener) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new LandingViewHolder that holds the view for each feature
     */
    @NonNull
    @Override
    public LandingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new LandingViewHolder(ItemLandingBinding.inflate(layoutInflater, parent, false));
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(@NonNull LandingViewHolder holder, int position) {
        holder.bindView(itemList.get(position));
    }

    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (itemList != null) return itemList.size();
        else return 0;
    }

    public interface ItemClickListener {
        void onItemClickListener(LandingData.Feature feature);
    }

    // Inner class for creating ViewHolders
    class LandingViewHolder extends RecyclerView.ViewHolder {

        private ItemLandingBinding itemBind;

        LandingViewHolder(@NonNull ItemLandingBinding itemBind) {
            super(itemBind.getRoot());
            this.itemBind = itemBind;
        }

        void bindView(final LandingData item) {
            itemBind.textViewItem.setText(item.getTitle());
            itemBind.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClickListener(item.getFeature());
                }
            });
        }
    }
}

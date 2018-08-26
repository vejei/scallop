package io.github.zeleven.scallop.ui.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<M, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected List<M> data = new ArrayList<>();

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void setData(List<M> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<M> getData() {
        return data;
    }

    public void appendItems(List<M> items) {
        if (items != null && (items.size() != 0) && data != null) {
            data.addAll(items);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (data != null) {
            data.clear();
            notifyDataSetChanged();
        }
    }
}

package io.github.zeleven.scallop.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.ui.modules.imageviewer.ImageViewerActivity;
import io.github.zeleven.scallop.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.scallop.ui.base.BaseViewHolder;

public class PictureAdapter extends BaseRecyclerViewAdapter<GanHuo, PictureAdapter.ViewHolder> {
    private List<String> imageUrlList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picture_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), position);
    }

    public class ViewHolder extends BaseViewHolder<GanHuo> {
        @BindView(R.id.picture) ImageView picture;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(GanHuo gank, final int position) {
            Glide.with(itemView.getContext())
                    .load(gank.getUrl()).apply(RequestOptions.fitCenterTransform())
                    .into(picture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageUrlList.clear();
                    for (int i = 0; i < data.size(); i++) {
                        imageUrlList.add(data.get(i).getUrl());
                    }
                    Intent intent = new Intent(itemView.getContext(), ImageViewerActivity.class);
                    intent.putExtra("CURRENT_POSITION", position);
                    intent.putStringArrayListExtra("IMAGE_URL_LIST",
                            (ArrayList<String>) imageUrlList);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}

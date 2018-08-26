package io.github.zeleven.scallop.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.ui.modules.webview.WebViewActivity;
import io.github.zeleven.scallop.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.scallop.ui.base.BaseViewHolder;
import io.github.zeleven.scallop.utils.StringUtils;

public class GanhuoAdapter extends BaseRecyclerViewAdapter<GanHuo, RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOADING = 2;

    private FooterViewHolder footerViewHolder;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                viewHolder = new ItemViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.ganhuo_list_item, parent, false));
                break;
            case VIEW_TYPE_LOADING:
                viewHolder = new FooterViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_footer, parent, false));
                footerViewHolder = (FooterViewHolder) viewHolder;
                break;
            default:
                viewHolder = new ItemViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.ganhuo_list_item, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(data.get(position), position);
        } else if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder) holder).loadMoreProgressBar.setVisibility(View.VISIBLE);
            ((FooterViewHolder) holder).noMoreHint.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (data != null && position == data.size()) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    public void resetFooter() {
        footerViewHolder.loadMoreProgressBar.setVisibility(View.GONE);
        footerViewHolder.noMoreHint.setVisibility(View.GONE);
    }

    public void setLoading() {
        footerViewHolder.loadMoreProgressBar.setVisibility(View.VISIBLE);
        footerViewHolder.noMoreHint.setVisibility(View.GONE);
    }

    public void setNoMoreHint() {
        footerViewHolder.loadMoreProgressBar.setVisibility(View.GONE);
        footerViewHolder.noMoreHint.setVisibility(View.VISIBLE);
    }

    class ItemViewHolder extends BaseViewHolder<GanHuo> {
        @BindView(R.id.title) TextView title;
        @BindView(R.id.who) TextView who;
        @BindView(R.id.pub_date) TextView pubDate;
        @BindView(R.id.image) ImageView image;

        ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final GanHuo ganhuo, int position) {
            title.setText(ganhuo.getDesc().trim());
            who.setText(ganhuo.getWho());
            pubDate.setText(StringUtils.getRelativeTime(ganhuo.getPublishedAt()));
            List<String> imageUrlList = ganhuo.getImages();
            if (imageUrlList != null && imageUrlList.size() > 0) {
                image.setVisibility(View.VISIBLE);
                Glide.with(itemView.getContext())
                        .load(imageUrlList.get(0)).apply(RequestOptions.centerCropTransform())
                        .into(image);
            } else {
                image.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), WebViewActivity.class);
                    intent.putExtra("URL", ganhuo.getUrl());
                    intent.putExtra("TITLE", ganhuo.getDesc());
                    itemView.getContext().startActivity(
                            intent,
                            ActivityOptionsCompat.makeClipRevealAnimation(
                                    itemView, 0, 0, itemView.getWidth(),
                                    itemView.getHeight()).toBundle()
                    );
                }
            });
        }
    }

    class FooterViewHolder extends BaseViewHolder {
        @BindView(R.id.load_more_progress_bar) ProgressBar loadMoreProgressBar;
        @BindView(R.id.no_more_hint) TextView noMoreHint;

        FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}

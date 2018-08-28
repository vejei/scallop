package io.github.zeleven.scallop.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.scallop.ui.base.BaseViewHolder;
import io.github.zeleven.scallop.ui.modules.browser.WebViewActivity;
import io.github.zeleven.scallop.utils.StringUtils;

public class SearchResultAdapter extends
        BaseRecyclerViewAdapter<GanHuo, SearchResultAdapter.ViewHolder> {
    private String searchKeyword;

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), position);
    }

    public class ViewHolder extends BaseViewHolder<GanHuo> {
        @BindView(R.id.title) TextView title;
        @BindView(R.id.who) TextView who;
        @BindView(R.id.pub_date) TextView pubDate;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final GanHuo ganhuo, int position) {
            String text = ganhuo.getDesc();
            boolean containKeyword = text.toLowerCase().contains(searchKeyword);
            if (containKeyword) {
                SpannableString spannableString = new SpannableString(text);
                ForegroundColorSpan span = new ForegroundColorSpan(
                        itemView.getContext().getResources().getColor(R.color.orange, null));
                int start = text.toLowerCase().indexOf(searchKeyword.toLowerCase());
                int end = start + searchKeyword.length();
                spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                title.setText(spannableString);
            } else {
                title.setText(text.trim());
            }
            who.setText(ganhuo.getWho());
            pubDate.setText(StringUtils.getRelativeTime(ganhuo.getPublishedAt()));
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
}

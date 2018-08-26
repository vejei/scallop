package io.github.zeleven.scallop.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.XianDu;
import io.github.zeleven.scallop.ui.modules.webview.WebViewActivity;
import io.github.zeleven.scallop.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.scallop.ui.base.BaseViewHolder;
import io.github.zeleven.scallop.utils.StringUtils;

public class XianDuAdapter extends BaseRecyclerViewAdapter<XianDu, XianDuAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.xiandu_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), position);
    }

    public class ViewHolder extends BaseViewHolder<XianDu> {
        @BindView(R.id.title) TextView titleTextView;
        @BindView(R.id.content) TextView contentTextView;
        @BindView(R.id.pub_date) TextView pubDate;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final XianDu xianDu, int position) {
            titleTextView.setText(xianDu.getTitle());
            String content;
            if (xianDu.getContent() != null && !xianDu.getContent().equals("")) {
                content = StringUtils.stripHtml(xianDu.getContent());
            } else {
                Pattern pattern = Pattern.compile(".+summary.+content.+'(.+)'.+direction.+");
                Matcher matcher = pattern.matcher(xianDu.getRaw());
                String html = matcher.find() ? matcher.group(1) : "";
                content = (html != null ? StringUtils.stripHtml(html) : "");
            }
            contentTextView.setText(content);
            pubDate.setText(StringUtils.getRelativeTime(xianDu.getPublishedAt()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), WebViewActivity.class);
                    intent.putExtra("URL", xianDu.getUrl());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}

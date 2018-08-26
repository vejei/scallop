package io.github.zeleven.scallop.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.XianDuSubCategory;
import io.github.zeleven.scallop.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.scallop.ui.modules.main.xiandu.list.XianDuListActivity;

public class XianDuSubCategoryAdapter
        extends BaseRecyclerViewAdapter<XianDuSubCategory, XianDuSubCategoryAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.xiandu_sub_category_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_image) ImageView categoryImage;
        @BindView(R.id.category_name) TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final XianDuSubCategory category) {
            Glide.with(itemView.getContext())
                    .load(category.getIconUrl()).apply(RequestOptions.centerCropTransform())
                    .into(categoryImage);
            categoryName.setText(category.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 点击闲读子分类列表，打开闲读列表页
                    Intent intent = new Intent(itemView.getContext(), XianDuListActivity.class);
                    intent.putExtra("CID", category.getId());
                    intent.putExtra("IMAGE_URL", category.getIconUrl());
                    intent.putExtra("CATEGORY_NAME", category.getTitle());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}

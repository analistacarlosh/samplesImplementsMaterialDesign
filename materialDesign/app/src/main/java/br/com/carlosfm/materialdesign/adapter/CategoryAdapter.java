package br.com.carlosfm.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.carlosfm.materialdesign.R;
import br.com.carlosfm.materialdesign.model.Category;

/**
 * Created by carlosfmr on 5/22/15.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context mContext;
    private List<Category> mCategory;
    private onClickInCategoryListener mListener;

    public CategoryAdapter(Context ctx, List<Category> category){
        mContext  =  ctx;
        mCategory = category;
    }

    public void setOnClickInCategoryListener(onClickInCategoryListener l){
        mListener = l;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(mContext).inflate(R.layout.card_list, parent, false);
        CategoryViewHolder vh = new CategoryViewHolder(v);

        v.setTag(vh);
        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mListener != null){
                    CategoryViewHolder vh = (CategoryViewHolder)view.getTag();
                    int position = vh.getPosition();
                    mListener.onClickInCategory(view, position, mCategory.get(position));
                }
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position){

        Category category = mCategory.get(position);
        Picasso.with(mContext).load(category.img).into(holder.imgCategory);
        holder.description.setText(category.description);
    }

    @Override
    public int getItemCount(){
        return mCategory != null ? mCategory.size() : 0;
    }

    public interface onClickInCategoryListener {
        void onClickInCategory(View v, int position, Category category);
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgCategory;
        public TextView description;

        public CategoryViewHolder(View parent){
            super(parent);
            imgCategory = (ImageView)parent.findViewById(R.id.imgCategory);
            description = (TextView)parent.findViewById(R.id.txtDescriptionCategory);
        }
    }
}

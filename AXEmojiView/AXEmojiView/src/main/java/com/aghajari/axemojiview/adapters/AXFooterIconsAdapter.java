package com.aghajari.axemojiview.adapters;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.aghajari.axemojiview.AXEmojiManager;
import com.aghajari.axemojiview.R;
import com.aghajari.axemojiview.utils.Utils;
import com.aghajari.axemojiview.view.AXEmojiLayout;
import com.aghajari.axemojiview.view.AXEmojiPager;

public class AXFooterIconsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    AXEmojiPager pager;
    public AXFooterIconsAdapter (AXEmojiPager pager){
        this.pager=pager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int iconSize = Utils.dpToPx(viewGroup.getContext(),24);
        AXEmojiLayout layout = new AXEmojiLayout(viewGroup.getContext());
        AppCompatImageView icon = new AppCompatImageView(viewGroup.getContext());
        layout.addView(icon,new AXEmojiLayout.LayoutParams(Utils.dpToPx(viewGroup.getContext(),8),Utils.dpToPx(viewGroup.getContext(),10),iconSize,iconSize));
        layout.setLayoutParams(new ViewGroup.LayoutParams(Utils.dpToPx(viewGroup.getContext(),40),Utils.dpToPx(viewGroup.getContext(),44)));
        return  new RecyclerView.ViewHolder(layout){};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        boolean selected = pager.getViewPager().getCurrentItem()==i;
        AXEmojiLayout layout = (AXEmojiLayout) viewHolder.itemView;
        AppCompatImageView icon = (AppCompatImageView) layout.getChildAt(0);

        Drawable dr = new BitmapDrawable(BitmapFactory.decodeResource(layout.getResources(), pager.getPageIcon(i)));
        if (selected) {
            DrawableCompat.setTint(DrawableCompat.wrap(dr), AXEmojiManager.getEmojiViewTheme().getFooterSelectedItemColor());
        }else{
            DrawableCompat.setTint(DrawableCompat.wrap(dr), AXEmojiManager.getEmojiViewTheme().getFooterItemColor());
        }
        icon.setImageDrawable(dr);
        Utils.setClickEffect(icon,true);
        icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (pager.getViewPager().getCurrentItem()!=i) {
                    pager.setPageIndex(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pager.getPagesCount();
    }
}

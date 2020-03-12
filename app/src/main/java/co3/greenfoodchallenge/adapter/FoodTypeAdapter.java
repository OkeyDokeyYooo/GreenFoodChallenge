package co3.greenfoodchallenge.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co3.greenfoodchallenge.FoodTypeUI;
import co3.greenfoodchallenge.R;
import co3.greenfoodchallenge.activity.SelectFoodTypeActivity;
import co3.greenfoodchallenge.activity.SelectSpecifiedFoodActivity;

public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.mViewHolder> {

    Context mContext;
    List<FoodTypeUI> mFoodTypes;

    public FoodTypeAdapter(Context context, List<FoodTypeUI> FoodTypes) {
        mContext = context;
        mFoodTypes = FoodTypes;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.food_class_adapter, parent, false);
        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, final int position) {
        holder.foodPicture.setImageResource(mFoodTypes.get(position).getBackground());
        holder.foodName.setText(mFoodTypes.get(position).getFoodType());
        holder.foodDescription.setText(mFoodTypes.get(position).getFoodDescription());
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SelectSpecifiedFoodActivity.class);
                intent.putExtra("Food_Position", position);
                /*
                mContext.startActivities(new Intent[]{intent});
                /**/
                ((Activity) mContext).startActivityForResult(
                        intent,
                        SelectFoodTypeActivity.SPECIFIC_FOOD_REQUEST_CODE
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodTypes.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        ImageView foodPicture;
        TextView foodName, foodDescription;
        ImageButton addButton;

        public mViewHolder(View itemView) {
            super(itemView);
            foodPicture = itemView.findViewById(R.id.food_type_picture);
            foodName = itemView.findViewById(R.id.food_type_name_tv);
            foodDescription = itemView.findViewById(R.id.food_type_description_tv);
            addButton = itemView.findViewById(R.id.btn_add);
        }
    }
}

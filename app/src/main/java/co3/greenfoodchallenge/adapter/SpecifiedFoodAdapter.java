package co3.greenfoodchallenge.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import co3.greenfoodchallenge.R;
import co3.greenfoodchallenge.SpecifiedFoodType;

import static android.app.Activity.RESULT_OK;

public class SpecifiedFoodAdapter extends RecyclerView.Adapter<SpecifiedFoodAdapter.mViewHolder> {

    private Context mContext;
    private List<SpecifiedFoodType> mSpecifiedFoodTypes;
    private Dialog myDialog;

    public SpecifiedFoodAdapter(Context context, List<SpecifiedFoodType> specifiedFoodTypes) {
        mContext = context;
        mSpecifiedFoodTypes = specifiedFoodTypes;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.food_selected_adapter,viewGroup,false);

        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final mViewHolder mViewHolder, int i) {
        final String type = mSpecifiedFoodTypes.get(i).getTypeName();
        //System.out.println("onBindViewHolder type = " + type);
        mViewHolder.foodPicture.setImageResource(mSpecifiedFoodTypes.get(i).getTypePicture());
        mViewHolder.foodName.setText(mSpecifiedFoodTypes.get(i).getTypeName());
        mViewHolder.foodDescription.setText(mSpecifiedFoodTypes.get(i).getTypeDescription());
        myDialog = new Dialog(mContext);

        mViewHolder.popUPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(type);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSpecifiedFoodTypes.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder{

        ImageView foodPicture;
        TextView foodName, foodDescription;
        Button popUPButton;
        // Relative to pop linker
//        private LinearLayout item_contact;

        mViewHolder(@NonNull View itemView) {
            super(itemView);
//            item_contact = (LinearLayout) itemView.findViewById(R.id.food_specified_type_id);
            foodPicture = itemView.findViewById(R.id.food_iv);
            foodName = itemView.findViewById(R.id.food_name_tv);
            foodDescription = itemView.findViewById(R.id.food_description);
            popUPButton = itemView.findViewById(R.id.pop_up_btn);
            // Will switch to pop up windows
            // Specified the button


        }
    }

    public void showPopUp(final String type){
        myDialog.setContentView(R.layout.pop_up_windows);
        Button doneButton = myDialog.findViewById(R.id.pop_up_done_btn);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final String type1 = type;
                Intent returnIntent = new Intent();
                String Tag = "MyTag2";
                returnIntent.putExtra("FoodType", type);
                String amount = ((EditText)myDialog.findViewById(R.id.editText)).
                        getText().toString();
                if (amount.isEmpty()) {
                    Toast toast = Toast.makeText(mContext,"Please Enter the Amount",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                returnIntent.putExtra("FoodAmount", Double.parseDouble(amount));
                String unit = ((Spinner)myDialog.findViewById(R.id.spinner)).
                        getSelectedItem().toString();
                String[] names = mContext.getResources().getStringArray(R.array.names);
                for (int i = 0; i < names.length; ++i) {
                    if (unit.contentEquals(names[i])) {
                        returnIntent.putExtra("FoodUnit", unit);
                        break;
                    }
                }

                Log.i(Tag,type);
                Log.i(Tag,amount);
                Log.i(Tag,unit);
                Log.i(Tag, Arrays.toString(names));


                ((Activity) mContext).setResult(RESULT_OK, returnIntent);
                ((Activity) mContext).finish();
            }
        });

        Button cancelButton = (Button) myDialog.findViewById(R.id.pop_up_cancel_btn);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do nothing here !!!
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}

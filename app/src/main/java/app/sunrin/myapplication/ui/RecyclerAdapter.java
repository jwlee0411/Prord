package app.sunrin.myapplication.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.sunrin.myapplication.R;
import app.sunrin.myapplication.ui.dashboard.Data;

public class RecyclerAdapter extends RecyclerView.Adapter<app.sunrin.myapplication.ui.RecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<Data> listData = new ArrayList<>();

    @NonNull
    @Override
    public app.sunrin.myapplication.ui.RecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);





        return new app.sunrin.myapplication.ui.RecyclerAdapter.ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull app.sunrin.myapplication.ui.RecyclerAdapter.ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textBeforeChange;
        private TextView textAfterChange;
        private TextView textMeaning;
        private TextView textOtherWord;
        private TextView textEnglish;

        private Button buttonShare;
        private Button buttonSpeak;


        ItemViewHolder(View itemView) {
            super(itemView);
        /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition() ;
                    System.out.println(pos);
                    if (pos != RecyclerView.NO_POSITION) {

                    }
                }
            });

         */

            textBeforeChange = itemView.findViewById(R.id.textBeforeChange);
            textAfterChange = itemView.findViewById(R.id.textAfterChange);
            textMeaning = itemView.findViewById(R.id.textMeaning);
            textOtherWord = itemView.findViewById(R.id.textOtherWord);
            textEnglish = itemView.findViewById(R.id.textEnglish);
            buttonShare = itemView.findViewById(R.id.buttonShare);
            buttonSpeak = itemView.findViewById(R.id.buttonSpeak);



        }

        void onBind(final Data data) {
            textBeforeChange.setText(data.getTextBeforeChange());
            textMeaning.setText(data.getTextMeaning());
            textEnglish.setText(data.getTextEnglish());
            textOtherWord.setText(data.getTextOtherWord());
            textAfterChange.setText(data.getTextAfterChange());



            buttonSpeak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    System.out.println(pos);

                }
            });

            buttonShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Sharing_intent = new Intent(Intent.ACTION_SEND);
                    Sharing_intent.setType("text/plain");
                    String Test_Message = data.getTextBeforeChange() + "\n\n"+ data.getTextMeaning();
                    Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                    Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                    view.getContext().startActivity(Sharing);

                }
            });

        }
    }
}



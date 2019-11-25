package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.model.Faq;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqActivity extends AppCompatActivity {

    @BindView(R.id.faqs)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);


    }

    private class FaqViewHolder extends RecyclerView.ViewHolder{
        public TextView question;
        public TextView reponse;

        public FaqViewHolder(@NonNull View itemView,OnItemSelectedListener listener){
            super(itemView);
            question = itemView.findViewById(R.id.question);
            reponse = itemView.findViewById(R.id.answerd);
        }
    }

    private class FaqAdapter extends RecyclerView.Adapter<FaqViewHolder>{
        private ArrayList<Faq> myFaq;

        @NonNull
        @Override
        public FaqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_element,parent,false);
            FaqViewHolder vh = new FaqViewHolder(v,position -> {
                Faq faqSelect = myFaq.get(position);
            });
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull FaqViewHolder holder, int position){
            Faq annonce = myFaq.get(position);
            holder.question.setText(annonce.getQuestion());
            holder.reponse.setText(annonce.getReponse());
        }
        @Override
        public int getItemCount(){
            return myFaq == null ? 0 : myFaq.size();
        }

        public void setFaq(ArrayList<Faq> myFaqs){
            this.myFaq = myFaqs;
            notifyDataSetChanged();
        }
    }
}

package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity.DataAccess.FaqDao;
import com.example.smartcity.DataAccess.FaqDataAccess;
import com.example.smartcity.R;
import com.example.smartcity.model.Faq;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqActivity extends AppCompatActivity {

    @BindView(R.id.faqs)
    RecyclerView recyclerView;
    FaqAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);
        adapter = new FaqAdapter();

        LoadFaq loadFaq = new LoadFaq();
        loadFaq.execute();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private class FaqViewHolder extends RecyclerView.ViewHolder{
        public TextView question;
        public TextView reponse;

        public FaqViewHolder(@NonNull View itemView){
            super(itemView);
            question = itemView.findViewById(R.id.question);
            reponse = itemView.findViewById(R.id.answerd);
        }

        public boolean isAffiche() {
            return reponse.getText().toString().compareTo("") != 0;
        }
    }

    private class FaqAdapter extends RecyclerView.Adapter<FaqViewHolder>{
        private ArrayList<Faq> myFaq;

        @NonNull
        @Override
        public FaqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_element,parent,false);
            FaqViewHolder vh = new FaqViewHolder(v);
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull FaqViewHolder holder, int position){
            Faq annonce = myFaq.get(position);
            holder.question.setText(annonce.getQuestion());
            holder.question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPosition = holder.getAdapterPosition();
                    if(!holder.isAffiche()){
                        Faq faqSelect = myFaq.get(position);
                        holder.reponse.setText(faqSelect.getReponse());
                    }
                    else{
                        holder.reponse.setText("");
                    }
                }
            });
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

    private class LoadFaq extends AsyncTask<Void,Void,ArrayList<Faq>>{
        @Override
        protected ArrayList<Faq> doInBackground(Void... voids) {
            FaqDataAccess access = new FaqDao();
            return access.getAllFaq() ;
        }

        @Override
        protected void onPostExecute(ArrayList<Faq> faqs) {
            adapter.setFaq(faqs);
        }
    }
}
